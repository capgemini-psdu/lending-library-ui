package app.book;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter // All fields are private and final. Getters (but not setters) are generated (https://projectlombok.org/features/Value.html)
@Setter
@RequiredArgsConstructor
public class Book {
    String title;
    String author;
    String isbn;

    public String getMediumCover() {
        return "http://covers.openlibrary.org/b/isbn/" + this.isbn + "-M.jpg";
    }

    public String getLargeCover() {
        return "http://covers.openlibrary.org/b/isbn/" + this.isbn + "-L.jpg";
    }
}
