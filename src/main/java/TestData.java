import java.sql.*;

public class TestData {

    //использовал для тестирования
    private String URL = "jdbc:mysql://localhost:3306/testing";
    private String LOGIN = "admin";
    private String PASSWORD = "password";

    private Connection connection;

    public TestData(String URL, String LOGIN, String PASSWORD) throws SQLException {
        this.URL = URL;
        this.LOGIN = LOGIN;
        this.PASSWORD = PASSWORD;
        connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        TestData testData = new TestData("jdbc:mysql://localhost:3306/testing", "admin", "password");
        testData.getUserData("user");

        for (String s :testData.getUserData("admin")) {
            System.out.println(s);
        }
    }

    public String[] getUserData(String role) throws SQLException {
        String sql = "SELECT * FROM peoples WHERE role = ?";
        PreparedStatement st = connection.prepareStatement(sql, 1);
        st.setString(1, role);
        ResultSet rs = st.executeQuery();

        rs.next();
        return new String[]{rs.getString("login"), rs.getString("password")};
    }
}
