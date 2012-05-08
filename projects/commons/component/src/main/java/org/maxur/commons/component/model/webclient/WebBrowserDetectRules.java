package org.maxur.commons.component.model.webclient;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
* @author Maxim Yunusov
* @version 1.0 07.05.12
*/
final class WebBrowserDetectRules {

    private static final String USER_AGENT = "user-agent";

    final List<WebBrowserDetectRule> rules = new ArrayList<>();

    private WebBrowserDetectRule currentRule;

    public WebBrowserDetectRules add() {
        this.currentRule = new WebBrowserDetectRule();
        rules.add(this.currentRule);
        return this;
    }

    public WebBrowserDetectRules on(final String id) {
        this.currentRule.setKey(id);
        return this;
    }

    public WebBrowserDetectRules set(final WebBrowserType agent) {
        this.currentRule.setAgent(agent);
        return this;
    }

    public WebBrowserDetectRules withVersion() {
        currentRule.addVersion();
        return this;
    }

    public WebBrowserDetectRules after(final String versionKey) {
        currentRule.versionAfterKey(versionKey);
        return this;
    }

    public WebBrowserDetectRules until(final String separator) {
        currentRule.versionSeparator(separator);
        return this;
    }

    public WebBrowser detect(final HttpServletRequest request) {
        final String userAgentString = request.getHeader(USER_AGENT);
        if (isNotBlank(userAgentString)) {
            for (WebBrowserDetectRule rule : rules) {
                if (rule.isApplicable(userAgentString)) {
                    return new WebBrowserBase(rule.getAgent(), rule.getVersionBy(userAgentString));
                }
            }
        }
        return (new WebBrowserBase(WebBrowserType.UNKNOWN, ""));
    }

    private static boolean isNotBlank(final String userAgentString) {
        return userAgentString != null && userAgentString.length() > 0;
    }

    private static final class WebBrowserDetectRule {

        private String key;

        private String versionKey;

        private WebBrowserType agent;

        private String versionSeparator;

        void setKey(final String key) {
            this.key = key;
        }

        public void setAgent(final WebBrowserType agent) {
            this.agent = agent;
        }

        public WebBrowserType getAgent() {
            return agent;
        }

        public boolean isApplicable(final String userAgentString) {
            return userAgentString.contains(this.key);
        }

        public void addVersion() {
            this.versionKey = this.key;
        }

        public void versionAfterKey(final String versionKey) {
            this.versionKey = versionKey;
        }

        public void versionSeparator(final String separator) {
            versionSeparator = separator;
        }

        public String getVersionBy(String userAgentString) {
            String ver = userAgentString.substring(userAgentString.indexOf(versionKey) + versionKey.length());
            return  (ver.indexOf(versionSeparator) > 0 ? ver.substring(0, ver.indexOf(versionSeparator)) : ver).trim();
        }
    }

}
