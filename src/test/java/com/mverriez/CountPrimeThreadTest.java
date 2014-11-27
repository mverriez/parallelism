package com.mverriez;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class CountPrimeThreadTest extends TestCase {

    @Test
    public void testCount() throws Exception {
        long count = new CountPrimeThread().count(1000000);
        Assert.assertEquals(78498,count);
    }
}