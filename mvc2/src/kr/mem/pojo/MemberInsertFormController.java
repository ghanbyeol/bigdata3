package kr.mem.pojo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberInsertFormController implements Controller{

	@Override
	public String requestHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//String page = "member/Member.html";
		
		return "member/Member.jsp";  //page  된건가? 내아... 고맙다, member폴더를 WEB-INF 여기로 옮김
	}

}
