<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.*,com.unitedpets.Pet" %>

<!DOCTYPE html>
<html lang="en">
<head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>United Pets</title>
        <link rel="shortcut icon" href="./Home/Icon.png" type="image/x-icon">
        <link rel="stylesheet" href="UserInterface.Css">
        <script src="UserInterface.Js"></script>
    </head>
    
    
    <body>
        <div class="sidebar">
            <div class="logo">
            <img src="./Home/Title-Bar.png" alt="logo" />
            </div>
            <div class="profile">
            <div class="profile-name"> <h1>Welcome <%= session.getAttribute("username") %>! </h1></div>
            </div>
        
            <ul class="menu">
            <li  class="nav-profile" id="nav-profile" onclick="nav_profile()"><img src="./User/Profile.png" alt="" > Profile</li>
            <li  id="nav-adoption" onclick="nav_adoption()"><img src="./User/Adoption.png" alt=""> Adoption</li>
            <li  id="nav-postadoption" onclick="nav_postadoption()"><img src="./User/PostAdoption.png" alt=""> Post Adoption</li>
            <li  id="nav-delete" onclick="nav_delete()"> <img src="./User/Delete.png" alt=""> Delete Account</li>
            <li  id="nav-signout" onclick="nav_signout()"><img src="./User/SignOut.png" alt=""> Log Out</li>
            </ul>
        </div>

        <!-- Main Content -->
        <div class="content">
            <!-- Profile Section -->
            <div id="profile">
                <h2>Profile</h2>
             <div class="profile-container">
                    <div class="profile-details">
                       
                             <div class="profile-details-bg">
                                <label><b>👤 Name:</b></label><br> 
                                <div class="inputbox"><%= session.getAttribute("name") %></div>
                                <label><b>📞 Mobile:</b></label><br> 
                                <div class="inputbox"><%= session.getAttribute("mobile_no") %></div>
                                <label><b>📧 Email:</b></label><br>
                                <div class="inputbox"> <%= session.getAttribute("email") %></div>
                                <label><b>🎂 Age:</b></label><br> 
                                <div class="inputbox"> <%= session.getAttribute("age") %></div>
                                <label><b>🏡 Address:</b></label><br> 
                                <div class="inputbox"><%= session.getAttribute("address") %></div>
                                <label><b>🐾 Preferred Pet Type:</b></label><br>
                                <div class="inputbox"><%= session.getAttribute("pettype") %></div>
                             </div>
                           
                            
                        </div>
                </div>
            </div>

            <!-- Adoption Section -->
            <div id="adoption" class="hidden">
                <h2>Adoption</h2>
                <p>🐶 Pets available for adoption:</p>
                <div id="petList" class="pet-list">
    <table>
        <tr>
      
            <th>Photo</th>
            <th>Pet Name</th>
            <th>Breed</th>
            <th>Gender</th>
            <th>Age</th>
             <th>Health1</th>
             <th>Health2</th>
             <th>Location</th>
             <th>OwnerName</th>
             <th>Prize</th>
             <th>ContactMail</th>
             
        </tr>
        <%
    if (request.getAttribute("pets") == null) {
        response.sendRedirect("viewPets");
        return;
    }
    
%>
        <%
            List<Pet> pets = (List<Pet>) request.getAttribute("pets");
        
        
        

            if (pets == null) {
        %>
            <tr><td colspan="13">Pet list is <strong>null</strong> (Servlet may not have passed data)</td></tr>
        <%
            } else if (pets.isEmpty()) {
        %>
            <tr><td colspan="13">No pets available in the database</td></tr>
        <%
            } else {
                for (Pet pet : pets) {
        %>
            <tr>
            <td>
     
    <!-- DEBUG START -->
    <% 
        // Get clean filename (remove any path components)
        String photoName = pet.getPhotoName().replace("uploads/", "").trim();
        String imageUrl = request.getContextPath() + "/uploads/" + photoName;
    %>
    
    <!-- Debug output -->
  
    
    <!-- Image with error handling -->
    <img src="<%= imageUrl %>" 
         alt="<%= pet.getpetname() %>" 
         style="width:80px;height:120px;"
         onerror="this.onerror=null;this.src='<%= request.getContextPath() %>/images/default-pet.jpg'">
</td>

                <td><%= pet.getpetname() %></td>
                <td><%= pet.getBreed() %></td>
                <td><%= pet.getGender() %></td>
                <td><%= pet.getAge() %></td>
                <td><%= pet.getHealth1() %></td>
                <td><%= pet.getHealth3() %></td>
                <td><%= pet.getLocation() %></td>
                <td><%= pet.getOwnername() %></td>
                <td><%= pet.getContactno() %></td>
                <td><a href= "https://mail.google.com/mail/?view=cm&fs=1&to=<%= pet.getOwnermail()%>&su=Pet%20Details"  target=_blank><%= pet.getOwnermail()%></a></td>
            </tr>
        <%
                }
            }
        %>
    </table>
</div>

            </div>
            
            
   

            <!-- Post for Adoption Section -->
            <div id="postadoption" class="hidden"  >
                <h2>Post for Adoption</h2>
                <form id="adoptionForm" action="UserInterface" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="petName">Pet Name</label>
                        <input type="text" id="petName" name="petName" required>
                    </div>
        
                    <div class="form-group">
                        <label for="petType">Pet Type</label>
                        <select id="petType" name="petType" required>
                            <option value="">Select Pet Type</option>
                            <option value="Dog">Dog</option>
                            <option value="Cat">Cat</option>
                            <option value="Rabbit">Rabbit</option>
                            <option value="Bird">Bird</option>
                            <option value="Other">Other</option>
                        </select>
                    </div>
        
                    <div class="form-group">
                        <label for="breed">Breed</label>
                        <input type="text" id="breed" name="breed">
                    </div>
        
                    <div class="form-group">
                        <label for="age">Age (in years)</label>
                        <input type="number" id="age" name="age" min="0" required>
                    </div>
        
                    <div class="form-group">
                        <label for="gender">Gender</label>
                        <select id="gender" name="gender">
                            <option value="">Select Gender</option>
                            <option value="Male">Male</option>
                            <option value="Female">Female</option>
                        </select>
                    </div>
        
                    <div class="form-group">
                        <label for="size">Size</label>
                        <select id="size" name="size">
                            <option value="">Select Size</option>
                            <option value="Small">Small</option>
                            <option value="Medium">Medium</option>
                            <option value="Large">Large</option>
                        </select>
                    </div>
        
                    <div class="form-group full-width">
                        <label>Health Status</label>
                        <div class="checkbox-group">
                            <label><input type="checkbox" name="health1[]" value="Vaccinated"> Vaccinated</label>
                            <label><input type="checkbox" name="health2[]" value="Neutered"> Neutered</label>
                            <label><input type="checkbox" name="health3[]" value="Dewormed"> Dewormed</label>
                            <label><input type="checkbox" name="health4[]" value="Healthy"> Healthy</label>
                            <label><input type="checkbox" name="health5[]" value="Special Needs"> Special Needs</label>
                        </div>
                    </div>
        
                    <div class="form-group full-width">
                        <label for="description">Pet Description</label>
                        <textarea id="description" name="description" rows="2" required></textarea>
                    </div>
        
                    <!-- Image Upload -->
                    <div class="form-group">
                        <label for="petImage">Upload Pet Image</label>
                        <input type="file" id="petImage" name="petImage" accept="image/*" required>
                    </div>
        
                    <!-- Location & Contact Information -->
                    <div class="form-group">
                        <label for="location">Current Location</label>
                        <input type="text" id="location" name="location" required>
                    </div>
        
                    <div class="form-group">
                        <label for="ownerName">Owner's Name</label>
                        <input type="text" id="ownerName" name="ownerName" required>
                    </div>
        
                    <div class="form-group">
                        <label for="contactNumber">Prize</label>
                        <input type="text" id="contactNumber" name="pcontactNumber" required>
                    </div>
        
                    <div class="form-group">
                        <label for="ownerEmail">Email</label>
                        <input type="email" id="ownerEmail" name="ownerEmail" required>
                    </div>
        
                    <div class="form-group full-width">
                        <label for="adoptionConditions">Adoption Conditions (Optional)</label>
                        <textarea id="adoptionConditions" name="adoptionConditions" rows="2"></textarea>
                    </div>
        
                    <button type="submit" >Submit</button>
                </form>
            </div>

            <!-- Sign Out Section -->
            <div class="animate">
                <div id="signout" class="hidden">
                    <h2>Are You sure you want to SignOut?</h2>
                    <div class="DeleteButton">
                    <form action="Logout" method="get">
                        <button class="but-yes">Yes</button>
                        </form>
                        <button class="but-no">No</button>
                    </div>
                </div>
            </div>

            <!-- Delete Account Section -->
            <div class="animate">
                <div id="deleteaccount" class="hidden">
                    <h2>Are You sure you want to Delete your Account?</h2>
                    <div class="DeleteButton">
                    <form action="delete" method="post">
                        <button class="but-yes">Yes</button>
                        </form>
                        <button class="but-no">No</button>
                    </div>
                </div>
            </div>
        </div>
        </body>
</html>