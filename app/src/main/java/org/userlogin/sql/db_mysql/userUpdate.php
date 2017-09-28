<?php
       require_once __DIR__ .'/db_function.php';
         $db = new DB_CONNECT();

         $id = $_GET['id'];
         $username = $_GET['username'];
         $password = $_GET['password'];
         $email = $_GET['email'];


           $updatelExist = $db->isUserExisted($id);

           $numrows = mysql_num_rows($updateExist);

           if($numrows <= 0){

           $numrows = mysql_query ("UPDATE user SET username = '$username' ,password = '$password', email = '$email' WHERE id = '$id'");


            if($numrows > 0){

              echo 'Data Update successfully';

            }else{

            echo 'Gagal Update ! ';

    }

}


?>