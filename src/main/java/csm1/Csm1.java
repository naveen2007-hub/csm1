package csm1;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


@WebServlet(urlPatterns = {"/Csm1","/reg"},	initParams = {
		@WebInitParam(name = "username", value="root", description="Database user"),
		@WebInitParam(name = "password", value="root", description="Database pasword")
})
public class Csm1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Cookie c= new Cookie("username","root");
//		Cookie c1= new Cookie("password","root");
//		response.addCookie(c);
//		response.addCookie(c1);
//		ServletContext sc= request.getServletContext();
//		sc.setAttribute("username","naveen");
//		sc.setAttribute("password","1432");
//		String s1=(String) sc.getAttribute("username");
//		String s2=(String) sc.getAttribute("password");
//		response.getWriter().println(s1+ " " +s2);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletConfig config = getServletConfig();
		String user= config.getInitParameter("username");
		String pass= config.getInitParameter("password");
		String path = request.getServletPath();
		if(path.equals("/reg")){
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qis", user, pass);
				int id = Integer.parseInt(request.getParameter("id"));
				String name = request.getParameter("name");
				String query = "insert into student values(?,?)";
				PreparedStatement pst =con.prepareStatement(query);
				pst.setInt(1, id);
				pst.setString(2, name);
				int row = pst.executeUpdate();
				if(row>=1) {
					RequestDispatcher rs = request.getRequestDispatcher("lo.html");
					rs.forward(request, response);
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
		else if(path.equals("/log")) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qis", user, pass);
				int id =  Integer.parseInt(request.getParameter("i"));
				String name = request.getParameter("n");
				
				
			}catch(Exception e) {
				e.printStackTrace(); 
			}
			
		}
	}

}
