package com.mverriez;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

/**
 * @author mverriez
 */
public class CountPrimeThread {

    private static Map<String, Integer> map = new ConcurrentHashMap<>();

    public long count(Integer limit){
        final long count = IntStream.rangeClosed(1, limit).parallel().filter(CountPrimeThread::isPrime).count();
        map.forEach((k, v) -> {
            System.out.println(k +":" + v);
        });
        return count;
    }

    public static boolean isPrime(Integer n){
        final String name = Thread.currentThread().getName();
        map.compute(name, (k, v) -> v==null ? 1:v+1);
        if (n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
