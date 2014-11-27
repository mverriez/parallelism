package com.mverriez;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * @author mverriez
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class CrawlerTest {

    @Test
    public void testCrawlerTpe() throws Exception {
        final CrawlerTPE crawlerTPE = new CrawlerTPE(64);
        crawlerTPE.processLink("https://www.google.fr");

        while (crawlerTPE.size() < 100){
            //process, little crawler...
        }
        crawlerTPE.stop();
        System.out.println(crawlerTPE.size() + " links visited in " + (System.currentTimeMillis() - crawlerTPE.getStartTime()) + " ms.");
    }

    @Test
    public void testCrawlerFjp() throws Exception {
        final CrawlerFJP crawlerFjp = new CrawlerFJP(64);
        crawlerFjp.processLink("https://www.google.fr");

        while (crawlerFjp.size() < 100){
            //process, little crawler...
        }
        crawlerFjp.stop();
        System.out.println(crawlerFjp.size() + " links visited in " + (System.currentTimeMillis() - crawlerFjp.getStartTime()) + " ms.");
    }
}
