<?
class SQLConnection{
	public $host = "localhost";
	private $user = "shufeng";
	private $pw = "YUtzpwbSWK3uMLG3";
	private $db = "shufeng_jioukaoyao";
	
	public function getHost(){
		return $this->host;
	}
	
	public function getUser(){
		return $this->user;
	}
	
	public function getPw(){
		return $this->pw;
	}
		
	public function getDBName(){
		return $this->db;
	}
}
?>