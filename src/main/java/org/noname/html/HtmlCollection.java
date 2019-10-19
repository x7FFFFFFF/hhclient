package org.noname.html;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface HtmlCollection {
    String attrName();

    String attrValue();

    Class<?> collectionElementType();
}
