package kr.mem.pojo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mem.model.MemberDAO;
import kr.mem.model.MemberVO;

public class MemberListController implements Controller {

	@Override
	public String requestHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. db에 연결해서 멤버 전체 리스트를 가져온다
		MemberDAO dao = new MemberDAO();
		ArrayList<MemberVO> list = dao.memberAllList();
		// 객체바인딩
		request.setAttribute("list", list);
		// View페이지 ->member / memberList.jsp

		return "member/memberList.jsp"; // member/리턴된 값
		
	}

}
