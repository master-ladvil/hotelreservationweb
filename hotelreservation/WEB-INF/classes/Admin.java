import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.net.http.HttpResponse;
import java.sql.*;


public class Admin extends HttpServlet{
	public Connection con = null;

	public void service(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		try {
			response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
			HttpSession session = request.getSession();
			session.setAttribute("username","admin"); 
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
		showRooms(response);
	}

	public void showRooms(HttpServletResponse response){
		Statement stmt;
		ResultSet rs = null;
		try {
			String query = String.format(
					"SELECT room.id,capacity,rtype,price,isavailablle FROM room JOIN capacity ON room.cid=capacity.id JOIN rtype ON room.tid = rtype.id order by room.id asc;");
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<head>");
			out.println("<body>");
			out.println("<h1>All rooms</h1>");
			out.println("<table>");
			out.println("<tr>");
			out.println("<td><b>roomno</b></td>");
			out.println("<td><b>capacity</b></td>");
			out.println("<td><b>roomtype</b></td>");
			out.println("<td><b>price</b></td>");
			out.println("<td><b>available?</b></td>");
			while (rs.next()) {
				out.println("<tr>");
				out.print("<td>" + rs.getString("id") + "</td>");
				out.print("<td>" + rs.getString("capacity") + "</td>");
				out.print("<td>" + rs.getString("rtype") + "</td>");
				out.println("<td>" + rs.getString("price") + "</td>");
				out.println("<td>" + rs.getString("isavailablle") + "</td>");
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("<h1>Add Room</h1>");
			out.println("<form method='Get' action=\"AdminFunctions\">");
			out.println("<table>");
			out.println("<tr>");
			out.println("<td><h2>capacity</td></h2></tr>");
			out.println("<select name=\"capacity\"><option value=\"1\">1</option><option value=\"2\">2</option><option value=\"4\">4</option><option value=\"6\">6</option></select>");
			out.println("<select name=\"rtype\"><option value=\"suite\">suite</option><option value=\"basic\">basic</option><option value=\"villa\">villa</option></select></tr>");
			out.println("<tr><td><p>Price</p></tr></td><tr><td><input type = \"text\" value = \"0.00\" name = \"price\"></tr></td>");
			out.println("<tr><td><input type = 'submit' value = 'addroom'></td></tr>");
			out.println("</table></form></body></html>");
			out.println("</body>");out.println("</html>");
	}catch(Exception e){
		System.out.println(e);
	}
}


}