package org.noname.dao;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.noname.entities.EmployeeSimple;
import org.noname.entities.SearchResult;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;

import static org.noname.html.Util.encode;
import static org.noname.html.Util.wrapEx;

@Component
public class EmployeesDao {

    public List<EmployeeSimple> getAllEmployees(String term, int area) {
        String str = encode(term);
        final String urlStr = String.format("https://hh.ru/search/resume?area=%d&clusters=true&exp_period=all_time&logic=normal&pos=full_text&from=employer_index_header&text=%s&from=suggest_post",
                area, str);
        URL url =  wrapEx(() -> new URL(urlStr));
        Document document = wrapEx(() -> Jsoup.parse(url, 10 * 1000));
        SearchResult searchResult = new SearchResult(document);
        return searchResult.getEmployees();
    }


}
