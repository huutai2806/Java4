package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.apache.coyote.Request;

import Servlets.DBUtils;
import Servlets.MySQLConntUtils;
import Servlets.UserAccount;
import Servlets.SignUpServlet;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at:").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			Connection conn;
			try{
				conn = MySQLConntUtils.getMySQLConnection();
				String Username = request.getParameter("Username");
				String password = request.getParameter("password");
				
				UserAccount usr = new UserAccount(Username,password);
				UserAccount u = DBUtils.findUser(conn,usr.getUserName(),usr.getPassword());
				if(u != null){
					UserAccount user = new 	UserAccount(u);
					Request request1 = new Request();
					request1.setAttribute("username", "Chào bạn: "+ user.getUserName());
					request.getRequestDispatcher("/index.jsp").forward(request,response);
				}else{
					response.setContentType("text/html;charset=UTF-8");
					try(PrintWriter out  = response.getWriter()){
					out.println("<!DOCTYPE html>");
					out.println("<html>");
					out.println("<head>");
					out.println("<title>Thông báo</title>");
					out.println("</head>");
					out.println("<body>");
					out.println("<h1>Thông tin đăng nhập không chính xác<a href = Login.jsp>Nhập lại</a></h1> ");
					out.println("</body>");
					out.println("</html>");
					}
				}
			}catch(ClassNotFoundException ex){
			Logger.getLogger(SignUpServlet.class.getName()).log(null);
			}catch(SQLException ex1){
				Logger.getLogger(SignUpServlet.class.getName()).log(null);
			}
			}
	
}


