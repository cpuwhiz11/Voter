<?php
//Receive a question id, either respond with data from the question or an error
//Paul Geromini 

//Get the nessesary data
$questionId = $_POST["ID"];
$selectedAns = $_POST["ANS"];

//Connect to the db
mysql_connect("localhost","root","root") or die("Unable to connect"); 
mysql_select_db("questiondb");

mysql_query("INSERT INTO responses (questionid, answer) VALUES ('$questionId', '$selectedAns')");

//Close our db connection
mysql_close();

//Respond with question info
$Array = array("ERROR" => "Error", "WIN" => "Success");  
echo json_encode($Array);
?>