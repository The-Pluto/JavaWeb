package src.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface RecordVisitor<T> {

    public T visit(ResultSet rs) throws SQLException;
}
