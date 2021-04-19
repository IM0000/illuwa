package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Room;
import dto.RoomFacilityMapping;
import dto.RoomImg;
import service.face.MyRoomService;
import service.impl.MyRoomServiceImpl;

@WebServlet("/host/roomview")
public class MyRoomViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MyRoomService myRoomService = new MyRoomServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MyRoomViewController [GET] 요청");
		
		Room room = myRoomService.getRoomNo(req);
		
		//숙소 정보 요청
		Room roomView = myRoomService.getRoom(room);
		
		//숙소 이미지 정보 요청
		List<RoomImg> roomImgList = myRoomService.getRoomImgList(room);
		
		//숙소 편의시설 정보 요청
		List<String> roomFacList = myRoomService.getRoomFacList(room);
		
		for(RoomImg r : roomImgList) {
			System.out.println(r);
		}
		req.setAttribute("roomView", roomView);
		req.setAttribute("roomImgList", roomImgList);
		req.setAttribute("roomFacList", roomFacList);
		req.getRequestDispatcher("/WEB-INF/views/host/roomView.jsp")
			.forward(req, resp);
		
		
	}
}
