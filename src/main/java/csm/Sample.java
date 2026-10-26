package csm;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(
		urlPatterns = { 
				"/Sample", 
				"/insert", 
				"/update",
				"/loginpage"
		}, 
		initParams = { 
				@WebInitParam(name = "username", value = "root", description = "Database user"), 
				@WebInitParam(name = "password", value = "root", description = "Database Password")
		})
public class Sample extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// cookies store the information at the client side
		// Temporary Storage
		Cookie cookie=new Cookie("language","english");
		Cookie cookie1=new Cookie("name","revanth");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		response.addCookie(cookie1);
		response.getWriter().println("Cookie is added");
		response.getWriter().println(cookie.getValue()); // used to print the value of the field 
		response.getWriter().println(cookie.getName()); // used to print the name of the field
		
		Cookie[] array=request.getCookies(); // method used to retrive the cookies
		for(Cookie i:array) {
			response.getWriter().println(i.getName()+" "+i.getValue());
			response.getWriter().println(array.length);
		}
		
		response.setContentType("text/html");
		response.getWriter().println("<h2>Hello World</h2>");
		
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=request.getServletPath();
		ServletConfig config=getServletConfig();
		
		
		
		if(path.equals("/insert")) {
			String bankname=request.getParameter("bankname");
			int accountno=Integer.parseInt(request.getParameter("accountno"));
			String accountname=request.getParameter("accountname");
			ServletConfig config1=getServletConfig();
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");				
				String url="jdbc:mysql://localhost:3306/qis";
				String username=config1.getInitParameter("username");
				String password=config1.getInitParameter("password");
				Connection con=DriverManager.getConnection(url,username,password);
				PreparedStatement st=con.prepareStatement("insert into bank values(?,?,?)");
				st.setString(1, bankname);
				st.setInt(2, accountno);
				st.setString(3, accountname);
				int rows=st.executeUpdate();
				if(rows>=1) {
					response.sendRedirect("login.html");
				}
						
			}
		
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(path.equals("/loginpage")) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/qis","root","root");
				PreparedStatement st=con.prepareStatement("select * from bank where accountno=? and accountname=?");
				int accountno=Integer.parseInt(request.getParameter("accountno"));
				String accountname=request.getParameter("accountname");
				st.setInt(1, accountno);
				st.setString(2, accountname);
				ResultSet rs=st.executeQuery();
				if(rs.next()) {
					RequestDispatcher rd=request.getRequestDispatcher("home.html");
					rd.forward(request, response);
				}
				else {
					RequestDispatcher rd=request.getRequestDispatcher("login.html");
					rd.forward(request, response);
				}
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(path.equals("/display")) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qis","root","root");
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery("select * from bank");
				response.setContentType("text/html");
				PrintWriter out=response.getWriter();
				out.println("<table>");
				out.println("<tr>");
				out.println("<th>BankName</th>");
				out.println("<th>AccountNo</th>");
				out.println("<th>AccountName</th>");
				out.println("</tr>");
				while(rs.next()) {
					out.println("<tr>");
					out.println("<td>"+rs.getString(1)+"</td>");
					out.println("<td>"+rs.getInt(2)+"</td>");
					out.println("<td>"+rs.getString(3)+"</td>");
					out.println("</tr>");
				}
				out.println("</html>");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
}
}