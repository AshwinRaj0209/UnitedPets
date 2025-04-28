package com.unitedpets;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/delete")
public class delete extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("email") == null) {
            response.sendRedirect("Login.Html");
            return;
        }

        String email = (String) session.getAttribute("email");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/unitedpets", "root", "Ashwin@2003");

            // Delete from both tables: candidateDetails and candidate (adjust as per your setup)
            PreparedStatement ps1 = conn.prepareStatement("DELETE FROM signup WHERE Email = ?");
            ps1.setString(1, email);
            ps1.executeUpdate();

            PreparedStatement ps2 = conn.prepareStatement("DELETE FROM completeprofile WHERE Email = ?");
            ps2.setString(1, email);
            int result = ps2.executeUpdate();

            ps1.close();
            ps2.close();
            conn.close();

            // If deletion successful, destroy session and redirect
            if (result > 0) {
                session.invalidate();
                response.sendRedirect("DeleteMessage.Html");
            } else {
                response.sendRedirect("UserInterface.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}