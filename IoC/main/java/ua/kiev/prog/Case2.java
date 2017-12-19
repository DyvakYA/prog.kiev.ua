package ua.kiev.prog;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by User on 12/19/2017.
 */

public class Case2 {

    ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

    public void invoke() {

        // case #2
        System.out.println(">>> Sample #2:");

        LoggerAPI api1 = ctx.getBean("fileLoggerAPI", LoggerAPI.class);
        LoggerAPI api2 = ctx.getBean("consoleLoggerAPI", LoggerAPI.class);

        try {
            //            Notifier notifier = ctx.getBean("notifier1", Notifier.class);
            //            notifier.sendSms();
            //            Notifier notifier2 = ctx.getBean("notifier2", Notifier.class);
            //            notifier2.sendSms();

            Notifier notifier1 = ctx.getBean("notifier", Notifier.class);
            notifier1.setLoggerAPI(api1);
            notifier1.sendSms();

            Notifier notifier2 = ctx.getBean("notifier", Notifier.class);
            notifier2.setLoggerAPI(api2);
            notifier2.sendSms();

        } finally {
            ctx.close();
        }
    }
}
