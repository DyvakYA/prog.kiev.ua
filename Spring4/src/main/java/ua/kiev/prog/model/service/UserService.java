package ua.kiev.prog.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.prog.model.dao.UserRepository;
import ua.kiev.prog.model.entity.CustomUser;
import ua.kiev.prog.model.entity.UserRole;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public CustomUser findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Transactional
    public boolean addUser(String login, String passHash, UserRole role, String email,
                           String phone) {
        if (userRepository.existsByLogin(login))
            return false;

        CustomUser customUser = CustomUser.builder()
                .login(login)
                .password(passHash)
                .role(role)
                .email(email)
                .phone(phone)
                .build();
        userRepository.save(customUser);

        return true;
    }

    @Transactional
    public void updateUser(String login, String email, String phone) {
        CustomUser customUser = userRepository.findByLogin(login);

        customUser.setEmail(email);
        customUser.setPhone(phone);

        userRepository.save(customUser);
    }

    @Transactional(readOnly = true)
    public List<CustomUser> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).getContent();
    }
}
