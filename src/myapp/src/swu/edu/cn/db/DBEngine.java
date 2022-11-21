package swu.edu.cn.db;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBEngine {
//    单链模式
    private static DBEngine instance = new DBEngine();
    private BasicDataSource dataSource = null;

//    visitor 模式
    public interface RecordVisitor<T>{
        public T visit(ResultSet rs);
    }

    private DBEngine(){

    }

    public static DBEngine getInstance(){
        return instance;
    }
//    单链模式
    private BasicDataSource getDataSource(){
        if(this.dataSource == null){
            BasicDataSource ds = new BasicDataSource();
            ds.setDriverClassName("com.mysql.jdbc.Driver");
            ds.setUrl("jdbc:mysql://localhost:3306/book");
            ds.setUsername("root");
            ds.setPassword("slc030415");

            ds.setInitialSize(3);
            ds.setMaxIdle(4);

            this.dataSource = ds;
        }
        return this.dataSource;
    }

    public void execute(String sql) throws SQLException {
        try(Connection connection = this.getDataSource().getConnection()){
            try(Statement statement = connection.createStatement()){
                statement.execute(sql);
            }
        }
    }
//  泛型
    public <T> List<T> query(String sql,RecordVisitor<T> visitor) throws SQLException {
        List<T> result = new ArrayList<>();
        try(Connection connection = this.getDataSource().getConnection()){
            try(Statement statement = connection.createStatement()){
                try(ResultSet resultSet = statement.executeQuery(sql)){
                    while(resultSet.next()){
//                        T obj = Visitor.visit();
                    }
                }
            }
        }
        return result;
    }
}
