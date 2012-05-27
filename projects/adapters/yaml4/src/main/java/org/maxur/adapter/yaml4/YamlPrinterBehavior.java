package org.maxur.adapter.yaml4;

import com.google.inject.Inject;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.request.resource.CssResourceReference;
import org.maxur.commons.component.behavior.BaseResourcesBehavior;
import org.maxur.commons.component.model.webclient.WebBrowserDetector;
import org.maxur.commons.view.api.StyleBehavior;

/**
 * Import print layout.
 *
 * @author Maxim Yunusov
 * @version 1.0 07.05.12
 */
public class YamlPrinterBehavior extends BaseResourcesBehavior implements StyleBehavior {

    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = -1362412483333201738L;

    @Inject
    private WebBrowserDetector detector;

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);

        response.render(CssHeaderItem.forReference(
                new CssResourceReference(this.getClass(), "/print/print.css")
        ));
    }

}

/*

@import url(../../../resources/org/maxur/adapter/yaml/forms/gray-theme.css);

*/
