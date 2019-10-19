package org.noname.entities;

import org.noname.html.HtmlCollection;

import java.util.List;

public class SearchResult {

    @HtmlCollection(attrName = "itemtype", attrValue = "http://schema.org/Person", collectionElementType = EmployeeSimple.class)
    private List<EmployeeSimple> employees;


    public List<EmployeeSimple> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeSimple> employees) {
        this.employees = employees;
    }
}
