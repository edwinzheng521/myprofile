package cs1302.p2;

import cs1302.adt.StringList;
import cs1302.oracle.OracleStringList;

/**
 *tester that test the methods from {@link BaseStringlist} and its children class.
 */
public class Driver {
    public static void main(String[] args) {
        StringList sl;


        //sl = new OracleStringList();
        // sl= new ArrayStringList();

        sl = new LinkedStringList();


        // Test isEmpty on an empty list
        if (sl.isEmpty()) {
            System.out.println("isEmpty: Test Passed");
        } else {
            System.out.println("isEmpty: Test Failed");
            System.exit(0);
        } // if
        sl.add(0,"hello");
        sl.add(1,"world");
        sl.add(2,"lorem");
        System.out.println(sl);
        System.out.println("2 " + sl.get(2));
        System.out.println("size " + sl.size());
        System.out.println("remove 0 " + sl.remove(0));
        sl.prepend("prepend");
        sl.append("append");
        System.out.println(sl);

    }

}
