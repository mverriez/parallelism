package com.mverriez;

import java.util.stream.IntStream;

/**
 * @author mverriez
 */
public class CountPrimeSerial {
;
    public long count(Integer limit){
        int count=0;
        for (int i = 0; i <= limit; i++) {
            if (isPrime(i))count++;
        }
        return count;
    }

    public static boolean isPrime(Integer n){
        if (n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
