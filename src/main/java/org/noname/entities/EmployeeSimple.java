package org.noname.entities;

import org.noname.html.Converter;
import org.noname.html.HtmlAttribute;

public class EmployeeSimple {
    private static final String ITEMPROP = "itemprop";
    private static final String DATA_QA = "data-qa";

    @HtmlAttribute(attrName = DATA_QA, attrValue = "resume-serp__resume-age", converter = Converter.INT)
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
