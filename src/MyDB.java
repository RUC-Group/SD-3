import java.sql.*;
import java.util.ArrayList;

class MyDB {
    Connection conn = null;

    MyDB() {
        if (conn == null) open();
    }

    public void open() {
        try {
            String url = "jdbc:sqlite:listdb.db";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("cannot open");
            if (conn != null) close();
        }
        ;
    }

    public void close() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("cannot close");
        }
        conn = null;
    }

    public void cmd(String sql) {
        if (conn == null) open();
        if (conn == null) {
            System.out.println("No connection");
            return;
        }
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error in statement " + sql);
        }
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            System.out.println("Error in statement " + sql);
        }
    }

    public ArrayList<String> query(String query, String fld) {
        ArrayList<String> res = new ArrayList<>();
        if (conn == null) open();
        if (conn == null) {
            System.out.println("No connection");
            return res;
        }
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString(fld);
                res.add(name);
            }
        } catch (SQLException e) {
            System.out.println("Error in statement " + query + " " + fld);
        }
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            System.out.println("Error in statement " + query + " " + fld);
        }
        return res;
    }
    public ArrayList<String> query(String query, String[] fld) {
        ArrayList<String> res = new ArrayList<>();
        if (conn == null) open();
        if (conn == null) {
            System.out.println("No connection");
            return res;
        }
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                for (int i = 0; i < fld.length; i++) {
                    String name = rs.getString(fld[i]);
                    res.add(name);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error in statement " + query + " " + fld);
        }
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            System.out.println("Error in statement " + query + " " + fld);
        }
        return res;
    }
}

