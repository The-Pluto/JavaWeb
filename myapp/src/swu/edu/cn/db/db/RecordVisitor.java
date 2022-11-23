package swu.edu.cn.db.db;

import swu.edu.cn.db.book.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RecordVisitor<T> {

    public T visit(ResultSet rs) throws SQLException;

}
