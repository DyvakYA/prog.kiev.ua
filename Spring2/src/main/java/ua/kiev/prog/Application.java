package ua.kiev.prog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ua.kiev.prog.model.entity.Contact;
import ua.kiev.prog.model.entity.Group;
import ua.kiev.prog.model.service.ContactService;
import ua.kiev.prog.model.service.GroupService;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(final ContactService contactService, final GroupService groupService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                Group group = new Group.Builder().setName("Test").build();
                Group group1 = new Group.Builder().setName("Fast").build();
                Group group2 = new Group.Builder().setName("Best").build();

                Contact contact;

                groupService.addGroup(group);
                groupService.addGroup(group1);
                groupService.addGroup(group2);

                for (int i = 0; i < 10; i++) {
                    contact = new Contact.Builder()
                            .setGroup(null)
                            .setName("Name" + i)
                            .setSurname("Surname" + i)
                            .setPhone("1234567" + i)
                            .setEmail("user" + i + "@test.com")
                            .build();
                    contactService.addContact(contact);
                }
                for (int i = 0; i < 10; i++) {
                    contact = new Contact.Builder()
                            .setGroup(group)
                            .setName("Other" + i)
                            .setSurname("OtherSurname" + i)
                            .setPhone("7654321" + i)
                            .setEmail("user" + i + "@other.com")
                            .build();
                    contactService.addContact(contact);
                }

                for (int i = 0; i < 10; i++) {
                    contact = new Contact.Builder()
                            .setGroup(group1)
                            .setName("More" + i)
                            .setSurname("LuckySurname" + i)
                            .setPhone("7654321000" + i)
                            .setEmail("user" + i + "@more.ru")
                            .build();
                    contactService.addContact(contact);
                }

                for (int i = 0; i < 10; i++) {
                    contact = new Contact.Builder()
                            .setGroup(group2)
                            .setName("This" + i)
                            .setSurname("BestSurname" + i)
                            .setPhone("76543211111" + i)
                            .setEmail("user" + i + "@best.com.ua")
                            .build();
                    contactService.addContact(contact);
                }
            }
        };
    }
}