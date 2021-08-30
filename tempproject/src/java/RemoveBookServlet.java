//package servlets;

import java.sql.*;
import javax.servlet.*;

//import sql.IBookConstants;

import java.io.*;

public class RemoveBookServlet extends GenericServlet {
	public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html;charset=UTF-8");
		String bkid = req.getParameter("barcode");
		try {
                    Class.forName("com.mysql.jdbc.Driver");   
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinebookstore","root","");
		     Statement stmt=con.createStatement();
                     ResultSet rs=stmt.executeQuery("delete from books where barcode='"+bkid+"'");
                    //Connection con = DBConnection.getCon();
			//PreparedStatement ps = con.prepareStatement(
			//		"delete from " + IBookConstants.TABLE_BOOK + "  where " + IBookConstants.COLUMN_BARCODE + "=?");
			String correctBarcode="";
                        if(rs.next()){
                            correctBarcode=rs.getString(1);
                        }
                        else
                        {
                            pw.println("<div class=\"tab\"Incorrect Barcode</div>");
                        }
                        if(correctBarcode.equals(bkid))
                        {
                            pw.println("welcome");
                            
                        
                       // rs.setString(1, bkid);
			//int k = rs.executeUpdate();
			//if (k == 1) {
				RequestDispatcher rd = req.getRequestDispatcher("Sample.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">Book Removed Successfully</div>");
				pw.println("<div class=\"tab\"><a href=\"RemoveBooks.html\">Remove more Books</a></div>");

			} else {
				RequestDispatcher rd = req.getRequestDispatcher("Sample.html");
				rd.include(req, res);
				pw.println("<div class=\"tab\">Book Not Available In The Store</div>");
				pw.println("<div class=\"tab\"><a href=\"RemoveBooks.html\">Remove more Books</a></div>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
