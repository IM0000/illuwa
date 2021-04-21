package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.AdminRoomDao;
import dto.Room;

public class AdminRoomDaoImpl implements AdminRoomDao{
	
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	@Override
	public List<Room> selectAll(Connection conn) {
		String sql = "SELECT * FROM room";
		
		List<Room> list = new ArrayList<>();
		Room room = null;
		
		try {
 			ps = conn.prepareStatement(sql);
 			
 			rs = ps.executeQuery();
 			
 			while(rs.next()) {
 				room = new Room();
 				
 				room.setRoomNo(rs.getInt("room_no"));
				room.setUserNo(rs.getInt("user_no"));
				room.setRoomName(rs.getString("room_name"));
				room.setRoomGuests(rs.getInt("room_guests"));
				room.setRoomPrice(rs.getInt("room_price"));
				room.setRoomBedroom(rs.getInt("room_bedroom"));
				room.setRoomBed(rs.getInt("room_bed"));
				room.setRoomAdminCheck(rs.getString("room_admin_check"));
				room.setRoomDesc(rs.getString("room_desc"));
				room.setRoomBathroom(rs.getInt("room_bathroom"));
				room.setRoomType(rs.getString("room_type"));
				room.setRoomRoadAddress(rs.getString("room_road_address"));
				room.setRoomDetailedAddress(rs.getString("room_detailed_address"));
 				
 				list.add(room);
 			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		
		return list;
	}
	
	@Override
	public int updateApprove(Connection conn, Room updateRoom) {
		String sql = "UPDATE room SET room_admin_check = 'Y' WHERE room_no = ?";
		int result = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, updateRoom.getRoomNo());
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return result;
	}
	
	@Override
	public int updateRefuse(Connection conn, Room updateRoom) {
		String sql = "UPDATE room SET room_admin_check = 'N' WHERE room_no = ?";
		int result = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, updateRoom.getRoomNo());
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return result;
	
	}
	
	@Override
	public int updateDelete(Connection conn, Room deleteRoom) {
		String sql = "DELETE room WHERE room_no = ?";
		int result = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, deleteRoom.getRoomNo());
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return result;
	}
}
