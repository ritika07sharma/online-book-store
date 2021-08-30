//package servlets;

import javax.servlet.*;

//import constants.IOnlineBookStoreConstants;
//import sql.IUserContants;

import java.io.*;
import java.sql.*;

public class UserLoginServlet extends GenericServlet {
	public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		//res.setContentType(IOnlineBookStoreConstants.CONTENT_TYPE_TEXT_HTML);
		res.setContentType("text/html;charset=UTF-8");
                String uName = req.getParameter("username");
		String pWord = req.getParameter("password");
		
                try {
                        pw.println("hello");
                        Class.forName("com.mysql.jdbc.Driver");   
                        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinebookstore","root","");
                        Statement stmt=con.createStatement();
                    
                        ResultSet rs=stmt.executeQuery("select username , password from users where username='"+uName+"'"+"and password='"+pWord+"'");
                      //  stmt.executeQuery("SELECT PASSWORD FROM LOGIN WHERE USERNAME="+username);
                      //  ResultSet rs=stmt.getResultSet();
                        String correctPassword="";
                        if(rs.next()){
	                   correctPassword=rs.getString(1);
                                 }
                        else
                        {
                            pw.println("<div class=\"tab\">Incorrect UserName or PassWord</div>");
                        }
                        if(correctPassword.equals(pWord)){
	                       pw.println("welcome");
                                RequestDispatcher rd = req.getRequestDispatcher("Sample.html");
				rd.include(req, res);
				pw.println("<div class=\"home hd brown\">Welcome ! " + uName + "</div><br/>");
				pw.println("<div class=\"tab hd brown\">User Login Successful !</div><br/>");
				pw.println("<div class=\"tab\"><a href=\"ViewBookServlet\">VIEW BOOKS</a></div>");
				//pw.println("<div class='tab'><a href=\"buybook\">BUY BOOKS</a></div>");       
                                 }
                        else
                          {
                              RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">Incorrect UserName or PassWord</div>");
                          }
                            
                        
                         
    //                    Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
//ResultSet rs=stmt.executeQuery("SELECT username,password FROM users WHERE username=" + uName + " AND password= " + pWord  );
                        
//Connection con = DBConnection.getCon();
			//Statement ps = con.createStatement("SELECT username,password FROM users WHERE username='?' AND password='?' ");
					//+ uName +  "AND password="  + pWord);
			//ps.setString(1, uName);
			//ps.setString(2, pWord);
                        //int f=rs.executeUpdate();
                        //pw.println(f);
                        //if (f==1)
			//rs = ps.executeQuery();
			/*if (rs.next()) {
				RequestDispatcher rd = req.getRequestDispatcher("Sample.html");
				rd.include(req, res);
				pw.println("<div class=\"home hd brown\">Welcome ! " + uName + "</div><br/>");
				pw.println("<div class=\"tab hd brown\">User Login Successful !</div><br/>");
				pw.println("<div class=\"tab\"><a href=\"viewbook\">VIEW BOOKS</a></div>");
				pw.println("<div class='tab'><a href=\"buybook\">BUY BOOKS</a></div>");
			} else {

				RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">Incorrect UserName or PassWord</div>");
			}*/

		} catch (ClassNotFoundException | SQLException e) {
		}
	}
}