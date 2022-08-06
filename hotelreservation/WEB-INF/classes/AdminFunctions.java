import java.io.IOException;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.text.*;

public class AdminFunctions extends HttpServlet {
	public Connection con = null;
	public static String uname = null;
	public static String mobile = null;
    

	public AdminFunctions() {
		try {
            //HttpSession session =  request.getSession();
            
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

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        int s = checksession(response,request);
        if(s == 0){
            response.sendRedirect("index.jsp");
        }
        int flag =0;
        String capacity = request.getParameter("capacity");
		String rtype = request.getParameter("rtype");
		String price = request.getParameter("price");
		String isavailablle = "true";
        String cid = null;
        String tid = null;
		cid = getid("capacity","capacity",capacity);
        tid = getid("rtype","rtype",rtype);
        flag = addRoom(cid,tid,price,isavailablle);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if(flag ==1){
            request.getRequestDispatcher("/roomadded.jsp").include(request, response);
        }
        else{
            out.print("problem in adding the room");
        }
    

    }
    public String getid(String tname,String colname, String value){
        String id = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            String query = String.format("select id from %s where %s = '%s';",tname,colname,value);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            if(rs.next()){
                id = rs.getString("id");
            }else{System.out.println("id not found");}

        }catch(Exception e){System.out.println(e);}
        return id;
    }
    public int addRoom(String cid,String tid,String price,String isavailablle){
        
        Statement stmt = null;
        try{
            String query = String.format("insert into room(cid,tid,price,isavailablle) values(%s,%s,%s,%s);",cid,tid,price,isavailablle);
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("room added");
            return 1;
        }catch(Exception e){
            System.out.println("Couldnt add room");
            System.out.println(e);
        }
        return 0;
    }
    public int checksession(HttpServletResponse response,HttpServletRequest request)throws ServletException,IOException{
        HttpSession tsession = request.getSession();
        if(tsession.getAttribute("username") == null){
            return 0;
        }
        String tsessionname = (String) tsession.getAttribute("username");
        System.out.println(tsessionname);
        System.out.println(tsessionname.equals("admin"));
        if(tsessionname.equals("admin")){
            return 1;
        }
        return 0;
    }
        
        

}

