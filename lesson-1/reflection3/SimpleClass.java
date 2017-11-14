package reflection3;

/**
 * Created by User on 11/14/2017.
 */
public class SimpleClass {

    private int a;

    @Save
    private int b;

    @Save
    private long l;

    @Save
    private String s;

    SimpleClass(int a, int b, long l, String s){
        this.a = a;
        this.b = b;
        this.l = l;
        this.s = s;
    }

    public SimpleClass() {

    }

    @Override
    public String toString() {
        return "SimpleClass{" +
                "a=" + a +
                ", b=" + b +
                ", l=" + l +
                ", s='" + s + '\'' +
                '}';
    }
}
