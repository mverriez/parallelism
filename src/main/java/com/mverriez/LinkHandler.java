package com.mverriez;

/**
 * @author mverriez
 */
public interface LinkHandler {

    void stop();

    /**
     * Get crawler start time
     * @return
     */
    long getStartTime();

    /**
    * Process Link
    * @param link
    * @throws Exception
    */
    void processLink(String link) throws Exception;

    /**
     * Returns the number of visited links
     * @return
     */
    int size();

    /**
     * Checks if the link was already visited
     * @param link
     * @return
     */
    boolean isVisited(String link);

    /**
     * Marks this link as visited
     * @param link
     */
    void addVisited(String link);
}
