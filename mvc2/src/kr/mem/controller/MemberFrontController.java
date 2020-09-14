package kr.mem.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.SendResult;

import kr.mem.model.MemberDAO;
import kr.mem.model.MemberVO;
import kr.mem.pojo.Controller;
import kr.mem.pojo.MemberDeleteController;
import kr.mem.pojo.MemberInsertController;
import kr.mem.pojo.MemberInsertFormController;
import kr.mem.pojo.MemberListController;
import sun.rmi.runtime.NewThreadAction;

public class MemberFrontController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr"); // 한글 깨짐 방지 인코딩
		// 1. 어떤 요청인지 파악하는 작업 --> *.do로 받기때문에 어떤 insert인지list인지 뭔지 알아보기
		String reqUrl = request.getRequestURI(); // 컨텍스트 이름
		// System.out.println(reqUrl);
		String ctxPath = request.getContextPath(); // 전체경로
		/// System.out.println(ctxPath);
		String command = reqUrl.substring(ctxPath.length()); // 클라이언트가 요청한 명령 comand == key값
		System.out.println(command);
		// 각 요청에 따라 처리하기(분기작업), 요청경로가 길어지면 코드도 길어진다.

		Controller controller = null;
		MemberDAO dao = new MemberDAO();
		String nextView = null;
		
		HandlerMapping mappings = new HandlerMapping();
		controller = mappings.getController(command);
		nextView = controller.requestHandle(request, response);
		
		// 우선 살려놓아야 오류가 없을거 같아요 주석때문? dao선언부가 없는데 아래 else if에선 dao를 써서 오류가 난걸꺼에요 ㅇㅋㅇㅋ
		//핸들러 매핑  --> 전문알바생 --> ~.do 요청이 오면 해당 pojo를 연결해줌
		//   key값                 value값
 		// list.do ---> MemberListControlooer
		// insert.do ---> MemberInsertControlooer
		// insertForm.do ---> MemberInsertFormControlooer
		// delete.do ---> MemberDeleteControlooer
		
		// 2단계변형하는 위치
		/*
		if (command.equals("/list.do")) {
			controller = new MemberListController();
			nextView = controller.requestHandle(request, response); //			
		} else if (command.equals("/insert.do")) {
			controller = new MemberInsertController();
			nextView = controller.requestHandle(request, response);
		} else if (command.equals("/insertForm.do")) {
			controller = new MemberInsertFormController();
			nextView = controller.requestHandle(request, response);
		} 
		else if (command.equals("/delete.do")) {
			controller = new MemberDeleteController();
			nextView = controller.requestHandle(request, response);
		} 
		*/
		
// ------------------------------------------------------------------------------
// controller(servlet) -> 1. front controller (servlet)  2. controller(java) --> action, pojo 
		//화이팅입니다..
		// View 페이지로 연동하는 부분
		if(nextView!=null) {
			if(nextView.indexOf("redirect:")!= -1) {
				String[] sp = nextView.split(":"); // sp[0]:sp[1]
				response.sendRedirect(sp[1]); // : 주소
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/" + nextView);
				rd.forward(request, response);
			}
		}
	}
}
