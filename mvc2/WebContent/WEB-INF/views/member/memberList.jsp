<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*"%>
<%@ page import="kr.mem.model.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cpath" value="${pageContext.request.contextPath}"/>
<%
	ArrayList<MemberVO> list = (ArrayList<MemberVO>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
 function insertForm() {
	location.href="${cpath}/insertForm.do";
}
 function deleteFn(num) {
	 //�ι�° ��Ÿ delete().do~       ????????
	location.href="${cpath}/delete.do?num="+num;	
}
</script>
</head>
<body>
	-ȸ������Ʈ ����(MVC-STEP1)-
	<table width="1000" border='1' color="black">
		<tr>
			<td>��ȣ</td>
			<td>�̸�</td>
			<td>��ȭ��ȣ</td>
			<td>�ּ�</td>
			<td>����</td>
			<td>�浵</td>
			<td>����</td>
			
		</tr>

	<c:forEach var="vo" items="${list}">
			<tr>
				<td>${vo.num}</td>
				<td><a href="${cpath}/content.do?num=${vo.num}">${vo.name}</a></td>
				<td>${vo.phone}</td>
				<td>${vo.addr}</td>
				<td>${vo.lat}</td>
				<td>${vo.lng}</td>
				<td><input type="button" value="����" onclick="deleteFn(${vo.num})"/></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="6" align="left">
				<input type="button" value="ȸ������" onclick="insertForm()"/>
			</td>
		</tr>
	</table>
</body>
</html>