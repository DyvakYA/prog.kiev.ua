package ua.kiev.prog;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MyController {

    private Map<Long, Photo> photos = new HashMap<>();

    @RequestMapping("/")
    public ModelAndView onIndex() {
        return new ModelAndView("index", "photos", photos.values());
    }

    @RequestMapping(value = "/add_photo", method = RequestMethod.POST)
    public String onAddPhoto(@RequestParam("photo") MultipartFile[] files) {
        if (files == null) {
            throw new PhotoErrorException();
        }
        try {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    Photo photo = new Photo.Builder()
                            .setId(System.currentTimeMillis() + Arrays.asList(files).indexOf(file))
                            .setName(file.getOriginalFilename())
                            .setFile(file.getBytes())
                            .build();
                    photos.put(photo.getId(), photo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @RequestMapping("/photo/{photo_id}")
    public ResponseEntity<byte[]> onPhoto(@PathVariable("photo_id") long id) {
        return photoById(id);
    }

    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public ResponseEntity<byte[]> onView(@RequestParam("photo_id") long id) {
        return photoById(id);
    }

    @RequestMapping("/delete")
    public String onDelete(@RequestParam("photo_id") List<Long> ids) {
        if (!ids.isEmpty()) {
            for (Long id : ids) {
                if (photos.remove(id) == null)
                    throw new PhotoNotFoundException();
            }
        }
        return "redirect:/";
    }

    private ResponseEntity<byte[]> photoById(long id) {
        byte[] bytes = photos.get(id).getFile();
        if (bytes == null)
            throw new PhotoNotFoundException();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }
}
