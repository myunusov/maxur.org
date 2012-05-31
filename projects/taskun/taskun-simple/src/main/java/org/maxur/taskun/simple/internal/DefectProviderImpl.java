package org.maxur.taskun.simple.internal;

import org.maxur.taskun.domain.BaseDefect;
import org.maxur.taskun.domain.Issue;
import org.maxur.taskun.domain.IssueProvider;

/**
 * @author Maxim Yunusov
 * @version 1.0 20.05.12
 */
public class DefectProviderImpl implements IssueProvider {

    @Override
    public Issue[] findAll() {
        final Issue[] issues = new Issue[2];
        issues[0] = new BaseDefect("Invalid ErrorPage on 'not found' event");
        issues[1] = new BaseDefect("NullPointer on restart felix :" +
                "ERROR org.ops4j.pax.web.extender.war.internal.WebXmlObserver Could not parse web.xml");
        return issues;
    }

}

