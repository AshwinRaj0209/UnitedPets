package com.unitedpets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/CompleteProfile")
public class CompleteProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/unitedpets", "root" , "Ashwin@2003");
			PreparedStatement pt=conn.prepareStatement("insert into completeprofile values(?,?,?,?,?,?)");
			
			pt.setString(1, request.getParameter("name"));
			pt.setString(2, request.getParameter("mobileno"));
			pt.setString(3, request.getParameter("email"));
			pt.setString(4, request.getParameter("age"));
			pt.setString(5, request.getParameter("address"));
			pt.setString(6, request.getParameter("pettype"));
			pt.executeUpdate();
			pt.close();
			conn.close();
			
			RequestDispatcher rd=request.getRequestDispatcher("Login.Html");
			rd.forward(request,response);
		}
	
		catch(Exception e) {
			System.out.println(e.toString());
		}

}
}
