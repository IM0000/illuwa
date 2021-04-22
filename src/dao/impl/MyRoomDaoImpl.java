package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.MyRoomDao;
import dto.Room;
import dto.RoomFacilityMapping;
import dto.RoomImg;
import dto.User;

public class MyRoomDaoImpl implements MyRoomDao {
	
	PreparedStatement ps = null;
	ResultSet rs = null;
	@Override
	public List<Room> selectAll(Connection conn, User user) {
		String sql = "SELECT * FROM room WHERE userNo = ?";
		List<Room> myRoomList = new ArrayList<>();
		Room room = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, user.getUserNo());
			
			rs = ps.executeQuery();
			while(rs.next()) {
				room = new Room();
				room.setRoomNo(rs.getInt("roomNo"));
				room.setUserNo(rs.getInt("userNo"));
				room.setRoomName(rs.getString("roomName"));
				room.setRoomGuests(rs.getInt("roomGuests"));
				room.setRoomPrice(rs.getInt("roomPrice"));
				room.setRoomBedroom(rs.getInt("roomBedroom"));
				room.setRoomBed(rs.getInt("roomBed"));
				room.setRoomAdminCheck(rs.getString("roomAdminCheck"));
				room.setRoomDesc(rs.getString("roomDesc"));
				room.setRoomBathroom(rs.getInt("roomBathroom"));
				room.setRoomType(rs.getString("roomType"));
				room.setRoomRoadAddress(rs.getString("roomRoadAddress"));
				room.setRoomDetailedAddress(rs.getString("roomDetailedAddress"));
				
				myRoomList.add(room);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return myRoomList;
	}
	
	@Override
	public Room selectRoom(Connection conn, Room room) {
		String sql = "SELECT * FROM room WHERE roomNo = ?";
		Room roomView = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, room.getRoomNo());
			
			rs = ps.executeQuery();
			while(rs.next()) {
				roomView = new Room();
				roomView.setRoomNo(rs.getInt("roomNo"));
				roomView.setUserNo(rs.getInt("userNo"));
				roomView.setRoomName(rs.getString("roomName"));
				roomView.setRoomGuests(rs.getInt("roomGuests"));
				roomView.setRoomPrice(rs.getInt("roomPrice"));
				roomView.setRoomBedroom(rs.getInt("roomBedroom"));
				roomView.setRoomBed(rs.getInt("roomBed"));
				roomView.setRoomAdminCheck(rs.getString("roomAdminCheck"));
				roomView.setRoomDesc(rs.getString("roomDesc"));
				roomView.setRoomBathroom(rs.getInt("roomBathroom"));
				roomView.setRoomType(rs.getString("roomType"));
				roomView.setRoomRoadAddress(rs.getString("roomRoadAddress"));
				roomView.setRoomDetailedAddress(rs.getString("roomDetailedAddress"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return roomView;
	}
	
	@Override
	public List<RoomImg> selectRoomImg(Connection conn, Room room) {
		String sql = "SELECT * FROM room_img WHERE room_no = ? ORDER BY room_img_no";
		List<RoomImg> list = new ArrayList<>();
		RoomImg roomImg = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, room.getRoomNo());
			
			rs = ps.executeQuery();
			while(rs.next()) {
				roomImg = new RoomImg();
				
				roomImg.setRoomImgNo(rs.getInt("room_img_no"));
				roomImg.setRoomNo(rs.getInt("room_no"));
				roomImg.setRoomImgFilename(rs.getString("room_img_filename"));
				
				list.add(roomImg);
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
	public List<RoomFacilityMapping> selectRoomFac(Connection conn, Room room) {
		String sql = "SELECT * FROM room_facility_mapping WHERE room_no = ?";
		List<RoomFacilityMapping> list = new ArrayList<>();
		RoomFacilityMapping roomFac = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, room.getRoomNo());
			
			rs = ps.executeQuery();
			while(rs.next()) {
				roomFac = new RoomFacilityMapping();
				
				roomFac.setRoomNo(rs.getInt("room_no"));
				roomFac.setFacilityNo(rs.getInt("facility_no"));
				
				list.add(roomFac);
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
	public int deleteRoom(Connection conn, Room room) {
		String sql = "DELETE room WHERE roomNo = ?";
		int result = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, room.getRoomNo());
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return result;
	}
	
	@Override
	public int deleteRoomImg(Connection conn, Room room) {
		String sql = "DELETE room_img WHERE room_no = ?";
		int result = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, room.getRoomNo());
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return result;
	}
	
	@Override
	public int deleteRoomFac(Connection conn, Room room) {
		String sql = "DELETE room_facility_mapping WHERE room_no = ?";
		int result = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, room.getRoomNo());
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return result;
	}
	
	@Override
	public int updateRoom(Connection conn, Room room) {
		String sql = "UPDATE room SET roomName = ?, roomGuests = ?, roomPrice = ?, roomBedroom = ?"
				+ ", roomBed = ?, roomDesc = ?, roomBathroom = ?, roomType = ?"
				+ ", roomRoadAddress = ?, roomDetailedAddress = ?"
				+ " WHERE roomNo = ?";
		int result = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, room.getRoomName());
			ps.setInt(2, room.getRoomGuests());
			ps.setInt(3, room.getRoomPrice());
			ps.setInt(4, room.getRoomBedroom());
			ps.setInt(5, room.getRoomBed());
			ps.setString(6, room.getRoomDesc());
			ps.setInt(7, room.getRoomBathroom());
			ps.setString(8, room.getRoomType());
			ps.setString(9, room.getRoomRoadAddress());
			ps.setString(10, room.getRoomDetailedAddress());
			ps.setInt(11, room.getRoomNo());
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return result;
	}
}
