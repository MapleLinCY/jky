<?php

	// 若使用CURL來POST資料，無法用$_POST取得資料，需用file_get_contents("php://input")
	$jsonString = file_get_contents("php://input");
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
                                
                switch ($data['page']){
                case "delicacies":
                    //$str = $sql_Instruction->getSQLDelicacies($data);
                    break;
                case "delicacies_history":
                    //$str = $sql_Instruction->getSQLDelicaciesHistory($data);
                    break;
                case "delicacies_score":
                    //$str = $sql_Instruction->getSQLDelicaciesScore($data);
                    break;
                case "room":
                    //$str = $sql_Instruction->getSQLRoom($data);
                    break;
                case "room_choice_delicacies":
                    //$str = $sql_Instruction->getSQLRoomChoiceDelicacies($data);
                    break;
                case "room_choice_eattime":
                    //$str = $sql_Instruction->getSQLRoomChoiceEattime($data);
                    break;
                case "room_choice_point":
                    //$str = $sql_Instruction->getSQLRoomChoicePoint($data);
                    break;
                case "user":
                    //$str = $sql_Instruction->getSQLUser($data);
                    break;
                case "user_friend":
                    //$str = $sql_Instruction->getSQLUserFriend($data);
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
                                    //$str = $sql_Instruction->getSQLDelicacies($data);
                                    break;
                                case "delicacies_history":
                                    //$str = $sql_Instruction->getSQLDelicaciesHistory($data);
                                    break;
                                case "delicacies_score":
                                    //$str = $sql_Instruction->getSQLDelicaciesScore($data);
                                    break;
                                case "room":
                                    $value[$index] = array(
                                        "r_No" => $arr[$index]['r_No'],
                                        "r_UserId" => $arr[$index]['r_UserId'],
                                        "r_DelicaciesNo" => $arr[$index]['r_DelicaciesNo'],
                                        "r_EatTimeNo" => $arr[$index]['r_EatTimeNo'],
                                        "r_CreateTime" => $arr[$index]['r_CreateTime']
                                    );
                                    break;
                                case "room_choice_delicacies":
                                    //$str = $sql_Instruction->getSQLRoomChoiceDelicacies($data);
                                    break;
                                case "room_choice_eattime":
                                    //$str = $sql_Instruction->getSQLRoomChoiceEattime($data);
                                    break;
                                case "room_choice_point":
                                    //$str = $sql_Instruction->getSQLRoomChoicePoint($data);
                                    break;
                                case "user":
                                    //$str = $sql_Instruction->getSQLUser($data);
                                    break;
                                case "user_friend":
                                    //$str = $sql_Instruction->getSQLUserFriend($data);
                                    break;
                                }
						}
                                    
						$status = "301";
                        //---------------------------------------
					}else{
						$status = "100";
						$value = $data['type']. "Operation Success";
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
