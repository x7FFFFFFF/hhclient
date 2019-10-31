package org.noname.entities;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class SearchResultTest {

    @Test
    public void testResult() throws IOException {
        try (InputStream is =  getClass().getClassLoader().getResourceAsStream("page0.html")) {
            Document document = Jsoup.parse(is, "UTF-8", "");
            SearchResult searchResult = new SearchResult(document);
            System.out.println("searchResult = " + searchResult);
            Assert.assertEquals(20, searchResult.getSize());
            Assert.assertEquals("Java-разработчик", searchResult.getEmployees().get(0).getResumeLink().getJobTitle());

        }
    }
}
