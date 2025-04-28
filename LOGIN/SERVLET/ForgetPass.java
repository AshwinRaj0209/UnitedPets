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
import java.sql.ResultSet;


@WebServlet("/ForgetPass")
public class ForgetPass extends HttpServlet {
	private static final long serialVersionUID = 1L;
  	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/unitedpets","root","Ashwin@2003");
			PreparedStatement pt=conn.prepareStatement("update SignUp set password=? where Email=?");
			pt.setString(1, request.getParameter("email"));
			pt.setString(2, request.getParameter("password"));
            int rs=pt.executeUpdate();
			 
			 if (rs>0) {
	                // Login successful - Redirect to home page
	                response.sendRedirect("Login.Html");
	            } else {
	                // Login failed - Show error message and reload login page
	                RequestDispatcher rd = request.getRequestDispatcher("ForgetPass.Html");
	                rd.include(request, response); // Include keeps the error message visible on the same page
	                out.println("<h2 style='color:red; position: relative; bottom: 17em;'>Invalid Username or Password</h2>");
	            }
			}
		catch(Exception e) {
			System.out.println(e.toString());
		}
	}

}
