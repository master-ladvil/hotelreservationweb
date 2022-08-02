
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	public static String name;
	public static String mobile;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Register.name = request.getParameter("uname");
		Register.mobile = request.getParameter("mobile");
		Statement stmt;
		try {
			String query = String.format("insert into client(fullname,mobile) values('%s','%s');", Register.name,
					Register.mobile);
			stmt = con.createStatement();
			stmt.executeUpdate(query);
			System.out.println("added succesfully...");
		} catch (Exception e) {
			System.out.println(e);
		}
		response.sendRedirect("login.jsp");
	}

}
