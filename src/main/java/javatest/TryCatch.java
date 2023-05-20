package javatest;

import java.util.concurrent.atomic.LongAdder;

public class TryCatch {
    public int tryCatchTest() throws Exception {
        try {
            throw new Exception();

        }
        finally {
            System.out.println("ghjkl");
        }
    }

    public static void main(String[] args) throws Exception {
        TryCatch tryCatch = new TryCatch();
        int i = tryCatch.tryCatchTest();
        System.out.println(i);
    }
}
