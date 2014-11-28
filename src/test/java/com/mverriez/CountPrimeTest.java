package com.mverriez;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.perf4j.StopWatch;

@RunWith(BlockJUnit4ClassRunner.class)
public class CountPrimeTest extends TestCase {

    @Test
    public void testCount() throws Exception {
        StopWatch stopWatch = new StopWatch("Count with parallel() stream");
        long count = new CountPrimeThread().count(10000000);
        stopWatch.stop();
        System.out.println(stopWatch.toString());
        Assert.assertEquals(664579,count);
    }

    @Test
    public void testCountFJP() throws Exception {
        StopWatch stopWatch = new StopWatch("Count with explicit fjp");
        long count = new CountPrimeFJP().count(10000000);
        stopWatch.stop();
        System.out.println(stopWatch.toString());
        Assert.assertEquals(664579,count);
    }

    @Test
    public void testCountSerial() throws Exception {
        StopWatch stopWatch = new StopWatch("Serial count");
        long count = new CountPrimeSerial().count(10000000);
        stopWatch.stop();
        System.out.println(stopWatch.toString());
        Assert.assertEquals(664579,count);
    }
}