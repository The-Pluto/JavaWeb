package swu.edu.cn.book;

import java.sql.*;
import java.util.*;

public class DBUtils {

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/book",
                "root",
                "slc030415"
        );
        return connection;
    }

    public static void saveBook(Book book) throws SQLException {
        String template = "INSERT INTO `book`.`book` (`name`, `author`, `price`, `describe`) VALUES ('%s', '%s', %s, '%s')";
        try(Connection connection = getConnection()){
            try(Statement statement = connection.createStatement()){
                String sql = String.format(template, book.getName(), book.getAuthor(), book.getPrice(), book.getDescribe());
                System.out.println(sql);
                statement.execute(sql);
            }
        }

    }

    public static List<Book> getallBook() throws SQLException {
        String sql = "SELECT * FROM book";

        List<Book> books = new ArrayList<>();;
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sql)) {

                    while (resultSet.next()) {
                        long id = resultSet.getLong("id");
                        String name = resultSet.getString("name");
                        String author = resultSet.getString("author");
                        float price = resultSet.getFloat("price");
                        String describe = resultSet.getString("describe");

                        Book book = new Book(id, name, author, price, describe);
                        books.add(book);
                    }
                }
            }
        }
        return books;
    }
}
