package org.noname.entities;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class SearchResult {

    private final  List<EmployeeSimple> employees = new ArrayList<>();

    private final int size;



    public SearchResult(Element node) {
        Elements elements = node.getElementsByAttributeValue("itemtype", "http://schema.org/Person");
        size = elements.size();
        for (Element element : elements) {
            employees.add(new EmployeeSimple(element));
        }
    }



    public List<EmployeeSimple> getEmployees() {
        return employees;
    }

    public int getSize() {
        return size;
    }
}
