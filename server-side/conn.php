<?php
$servername = "localhost";
$username = "webuser";
$password = "view123";
$dbname = "ec521";

// Create connection
$conn = mysqli_connect($servername, $username, $password, $dbname);

// Check connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}
//echo "Connected successfully";
?> 