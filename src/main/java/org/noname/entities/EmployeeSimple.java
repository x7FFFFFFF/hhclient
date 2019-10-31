package org.noname.entities;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.noname.html.Util;

public class EmployeeSimple {
    private static final String ITEMPROP = "itemprop";
    private static final String DATA_QA = "data-qa";

    private final ResumeLink resumeLink;
    private final int age;
    private final Money salary;

    public EmployeeSimple(ResumeLink resumeLink, int age, Money salary) {
        this.resumeLink = resumeLink;
        this.age = age;
        this.salary = salary;
    }

    public EmployeeSimple(Element node) {
        Element element = Util.requireOneElement(node.getElementsByAttributeValue("itemprop", "jobTitle"));
        resumeLink = new ResumeLink(element);
        Elements ageElements = node.getElementsByAttributeValue(DATA_QA, "resume-serp__resume-age");
        age = parseAge(Util.requireOneElement(ageElements));
        salary = parseSalary(node);
    }

    private Money parseSalary(Element node) {
        Element element = Util.requireOneElement(node.getElementsByClass("resume-search-item__compensation"));
        return new Money(element);

    }

    private int parseAge(Element element) {
        String text = element.text();
        if (text.isEmpty()) {
            return 0;
        }
        return Integer.valueOf(firsStr(text));


    }

    private String firsStr(String text) {
        return text.split(" ")[0];
    }

    public ResumeLink getResumeLink() {
        return resumeLink;
    }

    public int getAge() {
        return age;
    }

    public Money getSalary() {
        return salary;
    }
}
