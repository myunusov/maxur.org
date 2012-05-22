package org.maxur.taskun.war.pages.home;

import com.google.inject.Inject;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.maxur.taskun.domain.Issue;
import org.maxur.taskun.domain.IssueLister;
import org.maxur.taskun.war.pages.BasePage;

import java.util.List;


/**
 * It's Home Page controller class.
 *
 * @author Maxim Yunusov
 * @version 1.0 27.09.11
 */
public class HomePage extends BasePage {

    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = -306372547626459776L;

    @Inject
    private IssueLister issueLister;


    /**
     * It's Home Page constructor.
     */
    public HomePage() {
        add(new IssueListView("list_view", issueLister.listActive()));
    }


    private static class IssueListView extends ListView<Issue> {

        private static final long serialVersionUID = 1190381851610222798L;

        public IssueListView(final String id, final List<Issue> list) {
            super(id, list);
        }

        protected void populateItem(final ListItem<Issue> item) {
            final Issue issue = item.getModelObject();
            item.add(new Label("label", issue.getDescription()));
        }


    }
}
