package org.maxur.commons.component.model.webclient;

import org.maxur.commons.core.api.SerializableArrayList;
import org.maxur.commons.core.api.SerializableList;
import org.maxur.commons.core.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
* @author Maxim Yunusov
* @version 1.0 07.05.12
*/
final class WebBrowserDetectRules implements Serializable {

    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = -3814789251341221261L;

    /** Constant <code>USER_AGENT="user-agent"</code> */
    public static final String USER_AGENT = "user-agent";

    private final SerializableList<WebBrowserDetectRule> rules = new SerializableArrayList<>();

    /**
     * <p>Private Constructor for WebBrowserDetectRules.</p>
     *
     * A instance must be constructed with get method only.
     *
     */
    private WebBrowserDetectRules() {
    }

    /**
     * Returns instance of WebBrowserDetectRules class.
     * see 'Creators Method' pattern
     *
     * @return instance of WebBrowserDetectRules class.
     */
    public static WebBrowserDetectRules get() {
        return new WebBrowserDetectRules();
    }

    /**
     * <p>addRuleFor.</p>
     *
     * @param type a {@link org.maxur.commons.component.model.webclient.WebBrowserType} object.
     * @return a {@link org.maxur.commons.component.model.webclient.WebBrowserDetectRules} object.
     */
    public WebBrowserDetectRules addRuleFor(final WebBrowserType type) {
        rules.add(new WebBrowserDetectRule(type));
        return this;
    }

    /**
     * <p>keyString.</p>
     *
     * @param id a {@link java.lang.String} object.
     * @return a {@link org.maxur.commons.component.model.webclient.WebBrowserDetectRules} object.
     */
    public WebBrowserDetectRules keyString(final String id) {
        this.getCurrentRule().setKey(id);
        return this;
    }

    /**
     * <p>withVersion.</p>
     *
     * @return a {@link org.maxur.commons.component.model.webclient.WebBrowserDetectRules} object.
     */
    public WebBrowserDetectRules withVersion() {
        getCurrentRule().addVersion();
        return this;
    }

    /**
     * <p>after.</p>
     *
     * @param versionKey a {@link java.lang.String} object.
     * @return a {@link org.maxur.commons.component.model.webclient.WebBrowserDetectRules} object.
     */
    public WebBrowserDetectRules after(final String versionKey) {
        getCurrentRule().versionAfterKey(versionKey);
        return this;
    }

    /**
     * <p>until.</p>
     *
     * @param separator a {@link java.lang.String} object.
     * @return a {@link org.maxur.commons.component.model.webclient.WebBrowserDetectRules} object.
     */
    public WebBrowserDetectRules until(final String separator) {
        getCurrentRule().versionSeparator(separator);
        return this;
    }

    /**
     * <p>detect.</p>
     *
     * @param request a {@link javax.servlet.http.HttpServletRequest} object.
     * @return a {@link org.maxur.commons.component.model.webclient.WebBrowser} object.
     */
    public WebBrowser detect(final HttpServletRequest request) {
        final String userAgentString = request.getHeader(USER_AGENT);
        if (StringUtils.isNotBlank(userAgentString)) {
            for (WebBrowserDetectRule rule : rules) {
                if (rule.isApplicable(userAgentString)) {
                    return new BaseWebBrowser(rule.getType(), rule.getVersionBy(userAgentString));
                }
            }
        }
        return (new BaseWebBrowser(WebBrowserType.UNKNOWN, ""));
    }

    /**
     * <p>getCurrentRule.</p>
     *
     * @return a {@link org.maxur.commons.component.model.webclient.WebBrowserDetectRules.WebBrowserDetectRule} object.
     */
    public WebBrowserDetectRule getCurrentRule() {
        return rules.get(rules.size() - 1);
    }

    private static final class WebBrowserDetectRule implements Serializable {

        /**
         * The Serial Version UID.
         */
        private static final long serialVersionUID = 8267608665280048187L;

        private static final String DEFAULT_VERSION_SEPARATOR = " ";

        private String key;

        private String versionKey;

        private final WebBrowserType type;

        private String versionSeparator = DEFAULT_VERSION_SEPARATOR;

        public WebBrowserDetectRule(final WebBrowserType type) {
            this.type = type;
        }

        void setKey(final String key) {
            this.key = key;
        }

        public WebBrowserType getType() {
            return type;
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

        private boolean isVersionAvailable() {
            return StringUtils.isNotBlank(this.versionKey);
        }

        public String getVersionBy(String userAgentString) {
            if (!isVersionAvailable()) {
                return "0";
            }
            final String ver = userAgentString.substring(userAgentString.indexOf(versionKey) + versionKey.length());
            return  (ver.indexOf(versionSeparator) > 0 ? ver.substring(0, ver.indexOf(versionSeparator)) : ver).trim();
        }
    }

}
