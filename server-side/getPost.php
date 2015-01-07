<html>
<head>
<title>Backend Logs</title>
</head>
<body>

<?php

include 'conn.php';

if(!empty($_POST)) {
/*
    $sql = "select count(*) cnt from vuln_log where ip_addr='".($_POST["ip_addr"])."' and vuln_type='".($_POST["vuln_type"])."' ";
    $result = $conn->query($sql);
    if ($result->num_rows > 0) {
        // output data of each row
        while($row = $result->fetch_assoc()) {       
            if ($row["cnt"]=="0") {
                //add row
	        $sql_i = "insert into vuln_log (ip_addr, vuln_type, vuln_print) values ('".($_POST["ip_addr"])."','".($_POST["vuln_type"])."','".($_POST["vuln_print"])."')";
                if($conn->query($sql_i) === false) {
                    file_put_contents('debug' . time() . '.log', var_export("Insertion failure!", true));
                }
            }
        }
    }*/
    $file = file_get_contents('logs.txt');
    $file .= "\n" . time() . "\n" . $_POST["ip_addr"] . "\n" . $_POST["vuln_type"] . "\n" . $_POST["vuln_print"];
    file_put_contents('logs.txt',$file);
    file_put_contents('interesting',$_POST["vuln_print"]);
}
else {
    file_put_contents('debug' . time() . '.log', var_export("Post was corrupted!", true));
}
?>

</body>
</html>