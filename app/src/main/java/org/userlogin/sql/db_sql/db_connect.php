<?php
class dbx_connect {
    private $conn;

    public function connect() {
        require_once 'include/connect.php';

        $this->conn = new mysqli(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);

        echo "dbx connect";
        return $this->conn;
    }
}
?>