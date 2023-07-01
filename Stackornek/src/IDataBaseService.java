import java.sql.Connection;
import java.sql.ResultSet;

public interface IDataBaseService {
    void connect();
    void read(String sorgu);
    void databasexecute(String sorgu);
    void connectclose();
    Connection getConn();
    ResultSet getRs();

}
