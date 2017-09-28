<?php
    require_once __DIR__ .'/db_function.php';
    $db = new DB_CONNECT();

        if(isset($_GET['username']) &&  isset($_GET['email']) && isset ($_GET['password'])){

            $username = $_GET['username'];
            $password = $_GET['password'];
            $email = $_GET['email'];

            $emailExist = $db->isUserExisted($email);

            $numrows = mysql_num_rows($emailExist);

            if($numrows <= 0){

                $numrows =  mysql_query ("INSERT INTO user (username, password, email) VALUES ('$username','$password', '$email')");

                if($numrows > 0){
                    echo "Selamat data anda telah tersimpan";
                } else {
                    echo "Data anda gagal disimpan";
                }

            } else {
                echo "Email sudah terambil";
            }

        } else {

            echo 'gak isset';
        }

?>