package com.unitedpets;



import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class Contact
 */
@WebServlet("/ContactUs")
public class ContactUs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/unitedpets", "root" , "Ashwin@2003");
			PreparedStatement pt=conn.prepareStatement("insert into contactus values(?,?,?,?)");
			
			pt.setString(1, request.getParameter("UserName"));
			pt.setString(2, request.getParameter("EMail"));
			pt.setString(3, request.getParameter("Subject"));
			pt.setString(4, request.getParameter("Message"));
			pt.executeUpdate();
			
			pt.close();
			conn.close();
			
			RequestDispatcher rd=request.getRequestDispatcher("SuccessMessage.Html");
			rd.forward(request,response);
		
			
		}
		catch(Exception e){
			System.out.println(e.toString());
			
		}
	}
	}


