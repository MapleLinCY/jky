<?
class SQLInstruction{

    public function getSQLDelicacies($data, $datetime){
        switch ($data['type']){
        case "Insert":
            return "INSERT INTO jky_delicacies (d_No, d_Name, d_Phone, d_LowPrice, d_HighPrice, d_OpenTime, d_CloseTime, d_Address, d_Statement, d_CreateUser, d_CreateTime)
					VALUES (NULL,'".$data['new_d_Name']."','".$data['new_d_Phone']."','".$data['new_d_LowPrice']."','".$data['new_d_HighPrice']."', '".$data['new_d_OpenTime']."','".$data['new_d_CloseTime']."', '".$data['new_d_Address']."', '".$data['new_d_Statement']."', '".$data['new_d_CrateUser']."', '$datetime')";
        case "Select":
            return "SELECT * FROM jky_delicacies				
					ORDER BY d_No DESC";
        case "Update":
            return;
        case "Delete":
            return;
        }            
    }
    
    public function getSQLDelicaciesHistory($data, $datetime){
        switch ($data['type']){
        case "Insert":
            return "INSERT INTO jky_delicacies_history (dh_No, dh_DelicaciesNo, dh_EatTimeNo, dh_CreateTime)
					VALUES (NULL,'".$data['new_dh_DelicaciesNo']."','".$data['new_dh_EatTimeNo']."','$datetime')";
        case "Select":
            return "SELECT * FROM jky_delicacies				
					ORDER BY dh_No DESC";
        case "Update":
            return;
        case "Delete":
            return;
        }            
    }
    
    public function getSQLDelicaciesScore($data, $datetime){
        switch ($data['type']){
        case "Insert":
            return "INSERT INTO jky_delicacies_score (ds_No, ds_DelicaciesNo, ds_Score, ds_Comment, ds_UserId, ds_CreateTime)
					VALUES (NULL,'".$data['new_ds_DelicaciesNo']."','".$data['new_ds_Score']."','".$data['new_ds_Comment']."','".$data['new_ds_UserId']."','$datetime')";
        case "Select":
            return "SELECT * FROM jky_delicacies_score				
					ORDER BY ds_No DESC";
        case "Update":
            return;
        case "Delete":
            return;
        }            
    }
    
    public function getSQLRoom($data, $datetime){
        switch ($data['type']){
        case "Insert":
            return "INSERT INTO shufeng_jioukaoyao.jky_room (`r_No`, `r_UserId`, `r_DelicaciesNo`, `r_EatTimeNo`, `r_Counter`, `r_CreateTime`) 
            		VALUES (NULL, '".$data['new_r_UserId']."', '".$data['new_r_DelicaiesNo']."', '".$data['new_r_EatTimeNo']."', '".$data['new_r_Counter']."', '$datetime')";
        case "Select":
            return "SELECT * FROM shufeng_jioukaoyao.jky_room			
					ORDER BY r_No DESC";
        case "Update":
            return;
        case "Delete":
            return;
        }            
    }
    
    public function getSQLRoomChoiceDelicacies($data, $datetime){
        switch ($data['type']){
        case "Insert":
            return "INSERT INTO shufeng_jioukaoyao.jky_room_choice_delicacies (`rcd_No`, `rcd_RoomNo`, `rcd_UserId`, `rcd_DelicaciesNo`, `rcd_CreateTime`) 
            		VALUES (NULL, '".$data['new_rcd_RoomNo']."', '".$data['new_rcd_UserId']."','".$data['new_rcd_DelicaiesNo']."', '$datetime')";
        case "Select":
            return "SELECT * FROM shufeng_jioukaoyao.jky_room_choice_delicacies			
					ORDER BY rcd_No DESC";
        case "Update":
            return;
        case "Delete":
            return;
        }            
    }
    
    public function getSQLRoomChoiceEattime($data, $datetime){
        switch ($data['type']){
        case "Insert":
            return "INSERT INTO shufeng_jioukaoyao.jky_room_choice_eattime (`rce_No`, `rce_RoomNo`, `rce_UserId`, `rce_EatTime`, `rce_CreateTime`) 
            		VALUES (NULL, '".$data['new_rce_RoomNo']."', '".$data['new_rce_UserId']."','".$data['new_rce_EatTime']."', '$datetime')";
        case "Select":
            return "SELECT * FROM shufeng_jioukaoyao.jky_room_choice_eattime		
					ORDER BY rce_No DESC";
        case "Update":
            return;
        case "Delete":
            return;
        }            
    }
    
    public function getSQLRoomChoicePoint($data, $datetime){
        switch ($data['type']){
        case "Insert":
            return "INSERT INTO shufeng_jioukaoyao.jky_room_choice_eattime (`rcp_No`, `rcp_UserId`, `rcp_RoomNo`, `rcp_DelicaciesNo`, `rcp_EatTimeNo`, `rcp_CreateTime`) 
            		VALUES (NULL, '".$data['new_rcp_UserId']."', '".$data['new_rcp_RoomId']."',  '".$data['new_rcp_DelicaciesNo']."','".$data['new_rcp_EatTimeNo']."', '$datetime')";
        case "Select":
            return "SELECT * FROM shufeng_jioukaoyao.jky_room_choice_point		
					ORDER BY rcp_No DESC";
        case "Update":
            return;
        case "Delete":
            return;
        }            
    }
    
    public function getSQLUser($data, $datetime){
        switch ($data['type']){
        case "Insert":
            return "INSERT INTO shufeng_jioukaoyao.jky_user (`u_Id`, `u_Phone`, `u_Name`, `u_Password`, `u_Picture`, `u_CreateTime`) 
            		VALUES (NULL, '".$data['new_u_Phone']."', '".$data['new_u_Name']."', '".$data['new_u_Password']."', NULL, '$datetime')";
        case "Select":
            return "SELECT u_Id, u_Phone, u_Name, u_CreateTime FROM shufeng_jioukaoyao.jky_user 
            		ORDER BY u_Id DESC";
        case "Update":
            return;
        case "Delete":
            return;
        }            
    }
    
    public function getSQLUserFriend($data, $datetime){
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
    
    public function getSQLUserStatus($data, $datetime){
        switch ($data['type']){
        case "Insert":
            return "INSERT INTO shufeng_jioukaoyao.jky_user_status (`us_No`, `us_RoomId`, `us_UserId`, `us_Status`) 
            		VALUES (NULL, '".$data['new_us_RoomId']."', '".$data['new_us_UserId']."', '1')";
        case "Select":
            return "SELECT* FROM shufeng_jioukaoyao.jky_user_status 
            		ORDER BY us_No DESC";
        case "Update":
            return "UPDATE shufeng_jioukaoyao.jky_user_status 
					SET us_Status='".$data['new_us_Status']."'
				 	WHERE us_UserId='".$data['new_us_UserId']."'";
        case "Delete":
            return;
        }            
    }

    /*
 

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
