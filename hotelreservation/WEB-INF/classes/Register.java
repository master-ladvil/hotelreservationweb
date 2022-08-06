
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class Register extends HttpServlet {
	Connection con = null;
	public static String name;
	public static String mobile;

	public Register() {

		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hotelreserve1_0", "postgres", "pwd");
			if (con != null) {
				System.out.println("connection estabished");
			} else {
				System.out.println("Connection failed");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Register.name = request.getParameter("uname");
		Register.mobile = request.getParameter("mobile");
		Statement stmt;
		if(name == "" || mobile ==""){
			response.sendRedirect("error.jsp");
		}
		try {
			String query = String.format("insert into client(fullname,mobile) values('%s','%s');", Register.name,
					Register.mobile);
			stmt = con.createStatement();
			stmt.executeUpdate(query);
			System.out.println("user added succesfully...");
		} catch (SQLException e) {
			System.out.println(e);
			response.sendRedirect("error.jsp");
		}
		response.sendRedirect("login.jsp");
	}

}
