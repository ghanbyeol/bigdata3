package kr.mem.pojo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	// FC(front controller)가 해야할 일을 POJO가 대신해주는 메서드
	// 클라이언트의 요청이 오면 하는
	
	public String requestHandle(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException;
	}
