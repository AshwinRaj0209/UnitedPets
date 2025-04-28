package com.unitedpets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// Database Connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/unitedpets", "root", "Ashwin@2003");
			PrintWriter out = response.getWriter();


			// Validate Email & Password
			PreparedStatement pt = conn.prepareStatement("SELECT * FROM Signup WHERE Email=? AND password=?");
			pt.setString(1, request.getParameter("email"));
			pt.setString(2, request.getParameter("password"));
			ResultSet rs = pt.executeQuery();

			if (rs.next()) {
				// ✅ Login Successful
				HttpSession session = request.getSession();
				session.setAttribute("username", rs.getString("Username"));

				// Fetch User Profile from `completeprofile` Table
				PreparedStatement pt1 = conn.prepareStatement("SELECT * FROM completeprofile WHERE Email=?");
				pt1.setString(1, request.getParameter("email"));
				ResultSet rs1 = pt1.executeQuery();

				if (rs1.next()) {
					// Store user profile details in session
					
					session.setAttribute("name", rs1.getString("Name"));
					session.setAttribute("mobile_no", rs1.getString("mobile_no"));
					session.setAttribute("email", rs1.getString("Email"));
					session.setAttribute("age", rs1.getString("age"));
					session.setAttribute("address", rs1.getString("address"));
					session.setAttribute("pettype", rs1.getString("pettype"));
				}
				response.sendRedirect("UserInterface.jsp");
			} 
			 else {
	                // Login failed - Show error message and reload login page
	                RequestDispatcher rd = request.getRequestDispatcher("Login.Html");
	                rd.include(request, response); // Include keeps the error message visible on the same page
	                out.println("<h2 style='color:red; position: relative; bottom: 17em;'>Invalid Username or Password</h2>");
	            }


			// Close Resources
			pt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
