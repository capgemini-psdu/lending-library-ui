package app.book;

import java.io.IOException;
import java.net.ConnectException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.fluent.Request;

import com.google.common.collect.Lists;

import app.util.JsonUtil;

public class BookDao {

    private String envVarApiLocation = System.getenv("API_LOCATION");
    
    public Iterable<Book> getAllBooks() throws ConnectException {
        String json = null;
        try {
            String apiLocation = StringUtils.isEmpty(envVarApiLocation) ? "localhost:5678" : envVarApiLocation;
            json = Request.Get(String.format("http://%s/books", apiLocation))
                .setHeader("Accept", "application/json")
                .connectTimeout(1000)
                .socketTimeout(1000)
                .execute().returnContent().asString();
        } catch (ConnectException e) {
            throw e;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return JsonUtil.JsonToBookList(json);
    }

    public Book getBookByIsbn(String isbn) throws ConnectException {
        List<Book> books = Lists.newArrayList(getAllBooks());        
        return books.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst().orElse(null);
    }

}
