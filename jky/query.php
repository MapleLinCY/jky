<?php

	// 若使用CURL來POST資料，無法用$_POST取得資料，需用file_get_contents("php://input")
	$jsonString = file_get_contents("php://input");
/*
	$jsonString= array(
		"page" => "user",
		"type" => "Select"
	);
    $jsonString = json_encode($jsonString);
*/

	// 回傳訊息狀態
	$status = "000";
	$value = "";
	try{

		if ($jsonString != NULL){
			// 去除UTF-8的BOM標頭
			$jsonString = preg_replace("/\xef\xbb\xbf/",'',$jsonString);
			$data = json_decode($jsonString);

			// 將stdClass轉成array
			$data = (array)$data;

			// 判別是否為合法的識別碼
			$flag = $data['type'];
			if ($flag == "Insert" or $flag == "Select" or $flag == "Update" or $flag == "Delete"){

				// Connect Mysql
				require_once('connMysql.inc.php');
				$connInfo = new SQLConnection();
				$host = $connInfo->getHost();
				$user = $connInfo->getUser();
				$pw = $connInfo->getPw();
				$link_ID = mysql_connect($host, $user, $pw);
				// Connect database
				mysql_select_db($connInfo->getDBName());
				mysql_query("SET NAMES UTF8");

				// Excute SQL string
				require_once('SQLInstruction.inc.php');
				$sql_Instruction = new SQLInstruction();
                require_once('default.inc.php');
				$lib = new LocalLibrary();
				$datetime = $lib->getTime();

                switch ($data['page']){
                case "delicacies":
                    $str = $sql_Instruction->getSQLDelicacies($data, $datetime);
                    break;
                case "delicacies_history":
                    $str = $sql_Instruction->getSQLDelicaciesHistory($data, $datetime);
                    break;
                case "delicacies_score":
                    $str = $sql_Instruction->getSQLDelicaciesScore($data, $datetime);
                    break;
                case "room":
                    $str = $sql_Instruction->getSQLRoom($data, $datetime);
                    break;
                case "room_choice_delicacies":
                    $str = $sql_Instruction->getSQLRoomChoiceDelicacies($data, $datetime);
                    break;
                case "room_choice_eattime":
                    $str = $sql_Instruction->getSQLRoomChoiceEattime($data, $datetime);
                    break;
                case "room_choice_point":
                    $str = $sql_Instruction->getSQLRoomChoicePoint($data, $datetime);
                    break;
                case "user":
                    $str = $sql_Instruction->getSQLUser($data, $datetime);
                    break;
                case "user_friend":
                    //$str = $sql_Instruction->getSQLUserFriend($data, $datetime);
                    break;
                case "user_status":
                    $str = $sql_Instruction->getSQLUserStatus($data, $datetime);
                    break;
                }
				
                // Send the query
				$result = mysql_query($str, $link_ID);

				if ($result){
					// Output result			
					if ($data['type'] == "Select"){
                        
                        //--------------Select return result-------------------------                       
						$sn_index = mysql_num_rows($result);
						for ($index = 0; $index < $sn_index; $index++){
							$arr[$index] = mysql_fetch_array($result);
                            switch ($data['page']){
                                case "delicacies":
                                    $value[$index] = array(
                                        "d_No" => $arr[$index]['d_No'],
                                        "d_Name" => $arr[$index]['d_Name'],
                                        "d_Phone" => $arr[$index]['d_Phone'],
                                        "d_LowPrice" => $arr[$index]['d_LowPrice'],
                                        "d_HighPrice" => $arr[$index]['d_HighPrice'],
                                        "d_OpenTime" => $arr[$index]['d_OpenTime'],
                                        "d_CloseTime" => $arr[$index]['d_CloseTime'],
                                        "d_Address" => $arr[$index]['d_Address'],
                                        "d_Statement" => $arr[$index]['d_Statement'],
                                        "d_CreateUser" => $arr[$index]['d_CreateUser'],
                                        "d_CreateTime" => $arr[$index]['d_CreateTime']
                                    );
                                    $status = "201";
                                    break;
                                case "delicacies_history":
                                    $value[$index] = array(
                                        "dh_No" => $arr[$index]['dh_No'],
                                        "dh_DelicaciesNo" => $arr[$index]['dh_DelicaciesNo'],
                                        "dh_EatTimeNo" => $arr[$index]['dh_EatTimeNo'],
                                        "dh_CreateTime" => $arr[$index]['dh_CreateTime']
                                    );
                                    $status = "202";
                                    break;
                                case "delicacies_score":
                                    $value[$index] = array(
                                        "ds_No" => $arr[$index]['ds_No'],
                                        "ds_DelicaciesNo" => $arr[$index]['ds_DelicaciesNo'],
                                        "ds_Score" => $arr[$index]['ds_Score'],
                                        "ds_Comment" => $arr[$index]['ds_Comment'],
                                        "ds_UserId" => $arr[$index]['ds_UserId'],
                                        "ds_CreateTime" => $arr[$index]['ds_CreateTime']
                                    );
                                    $status = "203";
                                    break;
                                case "room":
                                    $value[$index] = array(
                                        "r_No" => $arr[$index]['r_No'],
                                        "r_UserId" => $arr[$index]['r_UserId'],
                                        "r_DelicaciesNo" => $arr[$index]['r_DelicaciesNo'],
                                        "r_EatTimeNo" => $arr[$index]['r_EatTimeNo'],
                                        "r_CreateTime" => $arr[$index]['r_CreateTime']
                                    );
                                    $status = "301";
                                    break;
                                case "room_choice_delicacies":
									$value[$index] = array(
                                        "rcd_No" => $arr[$index]['rcd_No'],
                                        "rcd_RoomNo" => $arr[$index]['rcd_RoomNo'],
                                        "rcd_UserId" => $arr[$index]['rcd_UserId'],
                                        "rcd_DelicaciesNo" => $arr[$index]['rcd_DelicaciesNo'],
                                        "rcd_CreateTime" => $arr[$index]['rcd_CreateTime']
                                    );
                                    $status = "302";
                                    break;
                                case "room_choice_eattime":
									$value[$index] = array(
                                        "rce_No" => $arr[$index]['rce_No'],
                                        "rce_RoomNo" => $arr[$index]['rce_RoomNo'],
                                        "rce_UserId" => $arr[$index]['rce_UserId'],
                                        "rce_EatTime" => $arr[$index]['rce_EatTime'],
                                        "rce_CreateTime" => $arr[$index]['rce_CreateTime']
                                    );
                                    $status = "303";
                                    break;
                                case "room_choice_point":
									$value[$index] = array(
                                        "rcp_No" => $arr[$index]['rcp_No'],
                                        "rcp_UserId" => $arr[$index]['rcp_UserId'],
                                        "rcp_RoomNo" => $arr[$index]['rcp_RoomNo'],
                                        "rcp_DelicaciesNo" => $arr[$index]['rcp_DelicaciesNo'],
                                        "rcp_EatTimeNo" => $arr[$index]['rcp_EatTimeNo'],
                                        "rcp_CreateTime" => $arr[$index]['rcp_CreateTime']
                                    );
                                    $status = "304";
                                    break;
                                case "user":
                                    $value[$index] = array(
                                        "u_Id" => $arr[$index]['u_Id'],
                                        "u_Phone" => $arr[$index]['u_Phone'],
                                        "u_Name" => $arr[$index]['u_Name'],
                                        "u_CreateTime" => $arr[$index]['u_CreateTime']
                                    );
                                    $status = "401";
                                    break;
                                case "user_friend":
                                    $value[$index] = array(
                                        "uf_No" => $arr[$index]['uf_No'],
                                        "uf_UserIdA" => $arr[$index]['uf_UserIdA'],
                                        "uf_UserIdB" => $arr[$index]['uf_UserIdB'],
                                        "uf_CreateTime" => $arr[$index]['uf_CreateTime']
                                    );
                                    $status = "402";
                                    break;
  								case "user_status":
                                    $value[$index] = array(
                                        "us_No" => $arr[$index]['us_No'],
                                        "us_RoomNo" => $arr[$index]['us_RoomNo'],
                                        "us_UserId" => $arr[$index]['us_UserId'],
                                        "us_Status" => $arr[$index]['us_Status']
                                    );
                                    $status = "403";
                                    break;
                                }
						}						
                        //---------------------------------------
					}else{
						$status = "100";
						$value = $data['type']. " Operation Success";
					}
				}else{
					$status = "101";
					$value = "No result";
				}

				// Close db
				mysql_close($link_ID);

			}else{
				$status = "101";
				$value =  "Operation Error";
			}

		}else{
			$status = "101";
			$value = "JSON is Null";
		}

	}catch(Exception $e){
		$status = "101";
		//value = $e->getMessage();
	}

	// Encode JSON
	// 回傳JSON
	$msg = array(
		"STATUS" => $status,
		"VALUE" => $value		
	);
	$msg = preg_replace("/\\\\u([a-f0-9]{4})/e", "iconv('UCS-4LE','UTF-8',pack('V', hexdec('U$1')))", json_encode($msg));
	echo $msg;
?>
