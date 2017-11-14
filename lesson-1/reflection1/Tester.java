package reflection1;

/**
 * Created by User on 11/14/2017.
 */

class Tester{
    int a;
    int b;

    @Test(a = 5,b = 6)
    public static int Sum(int a, int b){
        return a+b;
    }
}
