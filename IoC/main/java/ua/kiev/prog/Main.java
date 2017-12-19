package ua.kiev.prog;

enum LoggerType {Console, File};

public class Main {

    public static void main(String[] args) {

        Case1 case1 = new Case1();
        case1.invoke();

        Case2 case2 = new Case2();
        case2.invoke();

        Case3 case3 = new Case3();
        case3.invoke();



    }
}
