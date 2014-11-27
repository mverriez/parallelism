package com.mverriez;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;

/**
 * @author mverriez
 */
public class CrawlerFJP implements LinkHandler {

    private final long startTime;
    private final ForkJoinPool forkJoinPool;

    private final Set<String> visitedLink = Collections.synchronizedSet(new HashSet<String>());

    public CrawlerFJP(Integer threads){
        forkJoinPool = new ForkJoinPool(threads);
        startTime = System.currentTimeMillis();
    }

    @Override
    public void stop() {
        forkJoinPool.shutdown();
    }

    @Override
    public long getStartTime() {
        return startTime;
    }

    @Override
    public void processLink(String link) throws Exception {
        forkJoinPool.execute(new LinkFinderAction(this, link));
    }

    @Override
    public int size() {
        return visitedLink.size();
    }

    @Override
    public boolean isVisited(String link) {
        return visitedLink.contains(link);
    }

    @Override
    public void addVisited(String link) {
        visitedLink.add(link);
    }
}
