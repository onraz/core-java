package core;

public class BoxingAssertions {
    // autoboxing prevents return types but not overloading
    Integer boxCalc(Integer a) {
        return 0;
    }

    int boxCalc(int d) {
        return 2;
    }

//    public static void main(String[] args) {
//        int data = 12;
//        System.out.println(boxCalc((Integer)(12/5)));
//    }

}

class BoxingAssertions2 extends BoxingAssertions {
    // cant be used for override
    /*Integer*/int boxCalc(int a) {
        Integer a1 = null;
        int b2 = a1; // unboxing creates NPE
        return super.boxCalc((Integer)a);
    }
}
