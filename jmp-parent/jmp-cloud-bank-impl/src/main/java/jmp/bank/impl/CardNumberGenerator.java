package jmp.bank.impl;

import java.util.concurrent.ThreadLocalRandom;

public interface CardNumberGenerator {

    static String generate() {
        /* return a random long of 16 length */
        long smallest = 1000_0000_0000_0000L;
        long biggest =  9999_9999_9999_9999L;

        // return a long between smallest and biggest (+1 to include biggest as well with the upper bound)
        long random = ThreadLocalRandom.current().nextLong(smallest, biggest+1);

        return String.valueOf(random);
    }

}
