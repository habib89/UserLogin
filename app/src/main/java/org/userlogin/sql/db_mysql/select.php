<?php
    require_once __DIR__ .'/db_function.php';
        $db = new DB_CONNECT();


        $id = $_GET['id'];

        $selectExist = $db->isUserExisted($id);

        $result = mysql_num_rows($selectExist);

        $quary = mysql_query ("SELECT * FORM user ORDER BY username ASC");

        $json = array();

    while($row = mysql_query($quary)){

            $json[] = $row;

    }

    echo json_encode($json);
    mysql_close ($connect);


?>