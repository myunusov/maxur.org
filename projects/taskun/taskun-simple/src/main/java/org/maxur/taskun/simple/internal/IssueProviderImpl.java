package org.maxur.taskun.simple.internal;

import org.maxur.taskun.domain.BaseTask;
import org.maxur.taskun.domain.Issue;
import org.maxur.taskun.domain.IssueProvider;

/**
 * @author Maxim Yunusov
 * @version 1.0 20.05.12
 */
public class IssueProviderImpl implements IssueProvider {

    @Override
    public Issue[] findAll() {
        final Issue[] issues = new Issue[6];
        issues[0] = new BaseTask("Add support for YAML 4");
        issues[1] = new BaseTask("Add AOP Instrumentation (benchmark)");
        issues[2] = new BaseTask("Add AOP Instrumentation (trace)");
        issues[3] = new BaseTask("Rewrite simple task provider without hardcode");
        issues[4] = new BaseTask("Show issues in dashboard controls");
        issues[5] = new BaseTask("Add details field to issue");
        return issues;
    }

}

