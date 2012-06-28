package org.maxur.commons.component.model.bookmark;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>9/2/11</pre>
 */
public class Bookmarks implements Iterable<Bookmark> {

    private List<Bookmark> bookmarks = new ArrayList<>();

    @Override
    public Iterator<Bookmark> iterator() {
        return bookmarks.iterator();
    }

    public void add(final Bookmark bookmark) {
        this.bookmarks.add(bookmark);
    }

}