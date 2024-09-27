

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ragistraition
 */
public class Ragistraition extends HttpServlet {
	Connection con;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ragistraition() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		String s1=config.getInitParameter("driver");
		String s2=config.getInitParameter("url");
		String s3=config.getInitParameter("username");
		String s4=config.getInitParameter("password");
		try {
			Class.forName(s1);
			con=DriverManager.getConnection(s2,s3,s4);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			// TODO Auto-generated method stub
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String s1=request.getParameter("fname");
		String s2=request.getParameter("lname");
		String s3=request.getParameter("uname");
		String s4=request.getParameter("pword");
		
		PrintWriter pw=response.getWriter();
		
		try {
			PreparedStatement psmt=con.prepareStatement("INSERT INTO registration (fname, uname, username, password) VALUES (?, ?, ?, ?)");
			psmt.setString(1, s1);
			psmt.setString(2, s2);
			psmt.setString(3, s3);
			psmt.setString(4, s4);
			psmt.executeUpdate();
			
			pw.print("<html><body>");
			pw.print("<h1>You have Successfuly Regesterd</h1>");
            pw.print("<html><body ><a href=login.html>Login</a></body></html>");
			pw.print("</body></html>");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
