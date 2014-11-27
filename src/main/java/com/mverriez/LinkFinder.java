package com.mverriez;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mverriez
 */
public class LinkFinder implements Runnable {

    private final LinkHandler linkHandler;
    private final String url;

    public LinkFinder(LinkHandler linkHandler, String url){
        this.linkHandler = linkHandler;
        this.url = url;
    }

    @Override
    public void run() {

        //go go go!
        try {
            final URL linkAsUrl = new URL(url);
            final Parser parser = new Parser(linkAsUrl.openConnection());
            final NodeList list = parser.extractAllNodesThatMatch(new NodeClassFilter(LinkTag.class));
            final List<String> urls = new ArrayList<>();
            for (Node node : list.toNodeArray()) {
                LinkTag linkTag = (LinkTag)node;
                if (!linkTag.getLink().isEmpty() && !this.linkHandler.isVisited(linkTag.getLink())) {
                    urls.add(linkTag.getLink());
                }
            }
            linkHandler.addVisited(url);

            for (String currentUrl : urls) {
                linkHandler.processLink(currentUrl);
            }
        } catch (Exception e) {
           // e.printStackTrace();
        }
    }
}
