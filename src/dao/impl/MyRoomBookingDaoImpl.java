package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.JDBCTemplate;
import dao.face.MyRoomBookingDao;
import dto.Room;
import util.BookingPaging;

public class MyRoomBookingDaoImpl implements MyRoomBookingDao {
	
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	@Override
	public int selectCntAll(Connection conn, int hostNo) {
		String sql = "SELECT count(*) cnt FROM booking"
				+ " WHERE host_no = ?";
		
		//호스트의 총 예약 수
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, hostNo);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		
		return cnt;
	}
	
	@Override
	public List<Map<String, Object>> selectByPaging(Connection conn, int hostNo, BookingPaging paging) {
		String sql = "SELECT * FROM (" 
					+" SELECT rownum rnum, B.* FROM(" 
					+" 	SELECT"
					+" 		b.booking_no 예약번호"
					+" 		, r.room_name 숙소명"
					+" 		, u.user_name 예약자이름"
					+" 		, u.user_phone 예약자연락처"
					+" 		, b.booking_checkin 체크인"
					+" 		, b.booking_checkout 체크아웃"
					+" 		, b.booking_message 예약자메시지"
					+" 		, b.booking_status 예약상태"
					+" 		, u.user_email 예약자이메일"
					+" 	FROM booking b, room r, users u"
					+"	WHERE host_no = ? AND b.room_no = r.room_no AND b.user_no = u.user_no" 
					+" 	ORDER BY b.booking_checkin DESC) B"
					+" ) BOOK"
					+" WHERE rnum between ? and ?";
		
		//정보를 담을 Map
		Map<String, Object> map = null;
		
		//정보 리스트
		List<Map<String, Object>> list = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, hostNo);
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				map = new HashMap<String, Object>();
				
				map.put("예약번호", rs.getInt("예약번호"));
				map.put("숙소명", rs.getString("숙소명"));
				map.put("예약자이름", rs.getString("예약자이름"));
				map.put("예약자연락처", rs.getString("예약자연락처"));
				map.put("체크인", rs.getString("체크인"));
				map.put("체크아웃", rs.getString("체크아웃"));
				map.put("예약자메시지", rs.getString("예약자메시지"));
				map.put("예약상태", rs.getString("예약상태"));
				map.put("예약자이메일", rs.getString("예약자이메일"));
				
				list.add(map);
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
	public int updateStatus(Connection conn, int bookingno, String bookingStatus) {
		String sql = "UPDATE booking SET booking_status = ?"
				+ " WHERE booking_no = ?";
		
		int result = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, bookingStatus);
			ps.setInt(2, bookingno);
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return result;
	}
	
	@Override
	public int deleteBooking(Connection conn, Room room) {
		String sql = "DELETE booking WHERE booking_no = ?";
		
		int result = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, room.getRoomNo());
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return result;
	}
}
