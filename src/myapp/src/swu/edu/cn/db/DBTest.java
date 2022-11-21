package swu.edu.cn.db;

import swu.edu.cn.book.Book;

import java.sql.SQLException;

public class DBTest {
    public static void main(String[] args) throws SQLException{
        DBEngine dbEngine = DBEngine.getInstance();
        String sql2 = "select id,name,author,price,describe rom book";
        String sql = "delete from book where id = 1";
        dbEngine.execute(sql);
        dbEngine.query(sql, new DBEngine.RecordVisitor<Book>() {){
            @Override
            public Book visitor(Result rs){
                Book book = new Book();

            }
        }
    }
}
