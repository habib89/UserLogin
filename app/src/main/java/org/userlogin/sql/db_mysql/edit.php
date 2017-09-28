<?php

        require_once __DIR__ .'/config.php';
//        $db new DB_CONNECT();

        $id = $_POST['id'];

    class emp{}

 	if (empty($id)) {
 		$response = new emp();
 		$response->success = 0;
 		$response->message = "Error Mengambil Data";
 		die(json_encode($response));
 	} else {
 		$query 	= mysql_query("SELECT * FROM user WHERE id=28");
 		$row 	= mysql_fetch_array($query);

 		if (!empty($row)) {
 			$response = new emp();
 			$response->success = 1;
 			$response->id = $row["id"];
 			$response->nama = $row["nama"];
 			$response->alamat = $row["alamat"];
 			die(json_encode($response));
 		} else{
 			$response = new emp();
 			$response->success = 0;
 			$response->message = "Error Mengambil Data";
 			die(json_encode($response));
 		}
 	}

?>