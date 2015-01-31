<?
class SQLInstruction{

    pubic function getSQLDelicacies($data){
        switch ($data['type']){
        case "Insert":
            return "INSERT INTO jky_delicacies (d_No, d_Name, d_Phone, d_LowPrice, d_HighPrice, d_OpenTime, d_CloseTime, d_Address, d_Statement, d_CreateUser, d_CreateTime)
					VALUES (NULL,'".$data['new_romTitle']."','1','".$data['new_romPassword']."','".$data['new_romCaptain']."')";
        case "Select":
            return "SELECT * FROM jky_delicacies				
					ORDER BY romId DESC";
        case "Update":
            return "UPDATE jky_delicacies
					SET romTitle='".$data['new_romTitle']."',romPassword='".$data['new_romPassword']."'
				 	WHERE romId='".$data['new_romId']."'";
        case "Delete":
            return;
        }            
    }
    
    pubic function getSQLDelicaciesHistory($data){
        switch ($data['type']){
        case "Insert":
            return "INSERT INTO jky_delicacies_history (ds_No, ds_DelicaciesNo, ds_Score, ds_Comment, ds_UserId, ds_CreateTime)
					VALUES (NULL,'".$data['new_ds_DelicaciesNo']."','".$data['new_ds_Scroe']."','".$data['new_ds_Comment']."','".$data['new_ds_UserId']."','datetime')";
        case "Select":
            return;
        case "Update":
            return;
        case "Delete":
            return;
        }            
    }
    
    pubic function getSQLDelicaciesScore($data){
        switch ($data['type']){
        case "Insert":
            return;
        case "Select":
            return;
        case "Update":
            return;
        case "Delete":
            return;
        }            
    }
    
    pubic function getSQLRoom($data){
        switch ($data['type']){
        case "Insert":
            return;
        case "Select":
            return;
        case "Update":
            return;
        case "Delete":
            return;
        }            
    }
    
    pubic function getSQLRoomChoiceDelicacies($data){
        switch ($data['type']){
        case "Insert":
            return;
        case "Select":
            return;
        case "Update":
            return;
        case "Delete":
            return;
        }            
    }
    
    pubic function getSQLRoomChoiceEattime($data){
        switch ($data['type']){
        case "Insert":
            return;
        case "Select":
            return;
        case "Update":
            return;
        case "Delete":
            return;
        }            
    }
    
    pubic function getSQLRoomChoicePoint($data){
        switch ($data['type']){
        case "Insert":
            return;
        case "Select":
            return;
        case "Update":
            return;
        case "Delete":
            return;
        }            
    }
    
    pubic function getSQLUser($data){
        switch ($data['type']){
        case "Insert":
            return;
        case "Select":
            return;
        case "Update":
            return;
        case "Delete":
            return;
        }            
    }
    
    pubic function getSQLUserFriend($data){
        switch ($data['type']){
        case "Insert":
            return;
        case "Select":
            return;
        case "Update":
            return;
        case "Delete":
            return;
        }            
    }
    
    /*
    //
	public function getSQLIdentifyInfo($data){
		return "SELECT * FROM tp_member				
				WHERE memUser='".$data['new_memUser']."' AND memPassword='".$data['new_memPassword']."'";
	}

	// SQL use for Room
	public function getSQLRoom($data, $index){
		
		// 選擇指令動作
		switch ($data['type']){
		case "Insert":
			return "INSERT INTO tp_room (romId, romTitle, romType, romPassword, romCaptain)
					VALUES (NULL,'".$data['new_romTitle']."','1','".$data['new_romPassword']."','".$data['new_romCaptain']."')";			
		case "Select":
			return "SELECT * FROM tp_room				
					ORDER BY romId DESC";						
		case "Update":
			return "UPDATE tp_room
					SET romTitle='".$data['new_romTitle']."',romPassword='".$data['new_romPassword']."'
				 	WHERE romId='".$data['new_romId']."'";			
		case "Delete":
			switch ($index){
			case "1":
				return "DELETE FROM tp_room_object
						WHERE romId='".$data['new_romId']."'";
			case "2":
				return "DELETE FROM tp_room_message
						WHERE mTargetRoom='".$data['new_romId']."'";
			case "3":
				return  "DELETE FROM tp_room
						 WHERE romId='".$data['new_romId']."'";
			case "4":				
				return  "DELETE FROM tp_member_position
						 WHERE romId='".$data['new_romId']."'";
			}
		case "Update_Profile":
			switch ($index){
			case "1":
				return "UPDATE tp_member
						SET tp_member.memName='".$data['new_memName']."'
						WHERE tp_member.memUser='".$data['new_memUser']."'";
			case "2":
				return "UPDATE tp_member_position
						SET tp_member_position.pType='".$data['new_pType']."'
						WHERE tp_member.memUser='".$data['new_memUser']."'";
			}
		default:
			return;
		}
		
	}	

	public function getSQLRoomMessage($data){

		switch ($data['type']){
		case "Insert":
			// Get Local time
			require_once('_Default.php');
			$lib = new LocalLibrary();
			$datetime = $lib->getTime();
			return "INSERT INTO tp_room_message (no, memUser, mTargetRoom, mType, mMessage, mSendTime)
					VALUES (NULL,'".$data['new_memUser']."','".$data['new_mTargetRoom']."','".$data['new_mType']."','".$data['new_mMessage']."','$datetime')";
		case "Select":
			return "SELECT tp_room_message.*,tp_member.memName FROM tp_member join tp_room_message
					WHERE tp_room_message.mTargetRoom='".$data['new_mTargetRoom']."' AND tp_room_message.memUser=tp_member.memUser
					ORDER BY tp_room_message.mSendTime ASC";
		default:
			return;
		}
	}

	public function getSQLRoomObject($data){
		switch ($data['type']){
		case "Insert":
			// Get Local time
			require_once('_Default.php');
			$lib = new LocalLibrary();
			$datetime = $lib->getTime();
			return "INSERT INTO tp_room_object (no, romId, memUser, oLng, oLat, oType, oDescription, oTime)
					VALUES (NULL,'".$data['new_romId']."','".$data['new_memUser']."','".$data['new_oLng']."','".$data['new_oLat']."','".$data['new_oType']."', '".$data['new_oDescription']."', '$datetime')";
		case "Select":
			return "SELECT no, romId, memUser, oLng, oLat, oType, oDescription, oTime FROM tp_room_object
					WHERE romId='".$data['new_romId']."'
					ORDER BY no DESC";
		case "Delete":
			return "DELETE FROM tp_room_object
					WHERE no='".$data['new_no']."'";
		default:
		
		}
	}

	public function getSQLMember($data){

		switch ($data['type']){
		case "Insert":
			// Get Local time
			require_once('_Default.php');
			$lib = new LocalLibrary();
			$datetime = $lib->getTime();
			return "INSERT INTO tp_member (memId, memUser, memPassword, memName, memType, memLimit, memRegisterTime)
					VALUES (NULL,'".$data['new_memUser']."','".$data['new_memPassword']."','".$data['new_memName']."','0','1','$datetime')";
		case "Select":
			return "SELECT tp_member.* From tp_member				
					WHERE tp_member.memUser='".$data['new_memUser']."'";
		case "Update":
			return "UPDATE tp_member
					SET memName='".$data['new_memName']."'
					WHERE memUser='".$data['new_memUser']."'";
		case "Delete":
			return "DELETE FROM tp_member
					WHERE memUser='".$data['new_memUser']."'";
		case "Update_Type":
			return "UPDATE tp_member
					SET memType='".$data['new_memType']."'
					WHERE memUser='".$data['new_memUser']."'";
		default:
			return;
		}

	}

	public function getSQLMemberPosition($data){
		
		switch ($data['type']){
		case "Insert":
			// Get Local time
			require_once('_Default.php');
			$lib = new LocalLibrary();
			$datetime = $lib->getTime();
			return "INSERT INTO tp_member_position (no, romId, memUser, pLng, pLat, pTime, pAlive) 
					VALUES (NULL,'".$data['new_romId']."','".$data['new_memUser']."','".$data['new_pLng']."','".$data['new_pLat']."','$datetime','1')
					ON DUPLICATE KEY UPDATE romId='".$data['new_romId']."',pLng='".$data['new_pLng']."',pLat='".$data['new_pLat']."',pTime='$datetime',pAlive='1'";			
		case "Select":
			return "SELECT tp_member_position.*, tp_member.memName, tp_member.memType FROM tp_member join tp_member_position 
					WHERE tp_member_position.romId='".$data['new_romId']."' AND tp_member_position.memUser=tp_member.memUser
					ORDER BY no DESC";
		case "Update":
			return "UPDATE tp_member_position
					SET pAlive='0'
					WHERE memUser='".$data['new_memUser']."'";			
		case "Delete":
			return "DELETE FROM tp_room_position
					WHERE memUser='".$data['new_memUser']."'";
		default:
			return;
		}

	}
    
*/
}

?>
