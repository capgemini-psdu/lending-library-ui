package app.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import app.book.Book;

public class JsonUtil {

    public static List<Book> JsonToBookList(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Book> books = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Book.class));
            return books;
        } catch (IOException e) {
            throw new RuntimeException("IOEXception while mapping JSON (" + json + ") to list of Book objects", e);
        }
    }

    public static Book JsonToBook(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Book book = mapper.readValue(json, Book.class);
            return book;
        } catch (IOException e) {
            throw new RuntimeException("IOEXception while mapping JSON (" + json + ") to Book object", e);
        }
    }
}