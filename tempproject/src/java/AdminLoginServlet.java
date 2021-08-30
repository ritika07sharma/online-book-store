//package servlets;

import javax.servlet.*;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpSession;

//import constants.IOnlineBookStoreConstants;
//import sql.IUserContants;

import java.io.*;
import java.sql.*;

public class AdminLoginServlet extends GenericServlet {
	public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		//res.setContentType(IOnlineBookStoreConstants.CONTENT_TYPE_TEXT_HTML);
                  res.setContentType("text/html;charset=UTF-8");
		String uName = req.getParameter("username");
		String pWord = req.getParameter("password");
		try {
                        Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinebookstore","root","");
                	Statement stmt=con.createStatement();
                        ResultSet rs=stmt.executeQuery("select username , password from users where username='"+uName+"'"+"and password='"+pWord+"'");
        //                PreparedStatement ps = con.prepareStatement("SELECT * FROM " + IUserContants.TABLE_USERS + " WHERE  "
	//				+ IUserContants.COLUMN_USERNAME + "=? AND " + IUserContants.COLUMN_PASSWORD + "=? AND "
	//				+ IUserContants.COLUMN_USERTYPE + "=1");
			//ResultSet rs = ps.executeQuery();
                        rs=stmt.getResultSet();
                        String correctPassword="";
                        if (rs.next()) {
                             correctPassword=rs.getNString(1);
                        }
                        else
                        {
                            pw.println("<div class=\"tab\">Incorrect UserName or PassWord</div>");
                        }
			
                        if(correctPassword.equals(pWord)){
	                       pw.println("welcome");
                                RequestDispatcher rd = req.getRequestDispatcher("Sample.html");
				rd.include(req, res);
                       // RequestDispatcher rd = req.getRequestDispatcher("Sample.html");
                       pw.println("<div class=\"home hd brown\">Welcome ! " + uName + "</div><br/>");
				pw.println("<div class=\"tab hd brown\">Admin Login Successful !</div><br/>");
                               // pw.println("<div class='tab'><a href=\"buybook\">BUY BOOKS</a></div>");       
                                 }
                        else
                          {
                              RequestDispatcher rd = req.getRequestDispatcher("AdminLogin.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">Incorrect AdminName or PassWord</div>");
                          }
				pw.println("<div class=\"tab\"><br/><a href=\"RemoveBooks.html\">REMOVE BOOKS</a><br/></div>");
				//Cookie ck = new Cookie("usertype","admin");
				
				/*rd.include(req, res);
				pw.println("<div class=\"tab\">Admin login Successful</div>");
				pw.println("<div class=\"tab\"><br/><a href=\"AddBook.html\">ADD BOOKS</a><br/></div>");
				pw.println("<div class=\"tab\"><br/><a href=\"RemoveBooks.html\">REMOVE BOOKS</a><br/></div>");
				pw.println("<div class=\"tab\"><br/><a href=\"viewbook\">VIEW BOOKS</a></div>");
			} else {

				RequestDispatcher rd = req.getRequestDispatcher("AdminLogin.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">Incorrect UserName or PassWord</div>");
			}*/

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}