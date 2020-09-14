package kr.mem.model;

import java.sql.*;
import java.util.ArrayList;
// jdbc --> mybatis
public class MemberDAO {
	private Connection conn; // Connection 연결객체
	private PreparedStatement ps; // sql문 전송
	private ResultSet rs; // 결과의 집합, 일종의 커서
	// 초기화 블럭
	static {
		try { // DriverManger를 이용해서 db를 사용한다.
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 유지보수를 위해 sql문을 따로 빼주는게 좋다. xml 파일로
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 연결객체 생성
	public Connection getConnect() {// @ 뒤 서브 프로토콜 바뀔수 있다.
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		String user = "hr";
		String password = "hr";
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public int memberInsert(MemberVO vo) {
		// 메서드 이름을 지정된 메서드로 하고싶을때
		conn = getConnect();
		// MyBatis
		String SQL = "insert into tblMem values(seq_num.nextval,?,?,?,?,?)";
		int cnt = -1; // -1 == 실패 , 1 == 성공
		try {
			ps = conn.prepareStatement(SQL);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getPhone());
			ps.setString(3, vo.getAddr());
			ps.setDouble(4, vo.getLat());
			ps.setDouble(5, vo.getLng());
			cnt = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbclose();
		}
		return cnt;
	}

	public ArrayList<MemberVO> memberAllList() {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		conn = getConnect();
		String SQL = "select * from tblMem order by num desc";
		try {
			ps = conn.prepareStatement(SQL);
			rs = ps.executeQuery();
			while (rs.next()) {
				int num = rs.getInt("num");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String addr = rs.getString("addr");
				double lat = rs.getDouble("lat");
				double lng = rs.getDouble("lng");
				MemberVO vo = new MemberVO(num, name, phone, addr, lat, lng);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbclose();
		}
		return list;

	}

	public int memberDelete(int num) {
		conn = getConnect();
		String SQL = "delete from tblMem where num = ?";
		int cnt = -1;
		try {
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, num);
			cnt = ps.executeUpdate(); // 1
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbclose();
		}
		return cnt;
	}

	public void dbclose() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public MemberVO memberContent(int num) {
		MemberVO vo = null;
		conn = getConnect();
		String SQL = "select * from tblMem where num=?";

		try {
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			if (rs.next()) {
				num = rs.getInt("num");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String addr = rs.getString("addr");
				double lat = rs.getDouble("lat");
				double lng = rs.getDouble("lng");
				vo = new MemberVO(num, name, phone, addr, lat, lng);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbclose();
		}
		return vo;
	}

	// 나이먹어서 그래요.. 집에서 해서 그러는게 아니고
	// ㅉ쯧쯧!
	public int memberUpdate(MemberVO vo) {
		conn = getConnect();
		String SQL = "update tblMem set phone=?, addr=? where num=?";
		int cnt = -1;
		try {
			ps = conn.prepareStatement(SQL);
			ps.setString(1, vo.getPhone());
			ps.setString(2, vo.getAddr());
			ps.setInt(3, vo.getNum());
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbclose();
		}
		return cnt;
	}
}
