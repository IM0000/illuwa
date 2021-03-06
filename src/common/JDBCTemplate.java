package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//jdbc 관련 api 
public class JDBCTemplate {
	//드라이버
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	
	//db연결 정보
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAME = "scott";
	private static final String PASSWORD = "tiger";
	
	private JDBCTemplate() {}
	
	private static Connection conn;
	
	public static Connection getConnection() {
		//한번 생성된 객체를 유지한다.
		if(conn == null) {
			try {
				//드라이버 로드
				Class.forName(DRIVER);
				
				//db연결
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				
				//Auto Commit 설정 끄기 (기본값:true)
				conn.setAutoCommit(false);
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	//Connection 객체 닫는 메소드
	public static void close(Connection conn) {
		try {
			if( conn!=null && !conn.isClosed() )
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement st) {
		
		try {
			if( st!=null && !st.isClosed() )
				st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(PreparedStatement ps) {
		try {
			if( ps!=null && !ps.isClosed() )
				ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(ResultSet rs) {
		try {
			if( rs!=null && !rs.isClosed() )
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//commit 수행 메소드
	public static void commit(Connection conn) {
		try {
			if(conn!=null && !conn.isClosed())
				conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//rollback 수행 메소드
	public static void rollback(Connection conn) {
		try {
			if(conn!=null && !conn.isClosed())
				conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
