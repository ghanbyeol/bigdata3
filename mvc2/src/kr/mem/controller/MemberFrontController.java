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
		request.setCharacterEncoding("euc-kr"); // �ѱ� ���� ���� ���ڵ�
		// 1. � ��û���� �ľ��ϴ� �۾� --> *.do�� �ޱ⶧���� � insert����list���� ���� �˾ƺ���
		String reqUrl = request.getRequestURI(); // ���ؽ�Ʈ �̸�
		// System.out.println(reqUrl);
		String ctxPath = request.getContextPath(); // ��ü���
		/// System.out.println(ctxPath);
		String command = reqUrl.substring(ctxPath.length()); // Ŭ���̾�Ʈ�� ��û�� ��� comand == key��
		System.out.println(command);
		// �� ��û�� ���� ó���ϱ�(�б��۾�), ��û��ΰ� ������� �ڵ嵵 �������.

		Controller controller = null;
		MemberDAO dao = new MemberDAO();
		String nextView = null;
		
		HandlerMapping mappings = new HandlerMapping();
		controller = mappings.getController(command);
		nextView = controller.requestHandle(request, response);
		
		// �켱 ������ƾ� ������ ������ ���ƿ� �ּ�����? dao����ΰ� ���µ� �Ʒ� else if���� dao�� �Ἥ ������ ���ɲ����� ��������
		//�ڵ鷯 ����  --> �����˹ٻ� --> ~.do ��û�� ���� �ش� pojo�� ��������
		//   key��                 value��
 		// list.do ---> MemberListControlooer
		// insert.do ---> MemberInsertControlooer
		// insertForm.do ---> MemberInsertFormControlooer
		// delete.do ---> MemberDeleteControlooer
		
		// 2�ܰ躯���ϴ� ��ġ
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
		//ȭ�����Դϴ�..
		// View �������� �����ϴ� �κ�
		if(nextView!=null) {
			if(nextView.indexOf("redirect:")!= -1) {
				String[] sp = nextView.split(":"); // sp[0]:sp[1]
				response.sendRedirect(sp[1]); // : �ּ�
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/" + nextView);
				rd.forward(request, response);
			}
		}
	}
}
