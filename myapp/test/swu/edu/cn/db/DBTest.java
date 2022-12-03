package swu.edu.cn.db;

import swu.edu.cn.db.book.Book;
import swu.edu.cn.db.db.DBEngine;
import swu.edu.cn.db.db.RecordVisitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBTest {
    public static void main(String[] args) throws SQLException {
        DBEngine engine = DBEngine.getGetInstance();
        String sql = "SELECT `id`, `name`, `author`, `price`, `describe` from book";

        List<Book> books = engine.query(sql, new RecordVisitor<Book>() {
            @Override
            public Book visit(ResultSet rs) throws SQLException {
                Book book  = new Book();
                book.setId(rs.getLong("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setPrice(rs.getFloat("price"));
                book.setDescribe(rs.getString("describe"));

                return book;
            }
        });

        for (Book book:books){
            System.out.println(book.toString());
        }
    }

}
