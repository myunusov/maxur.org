package org.maxur.taskun.simple.internal;

import org.maxur.taskun.domain.BaseTask;
import org.maxur.taskun.domain.Issue;
import org.maxur.taskun.domain.IssueProvider;

/**
 * @author Maxim Yunusov
 * @version 1.0 20.05.12
 */
public class DefectProviderImpl implements IssueProvider {

    @Override
    public Issue[] findAll() {
        final Issue[] issues = new Issue[1];
        issues[0] = new BaseTask("Resolve defect with errorPage");
        return issues;
    }

}

