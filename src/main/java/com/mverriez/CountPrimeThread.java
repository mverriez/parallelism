package com.mverriez;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

/**
 * @author mverriez
 */
public class CountPrimeThread {

    public long count(Integer limit){
        return IntStream.rangeClosed(1, limit).parallel().filter(CountPrimeThread::isPrime).count();
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
