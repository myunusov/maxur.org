package org.maxur.commons.view.conf;

import com.google.inject.servlet.ServletModule;
import org.apache.wicket.protocol.http.WicketFilter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Maxim Yunusov
 * @version 1.0 28.09.11
 */
public class WebModule extends ServletModule {

    @Override
    protected void configureServlets() {
        Map<String, String> params = new HashMap<String, String>();
        params.put(WicketFilter.FILTER_MAPPING_PARAM, "/app/*");

        filter("/*").through(WicketGuiceFilter.class, params);
    }

}
