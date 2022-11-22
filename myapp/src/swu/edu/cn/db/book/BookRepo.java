package swu.edu.cn.db.book;

import swu.edu.cn.db.db.DBEngine;
import swu.edu.cn.db.db.RecordVisitor;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class BookRepo {
    private static BookRepo instance = new BookRepo();

    public static BookRepo getBookRepo(){
        return instance;
    }

    public void save(Book book) throws SQLException {
        String template = "INSERT INTO `book`(`name`, `author`, `price`, `describe`) " +
                " VALUES ('%s', '%s', %s, '%s')";
        String sql = String.format(template,book.getName(),book.getAuthor(),book.getPrice(),book.getDescribe());
//        System.out.println(sql);
        DBEngine.getGetInstance().execute(sql);

    }


    public List<Book> getAllBook() throws SQLException {
        String sql = "SELECT * FROM book";
        return DBEngine.getGetInstance().query(sql, new RecordVisitor<Book>() {
            @Override
            public Book visit(ResultSet rs) throws SQLException {
                Book book = new Book();
                book.setId(rs.getLong("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setPrice(rs.getFloat("price"));
                book.setDescribe(rs.getString("describe"));
                return book;

            }
        });
    }

    public void deleteBookById(Long id) throws SQLException {
        String template = "DELETE FROM `book` WHERE `id` = %s";
        DBEngine.getGetInstance().execute(String.format(template,id));
    }


}
