package org.maxur.adapter.kickstrap;

import org.apache.wicket.markup.MarkupStream;

import java.io.Serializable;

/**
 * @author Maxim Yunusov
 * @version 1.0 31.05.12
 */
public interface HTMLTagModifier extends Serializable {
    void updateHTMLTag(MarkupStream markupStream);
}
