package ua.kiev.prog;

import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import ua.kiev.prog.model.entity.Contact;
import ua.kiev.prog.model.entity.Group;
import ua.kiev.prog.model.entity.UserRole;
import ua.kiev.prog.model.service.ContactService;
import ua.kiev.prog.model.service.GroupService;
import ua.kiev.prog.model.service.UserService;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(final UserService userService, final ContactService contactService, final GroupService groupService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                userService.addUser("admin", "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8", UserRole.ADMIN, "", "");
                userService.addUser("user", "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8", UserRole.USER, "", "");

                val group = Group.builder()
                        .name("Test")
                        .build();
                val group1 = Group.builder()
                        .name("Fast")
                        .build();
                val group2 = Group.builder()
                        .name("Best")
                        .build();

                groupService.addGroup(group);
                groupService.addGroup(group1);
                groupService.addGroup(group2);

                for (int i = 0; i < 10; i++) {
                    val contact = Contact.builder()
                            .group(null)
                            .name("Name" + i)
                            .surname("Surname" + i)
                            .phone("1234567" + i)
                            .email("user" + i + "@test.com")
                            .build();
                    contactService.addContact(contact);
                }
                for (int i = 0; i < 10; i++) {
                    val contact = Contact.builder()
                            .group(group)
                            .name("Other" + i)
                            .surname("OtherSurname" + i)
                            .phone("7654321" + i)
                            .email("user" + i + "@other.com")
                            .build();
                    contactService.addContact(contact);
                }

                for (int i = 0; i < 10; i++) {
                    val contact = Contact.builder()
                            .group(group1)
                            .name("More" + i)
                            .surname("LuckySurname" + i)
                            .phone("7654321000" + i)
                            .email("user" + i + "@more.ru")
                            .build();
                    contactService.addContact(contact);
                }

                for (int i = 0; i < 10; i++) {
                    val contact = Contact.builder()
                            .group(group2)
                            .name("This" + i)
                            .surname("BestSurname" + i)
                            .phone("76543211111" + i)
                            .email("user" + i + "@best.com.ua")
                            .build();
                    contactService.addContact(contact);
                }
            }
        };
    }


    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        resolver.setContentType("text/html;charset=UTF-8");
        resolver.setViewClass(JstlView.class);

        return resolver;
    }

    @Bean
    public ResourceBundleMessageSource setupMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.addBasenames("messages");
        return messageSource;
    }
}