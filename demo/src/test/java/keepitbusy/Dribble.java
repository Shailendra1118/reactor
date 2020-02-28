package keepitbusy;

import org.junit.Test;

public class Dribble {

    @Test
    public void testObj(){
        TestClass obj = new TestClass();
        obj.print(null);
    }

    class TestClass {
        public void print(String a){
            System.out.println("A: "+a);
        }
        public void print(Object b) {
            System.out.println("B: "+b);
        }
    }
}
