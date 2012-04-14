<?php
//Store new questions, 
//Paul Geromini 

//Get the data from the app
$title = $_POST["Title"];
$question = $_POST["Question"];
$possibleResponses = $_POST["PossibleResponses"];
$id = $_POST["ID"];
  
//Connect to the db
mysql_connect("localhost","root","root") or die("Unable to connect"); 
mysql_select_db("questiondb");

//Send data
mysql_query("INSERT INTO question (Title, Question, Answers) VALUES ('$title', '$question', '$possibleResponses')");

//Grab id
$id = mysql_insert_id(); 

//Close our db connection
mysql_close();

//Respond with id number
$Array = array("ID" => $id);  
echo json_encode($Array);

?>