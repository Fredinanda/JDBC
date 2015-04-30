package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class prepareStatementTest1 {
	
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		Scanner key = new Scanner(System.in);
	
		
		try {
			
			System.out.println("검색어 : ");
			String name = key.next();
			
			//1 드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2 연결 생성
			String dbURL ="jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dbURL, "hr", "hr");

			
		
			
			//4 쿼리문날리기
			String sql = "select first_name, last_name, email, phone_number, hire_date from employees where first_name like ? or last_name like ?";
			ps = conn.prepareStatement(sql);
			
			//바인딩
			ps.setString(1, "%"+name+"%");
			ps.setString(2, "%"+name+"%");
			
			
			
			rs = ps.executeQuery();
		
			
			
			
			
			
			while(rs.next()){
				String f_name = rs.getString(1);
				String l_name = rs.getString(2);
				String email = rs.getString(3);
				String phone = rs.getString(4);
				String hiredate = rs.getString(5);
				
				System.out.println( f_name+" "+l_name+" "+email+" "+phone+" "+hiredate);
			}

			
			
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
				if(ps !=null){
					ps.close();
				}
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e){
			}
		}
		
	}

}