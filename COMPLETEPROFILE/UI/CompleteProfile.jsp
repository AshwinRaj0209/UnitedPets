<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>United Pets</title>
    <link rel="shortcut icon" href="./Home/Icon.png" type="image/x-icon">
    <link rel="stylesheet" href="./CompleteProfile.Css">
</head>
<body>
    <div class="Header">
        <img src="./Home/Title-Bar.png" alt="">
        <h1>Welcome to United Pets</h1><img src="./" alt="">
    </div>

    <div class="container">
        <h2>Successfully Registered!</h2>
        <p>Please complete your profile before logging in.</p>
        
        <form action="CompleteProfile" method="post">
            <div class="form-group">
                <label>Profile Name:</label>
                <input type="text" name="name" required>
            </div>
            <div class="form-group">
                <label>Mobile Number:</label>
                <input type="text" name="mobileno" required>
            </div>
            <div class="form-group">
                <label>Email:</label>
                <input type="email" name="email" required>
            </div>
            <div class="form-group">
                <label>Age:</label>
                <input type="number" name="age" required>
            </div>
            <div class="form-group">
                <label>Address:</label>
                <input type="text" name="address" required>
            </div>

            <div class="form-group">
                <label>Preferred Pet Type:</label>
                <select name="pettype" required>
                    <option value="">Select</option>
                    <option value="Dog">Dog</option>
                    <option value="Cat">Cat</option>
                    <option value="Rabbit">Rabbit</option>
                    <option value="Bird">Bird</option>
                </select>
            </div>

            <button type="submit">Complete & Move to Login</button>
        </form>
    </div>

</body>
</html>
