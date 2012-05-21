package org.maxur.taskun.domain.internal;

import org.maxur.taskun.domain.Issue;
import org.maxur.taskun.domain.IssueLister;
import org.maxur.taskun.domain.IssueProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Maxim Yunusov
 * @version 1.0 20.05.12
 */
public class IssueListerImpl implements IssueLister {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Collection<IssueProvider> providers =
            Collections.synchronizedCollection(new ArrayList<IssueProvider>());

    protected void bindProvider(final IssueProvider provider) {
        providers.add(provider);
        logger.debug("Added a provider");
    }

    protected void unbindProvider(final IssueProvider provider) {
        providers.remove(provider);
        logger.debug("Removed a provider");
    }

    @Override
    public List listActive(final String director) {
        final IssueProvider[] providerArray = providers.toArray(new IssueProvider[providers.size()]);
        final List<Issue> result = new LinkedList<>();
        for (IssueProvider provider : providerArray) {
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

