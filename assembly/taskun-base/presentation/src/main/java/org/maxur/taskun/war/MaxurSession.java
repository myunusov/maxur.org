package org.maxur.taskun.war;

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

/**
 * <p>MaxurSession class.</p>
 *
 * @author Maxim Yunusov
 * @version 1.0 14.04.12
 */
public class MaxurSession extends WebSession {

    private static final long serialVersionUID = -2944957217275470748L;

    /**
     * Constructor. Note that {@link org.apache.wicket.request.cycle.RequestCycle} is not available until this constructor returns.
     *
     * @param request The current request
     */
    public MaxurSession(Request request) {
        super(request);
    }

}
