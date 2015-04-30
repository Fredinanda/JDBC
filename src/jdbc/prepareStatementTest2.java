package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class prepareStatementTest2 {
	
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
	
		
		try {
			
			Scanner key = new Scanner(System.in);
			System.out.println("월급 최소 최대값 입력 :  ");
			int min = key.nextInt();
			int max = key.nextInt();
			
			//1 드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2 연결 생성
			String dbURL ="jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dbURL, "hr", "hr");

			
		
			
			//4 쿼리문날리기
			String sql = "SELECT first_name, last_name, salary from employees where salary >? and salary < ? order by salary asc";
			ps = conn.prepareStatement(sql);
			
			//바인딩
			ps.setInt(1, min);
			ps.setInt(2, max);
			
			
			
			rs = ps.executeQuery();
		
			
			
			
			
			
			while(rs.next()){
				String f_name = rs.getString(1);
				String l_name = rs.getString(2);
				String salary = rs.getString(3);
		
				
				System.out.println( f_name+" "+l_name+" "+salary);
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