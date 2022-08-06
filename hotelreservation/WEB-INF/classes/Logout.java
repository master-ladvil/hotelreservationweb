import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;

public class Logout extends HttpServlet{
public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
	response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	HttpSession session = request.getSession();
	String name = (String)session.getAttribute("username");
	System.out.println(name);
	session.removeAttribute("username");
	String rname = (String)session.getAttribute("username");
	System.out.println(rname);
	session.invalidate();
	response.sendRedirect("index.jsp");
}
}