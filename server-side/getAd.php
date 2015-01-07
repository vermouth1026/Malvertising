<html>
<head>
<title>Hack This World!</title>
<link rel="stylesheet" type="text/css" href="ad.css">
</head>
<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js'></script>

<body>
<?php include 'conn.php';?>

<table>
<tr>
<td>
<?php 

$r_no=1;
$sql="select advertisement_image, ifnull(advertisement_js,'') ad_js from advertisement where advertisement_id=".$r_no;
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {       
		echo '<img id="EC521" src="'.$row["advertisement_image"].'"/>';
		echo '<div id="indicator">Hello.<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/></div>';
		echo $row["ad_js"];    }
} else {
    echo '0 results';
}



?>



</td>
</tr>
</table>

</body>
</html>