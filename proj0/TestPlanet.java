public class TestPlanet {
    public static void main(String[] args) {
        checkPairwise();
    }

    /**
     *  Checks whether or not two Doubles are equal and prints the result.
     *
     *  @param  expected    Expected double
     *  @param  actual      Double received
     *  @param  label       Label for the 'test' case
     *  @param  eps         Tolerance for the double comparison.
     */

    private static void checkEquals(double expected, double actual, String label, double eps) {
        if (Math.abs(expected - actual) <= eps * Math.max(expected, actual)) {
            System.out.println("PASS: " + label + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label + ": Expected " + expected + " and you gave " + actual);
        }
    }

    private static void checkPairwise() {
        System.out.println("Checking update...");

        Planet p1 = new Planet(0,0,1,2,3,"jupiter.gif");
        Planet p2 = new Planet(1,1,2,3,4,"saturn.gif");

        checkEquals(8.004*Math.pow(10,-10), 6.67*Math.pow(10,-11)*p1.mass*p2.mass, "xxVel update()", 0.01);
    }
}
