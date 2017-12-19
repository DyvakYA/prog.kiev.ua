package ua.kiev.prog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AppConfig {
    // comment out this bean to test the result
    @Bean
    public Preprocessor datePreprocessor() {
        return new DatePreprocessor();
    }

    @Bean
    public Preprocessor badPreprocessor() {
        return new BadPreprocessor();
    }

    @Bean(initMethod = "open", destroyMethod = "close")
    public LoggerAPI fileLoggerAPI() {
        return new FileLoggerAPI("log.txt");
    }

    @Bean(name="consoleLogger")
    public LoggerAPI consoleLoggerAPI() {
        return new ConsoleLoggerAPI();
    }

//    @Bean(name = "notifier1")
//    public Notifier notifier1(@Qualifier("fileLoggerAPI") LoggerAPI api) {
//        return new Notifier(api);
//    }
//
//    @Bean(name = "notifier2")
//    public Notifier notifier2(@Qualifier("consoleLogger") LoggerAPI api) {
//        return new Notifier(api);
//    }

}
