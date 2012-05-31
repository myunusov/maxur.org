package org.maxur.taskun.domain.internal;

import com.google.inject.Inject;
import org.maxur.taskun.domain.Issue;
import org.maxur.taskun.domain.IssueLister;
import org.maxur.taskun.domain.IssueProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Maxim Yunusov
 * @version 1.0 20.05.12
 */
public class IssueListerImpl implements IssueLister {

    private static final long serialVersionUID = -7430403084100489598L;

    @Inject
    private Set<IssueProvider> providers;

    @SuppressWarnings("UnusedDeclaration")
    public void setProviders(Set<IssueProvider> providers) {
        this.providers = providers;
    }

    @Override
    public List<Issue> listActive() {
        final List<Issue> result = new ArrayList<>();
        for (IssueProvider provider : providers) {
            final Issue[] all = provider.findAll();
            for (Issue issue : all) {
                if (issue.isActive()) {
                    result.add(issue);
                }
            }
        }
        return result;
    }

}

