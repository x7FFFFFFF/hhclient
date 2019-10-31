package org.noname.html;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

public interface HtmlEntityMapper<T> {

    T from(Element node);

}
