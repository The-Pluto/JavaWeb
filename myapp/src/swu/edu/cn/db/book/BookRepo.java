package swu.edu.cn.db.book;

import swu.edu.cn.db.db.DBEngine;
import swu.edu.cn.db.db.RecordVisitor;

import java.sql.*;
import java.util.List;

public class BookRepo {
    private static BookRepo instance = new BookRepo();

    public static BookRepo getBookRepo(){
        return instance;
    }

    public void saveBook(Book book) throws SQLException {
        if(book.getId() == 0){
            this.InsertBook(book);
        }
        else{
            this.editBookById(book);
        }
    }
    private void InsertBook(Book book) throws SQLException {
        String template = "INSERT INTO `book`(`name`, `author`, `price`, `describe`) " +
                " VALUES ('%s', '%s', %s, '%s')";
        String sql = String.format(template,book.getName(),book.getAuthor(),book.getPrice(),book.getDescribe());
//        System.out.println(sql);
        DBEngine.getGetInstance().execute(sql);
    }

    private void editBookById(Book book) throws SQLException {
        String template = "UPDATE `book` SET `name` = \"%s\", `author` = \"%s\", `price` = %s, `describe` = \"%s\" " +
                "WHERE `id` = %s";
        String sql = String.format(template,book.getName(),book.getAuthor(),book.getPrice(),book.getDescribe(),book.getId());
        System.out.println(sql);
        DBEngine.getGetInstance().execute(sql);
    }

    public List<Book> getAllBook() throws SQLException {
        String sql = "SELECT * FROM book";
        return DBEngine.getGetInstance().query(sql, new RecordVisitor<Book>() {
            @Override
            public Book visit(ResultSet rs) throws SQLException {
                return BookRepo.getBookRepo().getBookFromResultSet(rs);
            }
        });
    }

    public void deleteBookById(Long id) throws SQLException {
        String template = "DELETE FROM `book` WHERE `id` = %s";
        DBEngine.getGetInstance().execute(String.format(template,id));
    }

    public Book getById(String id) throws SQLException {
        String template = "SELECT * FROM `book` WHERE `id` = %s";
        String sql = String.format(template,id);
        List<Book> books = DBEngine.getGetInstance().query(sql, new RecordVisitor<Book>() {
            @Override
            public Book visit(ResultSet rs) throws SQLException {
                return BookRepo.getBookRepo().getBookFromResultSet(rs);
            }
        });
        return books.size() == 0 ? null : books.get(0);

    }

    private Book getBookFromResultSet(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setName(rs.getString("name"));
        book.setAuthor(rs.getString("author"));
        book.setPrice(rs.getFloat("price"));
        book.setDescribe(rs.getString("describe"));
        return book;
    }



}
