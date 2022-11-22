package swu.edu.cn.db.db;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBEngine {

    private static DBEngine Instance = new DBEngine();
    private BasicDataSource dataSource = null;
    //Visitor 模式

    private DBEngine(){

    }

    public static DBEngine getGetInstance(){
        return Instance;
    }

    private BasicDataSource getDataSource(){
        if(this.dataSource == null){
            BasicDataSource ds = new BasicDataSource();
            ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
            ds.setUrl("jdbc:mysql://localhost:3306/book");
            ds.setUsername("root");;
            ds.setPassword("slc030415");

            //设置其他功能
            ds.setInitialSize(10);
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

    //泛型
    public <T> List<T> query(String sql, RecordVisitor<T> visitor) throws SQLException {
        List<T> result = new ArrayList<>();
        try(Connection connection = this.getDataSource().getConnection()){
            try(Statement statement = connection.createStatement()){
                try(ResultSet resultSet = statement.executeQuery(sql)) {
                    while (resultSet.next()){
                        //Visitor 模式
                        T obj = visitor.visit(resultSet);
                        result.add(obj);
                    }
                    statement.execute(sql);
                }
            }
        }
        return result;
    }

}
