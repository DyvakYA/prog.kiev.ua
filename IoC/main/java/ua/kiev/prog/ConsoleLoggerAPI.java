package ua.kiev.prog;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ConsoleLoggerAPI extends LoggerAPI {

    public ConsoleLoggerAPI() {
    }

    @Override
    protected void doLog(String msg) throws IOException {
        System.out.println("Writing to console: " + msg);
    }

    @Override
    public void open() throws IOException {
        // do nothing
    }

    @Override
    public void close() {
        // do nothing
    }

    public void setPreprocessor(DatePreprocessor preprocessor) {
    }
}
