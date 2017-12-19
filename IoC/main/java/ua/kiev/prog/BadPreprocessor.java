package ua.kiev.prog;

/**
 * Created by User on 12/19/2017.
 */
public class BadPreprocessor implements Preprocessor {
    @Override
    public String prepare(String msg) {
        return "[Bad] " + msg;
    }
}
