package ua.kiev.prog;

import java.io.IOException;

/**
 * Created by User on 12/20/2017.
 */
public class Case1 {

    static LoggerType loggerType = LoggerType.File;
    static boolean usePreprocessors = true;

    public void invoke() {
        // case #1
        System.out.println(">>> Sample #1:");

        LoggerAPI api = null;
        if (loggerType == LoggerType.Console)
            api = new ConsoleLoggerAPI();
        else if (loggerType == LoggerType.File)
            api = new FileLoggerAPI("log.txt");

        try {
            api.open();
            try {
                // optional functionality
                if (usePreprocessors) {
                    Preprocessor preprocessor = new DatePreprocessor();
                    api.addPreprocessor(preprocessor);
                    Preprocessor preprocessor1 = new BadPreprocessor();
                    api.addPreprocessor(preprocessor1);
                }
                Notifier notifier = new Notifier(api);
                notifier.sendSms();
            } finally {
                api.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
}
