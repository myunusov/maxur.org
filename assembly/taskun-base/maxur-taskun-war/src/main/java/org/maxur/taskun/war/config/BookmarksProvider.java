package org.maxur.taskun.war.config;

import com.google.inject.Provider;
import org.maxur.commons.component.model.bookmark.Bookmark;
import org.maxur.commons.component.model.bookmark.Bookmarks;
import org.maxur.taskun.war.pages.about.AboutPage;
import org.maxur.taskun.war.pages.home.HomePage;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>5/10/12</pre>
 */
public class BookmarksProvider implements Provider<Bookmarks> {

    @Override
    public Bookmarks get() {
        final Bookmarks bookmarks = new Bookmarks();
        bookmarks.add(new Bookmark("/home", HomePage.class));
        bookmarks.add(new Bookmark("/about", AboutPage.class));
        return bookmarks;
    }
}
