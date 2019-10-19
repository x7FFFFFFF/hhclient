package org.noname.client;

import org.noname.entities.Employee;

import java.util.ArrayList;
import java.util.List;

public class HHPage implements Page<Employee> {
    private List<Employee> list = new ArrayList<>();
    private int number;
    private String url;

    public HHPage(List<Employee> list, int number, String url) {
        this.list = list;
        this.number = number;
        this.url = url;
    }

    @Override
    public List<Employee> getList() {
        return list;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String getUrl() {
        return url;
    }
}
