package org.maxur.commons.view.conf;

import com.google.inject.servlet.ServletModule;
import org.apache.wicket.protocol.http.WicketFilter;

import java.util.HashMap;
import java.util.Map;

/**
 * The binding and configuration of Wicket happens in WebModule.
 *
 * @author Maxim Yunusov
 * @version 1.0 28.09.11
 */
public class WebModule extends ServletModule {

    @Override
    protected void configureServlets() {
        Map<String, String> params = new HashMap<>();
        params.put(WicketFilter.FILTER_MAPPING_PARAM, "/app/*"); /// TODO It must be property value.

        filter("/*").through(WicketGuiceFilter.class, params);

        //If you use WARP, use a pattern to exclude the resources from the PersistenceFilter : //TODO
        //filterRegex("/*^(?!.*(swf|jpg|jpeg|png|ico|gif|css|js)).*$").through(PersistenceFilter.class);
    }

}
