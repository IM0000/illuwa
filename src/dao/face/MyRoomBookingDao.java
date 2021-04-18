package dao.face;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import dto.Room;
import util.BookingPaging;

public interface MyRoomBookingDao {
	/**
	 * 호스트의 예약 갯수를 조회
	 * @param conn
	 * @param hostNo - 접속한 호스트의 회원번호
	 * @return 예약 갯수
	 */
	public int selectCntAll(Connection conn, int hostNo);
	/**
	 * 호스트의 예약을 전체조회 (페이징처리)
	 * @param conn 
	 * @param hostNo - 호스트의 회원번호
	 * @param paging 
	 * @return 예약정보를 담은 리스트
	 */
	public List<Map<String, Object>> selectByPaging(Connection conn, int hostNo, BookingPaging paging);
	/**
	 * 예약상태를 수정
	 * @param conn
	 * @param bookingno - 예약번호
	 * @param bookingStatus - 예약상태
	 * @return 예약 수정 결과
	 */
	public int updateStatus(Connection conn, int bookingno, String bookingStatus);
	
	/**
	 * 해당 숙소번호의 예약들을 삭제
	 * @param conn
	 * @param room - 해당 숙소 번호
	 * @return 삭제결과 반환
	 */
	public int deleteBooking(Connection conn, Room room);

}
