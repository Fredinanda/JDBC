package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnectTest {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			//1 드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2 연결 생성
			String dbURL ="jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dbURL, "hr", "hr");

			
			//3 스테이먼트생성
			stmt = conn.createStatement();
			
			//4 쿼리문날리기
			String sql = "select * from departments";
			rs = stmt.executeQuery(sql);
			
			
			while(rs.next()){
				Long id = rs.getLong(1);
				String name = rs.getString(2);
				
				System.out.println( id + " "+name);
			}

			System.out.println("연결 성공");
			
			
			
		} catch (ClassNotFoundException e) {
			System.out.println( "oracle jdbc 라이브러리가 엄씀 ㅋ" );
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			//정리
			
			try{
				if(rs != null){
					rs.close();
				}
				if(stmt !=null){
					stmt.close();
				}
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e){
			}
		}
		
	}

}
