package org.maxur.commons.component.model.bookmark;

import org.maxur.commons.view.api.Bookmarks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>9/2/11</pre>
 */
public class BaseBookmarks implements Bookmarks<BaseBookmark> {

    private List<BaseBookmark> bookmarks = new ArrayList<>();

    @Override
    public Iterator<BaseBookmark> iterator() {
        return bookmarks.iterator();
    }

    public void add(final BaseBookmark bookmark) {
        this.bookmarks.add(bookmark);
    }

}