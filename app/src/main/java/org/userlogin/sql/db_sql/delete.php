
<?php

    require_once __DIR__ .'/db_function.php';
    $db = new DB_CONNECT();

           $id = $_GET['id'];

           $updateExist = $db->isUserExisted($id);

           $result = mysql_num_rows($updatelExist);

           if($result <= 0){

           $result = mysql_query ("DELETE FROM user WHERE  id = '$id'");
           $response["numrows"] = 1;
           $response["id"] = $id;

           if($result > 0){

              echo 'successfully Delete';

           }else{

           echo 'Gagal Delete ! ';
        }
    }
        echo json_encode($response);
?>
