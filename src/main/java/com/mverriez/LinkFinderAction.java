package com.mverriez;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * @author mverriez
 */
public class LinkFinderAction extends RecursiveAction {

    /**
     * link handler
     */
    private final LinkHandler linkHandler;


    /**
     * url
     */
    private final String url;


    public LinkFinderAction(LinkHandler linkHandler, String url) {
        this.linkHandler = linkHandler;
        this.url = url;
    }

    @Override
    protected void compute() {

        final URL linkAsUrl;
        try {
            final List<RecursiveAction> actions = new ArrayList<>();
            linkAsUrl = new URL(url);
            final Parser parser = new Parser(linkAsUrl.openConnection());
            final NodeList list = parser.extractAllNodesThatMatch(new NodeClassFilter(LinkTag.class));

            for (Node node : list.toNodeArray()) {
                LinkTag linkTag = (LinkTag)node;
                if (!linkTag.getLink().isEmpty() && !this.linkHandler.isVisited(linkTag.getLink())) {
                    actions.add(new LinkFinderAction(this.linkHandler, linkTag.getLink()));
                }
            }
            this.linkHandler.addVisited(this.url);
            invokeAll(actions);
        } catch (Exception e) {

        }
    }
}
