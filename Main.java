import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args)throws Exception {

        //System.out.println("KKK");
        getConnection();
        //get("parcari");
        post("2", "3", "0");
        getLocuri();
    }


    public static void getParcari() throws Exception{
        try{
            Connection conn = getConnection();
            PreparedStatement posted = conn.prepareStatement("SELECT * FROM parcari");

            ResultSet result = posted.executeQuery();
            ArrayList<String> arr = new ArrayList<>();
            while(result.next()){
                System.out.print("parcare_id: " + result.getString("parcare_id") + " ");
                System.out.print("nume: " + result.getString("nume") + " ");
                System.out.print("adresa: " + result.getString("adresa") + " ");
                System.out.println("nr locuri: " + result.getString("numar_locuri"));
                //arr.add(res)
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    public static void getLocuri() throws Exception{
        try{
            Connection conn = getConnection();
            PreparedStatement posted = conn.prepareStatement("SELECT * FROM locuri");

            ResultSet result = posted.executeQuery();
            ArrayList<String> arr = new ArrayList<>();
            while(result.next()){
                System.out.print(result.getString("loc_id") + " ");
                System.out.print(" din parcarea " + result.getString("parcare_id") + " ");
                System.out.println(result.getString("ocupat"));
                //arr.add(res)
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void post(String id1, String id2, String valid) throws Exception{
        final String loc_id = id1;
        final String parcare_id = id2;
        final String ocupat = valid;
        try{
            Connection conn = getConnection();
            PreparedStatement posted = conn.prepareStatement("INSERT INTO locuri (loc_id, parcare_id, ocupat) VALUES ('"+loc_id+"','"+parcare_id+"', '"+ocupat+"')");
            posted.executeUpdate();

        }catch(Exception e) {
            System.out.println(e);
        }
    }
    public static Connection getConnection() throws Exception{
        try {
            String name = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/database_name";
            String username = "vlad";
            String password = "";
            Class.forName(name);
            Connection conn = DriverManager.getConnection(url, username, password);
            //System.out.println("connected");
            return conn;
        }catch(Exception e) {
            System.out.println(e);

        }
        return null;
    }
}