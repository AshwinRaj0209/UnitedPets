package com.unitedpets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.nio.file.Paths;
import java.sql.*;

@WebServlet("/UserInterface")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 10)
public class UserInterface extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Save image to "uploads" folder
        	Part filePart = request.getPart("petImage");
        	String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        	String uniqueFileName = System.currentTimeMillis() + "_" + fileName;

        	// Absolute uploads folder path
        	String uploadsDir = "E:/JAVA/UnitedPets/src/main/webapp/uploads";
        	File uploadFolder = new File(uploadsDir);
        	if (!uploadFolder.exists()) uploadFolder.mkdirs();

        	String filePath = uploadsDir + File.separator + uniqueFileName;

        	// Write to disk
        	filePart.write(filePath);

        	// Save to database (relative path, or uniqueFileName as per your logic)

            System.out.println(filePath);

            // Now insert into DB
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/unitedpets", "root", "Ashwin@2003");

            PreparedStatement ps = conn.prepareStatement("INSERT INTO postadoption VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            ps.setString(1, request.getParameter("petName"));
            ps.setString(2, request.getParameter("petType"));
            ps.setString(3, request.getParameter("breed"));
            ps.setString(4, request.getParameter("age"));
            ps.setString(5, request.getParameter("gender"));
            ps.setString(6, request.getParameter("size"));

            ps.setString(7, request.getParameter("health1[]"));
            ps.setString(8, request.getParameter("health2[]"));
            ps.setString(9, request.getParameter("health3[]"));
            ps.setString(10, request.getParameter("health4[]"));
            ps.setString(11, request.getParameter("health5[]"));
            ps.setString(12, request.getParameter("description"));
         // Change this line in UserInterface servlet:
            ps.setString(13, uniqueFileName);  // Store just filename without "uploads/" prefix // Save relative path
 // Save the image path in the database
            ps.setString(14, request.getParameter("location"));
            ps.setString(15, request.getParameter("ownerName"));
            ps.setString(16, request.getParameter("pcontactNumber"));
            ps.setString(17, request.getParameter("ownerEmail"));
            ps.setString(18, request.getParameter("adoptionConditions"));

            ps.executeUpdate();  // Execute the database update
            ps.close();
            conn.close();

            // Forward to UserInterface.jsp (or another page if needed)
            RequestDispatcher rd = request.getRequestDispatcher("UserInterface.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.toString());
        }
    }
}
