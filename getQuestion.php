<?php
//Get an existing question based on the id and return question info or an error
//Paul Geromini 

//Get the data from the app
$id = $_POST["ID"];

//Connect to the db
mysql_connect("localhost","root","root") or die("Unable to connect"); 
mysql_select_db("questiondb");

//Retrieve question based on id    
$question = mysql_query("SELECT * FROM question WHERE id='$id'");

//Check if we got a result
if(mysql_numrows($question) == null){
  //If so respond with error
  $Array = array("ERROR" => "Question with id: '$id', does not exist" );  
  echo json_encode($Array);
  
  //Close our db connection
  mysql_close();

  return; 
} 

//Retrieve all selected answers
$ans = mysql_query("SELECT * FROM responses WHERE questionid = '$id'");

//Close our db connection
mysql_close();

//Retrieve the row
$row = mysql_fetch_array($question);

//Store data into each var
$title = $row['Title'];
$questionContent = $row['Question'];
$possibleResponses = $row['Answers'];

//Check to see if there are any responses 
if(mysql_numrows($ans) == null){
  //if not
  $answers = "NONE";
} else {
  while ($row = mysql_fetch_array($ans)){
    $answers[] = $row['answer'];
  }
}
  


//Respond with question info
$Array = array("ERROR" => "NONE", "Title" => $title, "Question" => $questionContent, 
               "PossibleResponse" => $possibleResponses, "Responses" => $answers);  
echo json_encode($Array);
?>