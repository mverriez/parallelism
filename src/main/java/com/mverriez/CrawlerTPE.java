package com.mverriez;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author mverriez
 */
public class CrawlerTPE implements LinkHandler{

    /**
     * Executor service
     */
    private final ExecutorService executorService;

    private final Set<String> visitedLink = Collections.synchronizedSet(new HashSet<String>());

    private final long startTime;

    public CrawlerTPE(Integer threads){
        startTime = System.currentTimeMillis();
        executorService = Executors.newFixedThreadPool(threads);
    }

    @Override
    public void stop() {
        executorService.shutdown();
    }

    @Override
    public long getStartTime() {
        return startTime;
    }

    @Override
    public void processLink(String link) throws Exception {
        executorService.submit(new LinkFinder(this, link));
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
