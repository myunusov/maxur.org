package org.maxur.commons.component.model.bookmark;

import org.apache.wicket.markup.html.WebPage;

import java.io.Serializable;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>9/2/11</pre>
 */
public class Bookmark implements Serializable {

    private static final long serialVersionUID = 8210845160078911968L;

    private String shortLink;

    private Class<? extends WebPage> targetClass;

    public Bookmark(final String shortLink, final Class<? extends WebPage> targetClass) {
        this.shortLink = shortLink;
        this.targetClass = targetClass;
    }

    public String getShortLink() {
        return shortLink;
    }

    public Class<? extends WebPage> getTargetClass() {
        return targetClass;
    }

}