<?php

//Get the nessesary data
$possibleResponses = $_GET['Possible Responses'];
$id = $_GET['ID'];

//Connect to the db
mysql_connect("129.63.70.103","root","root"); 
mysql_select_db("responses");

?>