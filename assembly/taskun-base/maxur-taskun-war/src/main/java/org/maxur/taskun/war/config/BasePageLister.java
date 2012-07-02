package org.maxur.taskun.war.config;

import com.google.inject.Provider;
import com.google.inject.Singleton;
import org.apache.wicket.markup.html.WebPage;
import org.maxur.adapter.yaml4.pages.BasePage;
import org.maxur.adapter.yaml4.pages.error.AccessDeniedPage;
import org.maxur.adapter.yaml4.pages.error.ExpiredPage;
import org.maxur.adapter.yaml4.pages.error.InternalErrorPage;
import org.maxur.adapter.yaml4.pages.error.NotFoundPage;
import org.maxur.commons.component.model.bookmark.BaseBookmark;
import org.maxur.commons.component.model.bookmark.BaseBookmarks;
import org.maxur.commons.view.api.Bookmarks;
import org.maxur.commons.view.api.PageLister;
import org.maxur.taskun.war.pages.about.AboutPage;
import org.maxur.taskun.war.pages.home.HomePage;


/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>7/2/12</pre>
 */
@Singleton
public class BasePageLister implements PageLister<WebPage>, Provider<Bookmarks<BaseBookmark>> {

    @Override
    public Bookmarks<BaseBookmark> get() {
        final BaseBookmarks bookmarks = new BaseBookmarks();
        bookmarks.add(new BaseBookmark("/home", getStartPage()));
        bookmarks.add(new BaseBookmark("/about", AboutPage.class));
        return bookmarks;
    }

    @Override
    public Class<? extends BasePage> getLoginPage() {
        return null;
    }

    @Override
    public Class<? extends BasePage> getStartPage() {
        return HomePage.class;
    }

    @Override
    public Class<? extends BasePage> getInternalErrorPage() {
        return InternalErrorPage.class;
    }

    @Override
    public Class<? extends BasePage> getPageExpiredErrorPage() {
        return ExpiredPage.class;
    }

    @Override
    public Class<? extends BasePage> getAccessDeniedPage() {
        return AccessDeniedPage.class;
    }

    @Override
    public Class<? extends BasePage> getPageNotFoundPage() {
        return NotFoundPage.class;
    }


}
