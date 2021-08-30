//package servlets;
import java.io.*;
import java.sql.*;
import javax.servlet.*;

//import constants.IOnlineBookStoreConstants;
//import sql.IBookConstants;

public class ViewBookServlet extends GenericServlet{
	public void service(ServletRequest req,ServletResponse res) throws IOException,ServletException
	{
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		try {
                        Class.forName("com.mysql.jdbc.Driver");   
                        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinebookstore","root","");
			//Connection con = DBConnection.getCon();
			Statement stmt=con.createStatement();
                    
                        ResultSet rs=stmt.executeQuery("select * from books");
                        //PreparedStatement ps = con.prepareStatement("Select * from books");
			//ResultSet rs = ps.executeQuery();
			pw.println("<div class=\"tab\">Books Available In Our Store</div>");
			pw.println("<div class=\"tab\">\r\n" + 
					"		<table>\r\n" + 
					"			<tr>\r\n" + 
					"				\r\n" + 
					"				<th>Book Code</th>\r\n" + 
					"				<th>Book Name</th>\r\n" + 
					"				<th>Book Author</th>\r\n" + 
					"				<th>Book Price</th>\r\n" + 
					"				<th>Quantity</th><br/>\r\n" + 
					"			</tr>");
			while(rs.next())
			{
				String bCode = rs.getString(1);
				String bName = rs.getString(2);
				String bAuthor = rs.getString(3);
				int bPrice = rs.getInt(4);
				int bQty = rs.getInt(5);
				pw.println("<tr><td>"+bCode+"</td>");
				pw.println("<td>"+bName+"</td>");
				pw.println("<td>"+bAuthor+"</td>");
				pw.println("<td>"+bPrice+"</td>");
				pw.println("<td>"+bQty+"</td></tr>");
			}
			pw.println("</table>\r\n" + 
					"	</div>");
			//pw.println("<div class=\"tab\"><a href=\"AddBook.html\">Add More Books</a></div>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
