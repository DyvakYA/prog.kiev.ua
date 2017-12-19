package ua.kiev.prog;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by User on 12/20/2017.
 */
public class Case3 {

    public void invoke() {
        // case #3
        System.out.println(">>> Sample #3:");

        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("/spring-config.xml");
        try {
            Notifier notifier = ctx.getBean("notifier", Notifier.class);
            notifier.sendSms();
        } finally {
            ctx.close();
        }
    }
}
