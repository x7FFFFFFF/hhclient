package org.noname.html;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface HtmlAttribute {


    String attrName();

    String attrValue();

    String valFromAttr() default "";

    Converter converter() default Converter.NONE;


}
