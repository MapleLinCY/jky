<?
class LocalLibrary{
	
	public function getTime(){
		date_default_timezone_set("Asia/Taipei");
		$datetime = date ("Y-m-d H:i:s" , mktime(date('H'), date('i'), date('s'), date('m'), date('d'), date('Y'))) ;
		return $datetime;
	}

	// 去除倒斜線
	public function getFormatString($str){
		return preg_replace('/\\\\/', '',$str);
	}
	
}
?>