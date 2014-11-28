package com.mverriez;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

/**
 * @author mverriez
 */
public class CountPrimeFJP {


    private final ForkJoinPool forkJoinPool;

    CountPrimeFJP(){
        forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
    }

    public Integer count(Integer limit){
        return forkJoinPool.invoke(new CountPrimeFJPTask(0, limit));
    }


    public class CountPrimeFJPTask extends RecursiveTask<Integer> {

        private final Integer left;
        private final Integer right;

        CountPrimeFJPTask(Integer left, Integer right){
            this.left = left;
            this.right = right;
        }

        protected Integer compute() {
            if (right - left < 10){
                int count=0;
                for (int i = left; i <= right; i++) {
                    if (isPrime(i)){
                        count ++;
                    }
                }
                return count;
            }else{
                Integer middle = left + (right - left) / 2;
                CountPrimeFJPTask task1 = new CountPrimeFJPTask(left, middle);
                CountPrimeFJPTask task2 = new CountPrimeFJPTask(middle + 1, right);
                task2.fork();
                return  task1.compute() + task2.join();
            }
        }

        public boolean isPrime(Integer n){
            if (n % 2 == 0) return false;
            for (int i = 3; i * i <= n; i += 2) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
