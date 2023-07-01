import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySqlService extends DBconfig implements IDataBaseService{

    private static Connection conn;
    private static ResultSet rs;


    public Connection getConn() {
        return conn;
    }


    public ResultSet getRs() {
        return rs;
    }


    public MySqlService() {


        super();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Bulunamadı....");
        }
    }

    @Override
    public void connect() {

        String url = "jdbc:mysql://" + this.getHost() + ":"+3306+"/" + this.getDb() + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8";
        try {
            this.conn = DriverManager.getConnection(url, this.getUser(), this.getPassword());
        }
        catch (SQLException ex) {
            System.out.println("Bağlantı Başarısız...");
            ex.printStackTrace();
        }
    }

    @Override
    public void read(String sorgu) {
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sorgu);
        } catch (SQLException ex) {
            Logger.getLogger(MySqlService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void databasexecute(String sorgu) {
        Statement statement = null;
        try {
            statement = this.conn.createStatement();
            statement.executeUpdate(sorgu);
        } catch (SQLException ex) {
            Logger.getLogger(MySqlService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void connectclose() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
