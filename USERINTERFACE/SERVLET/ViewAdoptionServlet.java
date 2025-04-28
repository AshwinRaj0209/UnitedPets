package com.unitedpets;



import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/viewPets")
public class ViewAdoptionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Pet> petList = new ArrayList<>();

        String jdbcURL = "jdbc:mysql://localhost:3306/unitedpets";
        String dbUser = "root";
        String dbPassword = "Ashwin@2003";

        try {
            System.out.println("Loading JDBC driver...");
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Connecting to database...");
            Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            System.out.println("Database connected!");

            String sql = "SELECT * FROM postadoption";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Pet pet = new Pet();
                pet.setPhotoName(rs.getString("petimage")); // Store filename from DB
                pet.setpetname(rs.getString("petname"));
                pet.setBreed(rs.getString("breed"));
                pet.setGender(rs.getString("gender"));
                pet.setAge(rs.getInt("age"));
                pet.setHealth1(rs.getString("health1"));
                pet.setHealth3(rs.getString("health3"));
                pet.setLocation(rs.getString("location"));
                pet.setOwnername(rs.getString("ownername"));
                pet.setContactno(rs.getLong("contactno"));
                pet.setOwnermail(rs.getString("ownermail"));
                petList.add(pet);
            }





            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("pets", petList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("UserInterface.jsp");
        dispatcher.forward(request, response);
        
    }
}




