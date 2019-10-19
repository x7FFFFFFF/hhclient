package org.noname.client;

import java.util.List;

public interface Page<T> {

    List<T> getList();

    int getNumber();

    void setNumber(int num);

    String getUrl();

}
