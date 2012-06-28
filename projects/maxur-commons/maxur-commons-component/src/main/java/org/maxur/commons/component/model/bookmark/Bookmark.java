package org.maxur.commons.component.model.bookmark;

import org.apache.wicket.core.request.mapper.MountedMapper;
import org.apache.wicket.markup.html.WebPage;

import java.io.Serializable;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>9/2/11</pre>
 */
public class Bookmark implements Serializable {

    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = 8210845160078911968L;

    private final String mountPath;

    private final Class<? extends WebPage> pageClass;

    public Bookmark(final String mountPath, final Class<? extends WebPage> pageClass) {
        assert (null != mountPath && null != pageClass) : "Short links map is invalid";
        this.mountPath = mountPath;
        this.pageClass = pageClass;
    }

    public MountedMapper getMapper() {
        return new MountedMapper(mountPath, pageClass);
    }
}