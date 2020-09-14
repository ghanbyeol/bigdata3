package kr.mem.pojo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mem.model.MemberDAO;
import kr.mem.model.MemberVO;

public class MemberInsertController implements Controller{
// 모든 포조들이 가지고있어야할 공통의 메서드를 인터페이스로 만들어준다.

	@Override
	public String requestHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cpath = request.getContextPath();
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		String addr=request.getParameter("addr");
		MemberVO vo = new MemberVO();
		vo.setName(name);
		vo.setPhone(phone);
		vo.setAddr(addr);
		MemberDAO dao = new MemberDAO();
		int cnt = dao.memberInsert(vo);
		String page = null;
		if(cnt>0) {
			page="redirect:"+cpath+"/list.do";
		}else {
			throw new ServletException("error");
		} // 이거ㄴㅔ 잘라내기를 안했구나 쌤이 밑에껀 남겨놨다가 실습때 지우셨어요
		return page;
	}
}
