package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Room;
import service.face.MyRoomService;
import service.impl.MyRoomServiceImpl;

@WebServlet("/host/roomlist")
public class MyRoomListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MyRoomService myRoomService = new MyRoomServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[test] MyRoomListController [GET] 요청");
		
		//==== [test]세션에 유저번호 저장 ====
		HttpSession session = req.getSession();
		session.setAttribute("userno", 1001);
		//=============================
		
		//본인의 숙소 리스트 가져오기
		List<Room> myRoomList = myRoomService.getRoomList(req);
		req.setAttribute("myRoomList", myRoomList);
		req.getRequestDispatcher("/WEB-INF/views/host/roomList.jsp")
			.forward(req, resp);
		
		
	}
}