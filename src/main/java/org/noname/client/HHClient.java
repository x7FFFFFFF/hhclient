package org.noname.client;

import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.noname.entities.Employee;
import org.noname.entities.SearchResult;
import org.noname.html.HtmlParser;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.concurrent.TimeUnit;

@Component
public class HHClient {

    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private final Gson gson;
    public HHClient() {
        gson = new Gson();;
    }



    HHPage getPage(String search, int page, int area) throws Exception {
        HttpGet httpGet = new HttpGet(String.format("https://hh.ru/search/resume?L_is_autosearch=false&area=%d&clusters=true&exp_period=all_time&logic=normal&no_magic=false&order_by=relevance&pos=full_text&text=%s&page=%d",
                area,
                search,
                page
                ));
        try (CloseableHttpClient httpclient = HttpClientBuilder.create().setConnectionTimeToLive(2, TimeUnit.MINUTES).build();
             CloseableHttpResponse response = httpclient.execute(httpGet);
             InputStream is = response.getEntity().getContent();
        ) {

            final SearchResult parse = HtmlParser.parse(is, SearchResult.class);
            System.out.println("parse = " + parse);

        }
        return null;

    }


    public static void main(String[] args) throws Exception {
        final HHPage page = new HHClient().getPage("программист+java", 0, 1);
        System.out.println("page = " + page);
    }


}
