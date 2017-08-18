package app.book;

import static app.Application.bookDao;
import static app.util.RequestUtil.clientAcceptsHtml;
import static app.util.RequestUtil.getParamIsbn;

import java.net.ConnectException;
import java.util.HashMap;

import app.signin.SigninController;
import app.util.Path;
import app.util.ViewUtil;
import spark.Request;
import spark.Response;
import spark.Route;

public class BookController {

    public static Route fetchAllBooks = (Request request, Response response) -> {
        SigninController.ensureUserIsLoggedIn(request, response);
        if (clientAcceptsHtml(request)) {
            HashMap<String, Object> model = new HashMap<>();
            Iterable<Book> books = null;
            try {
                books = bookDao.getAllBooks();            
            } catch (ConnectException e) {
                return ViewUtil.badGateway.handle(request, response);
            }
            model.put("books", books);
            return ViewUtil.render(request, model, Path.Template.BOOKS_ALL);
        }
        return ViewUtil.notAcceptable.handle(request, response);
    };

    public static Route fetchOneBook = (Request request, Response response) -> {
        SigninController.ensureUserIsLoggedIn(request, response);
        if (clientAcceptsHtml(request)) {
            HashMap<String, Object> model = new HashMap<>();
            Book book = null;
            try {
                book = bookDao.getBookByIsbn(getParamIsbn(request));      
            } catch (ConnectException e) {
                return ViewUtil.badGateway.handle(request, response);
            }
            model.put("book", book);
            return ViewUtil.render(request, model, Path.Template.BOOKS_ONE);
        }
        return ViewUtil.notAcceptable.handle(request, response);
    };
}
