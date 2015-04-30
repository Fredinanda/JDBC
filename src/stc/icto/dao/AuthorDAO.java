package stc.icto.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import stc.icto.vo.AuthorVO;

public class AuthorDAO {
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		
		//1 드라이버로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//2 연결 생성
		String dbURL ="jdbc:oracle:thin:@localhost:1521:xe";
		conn = DriverManager.getConnection(dbURL, "icto55", "icto55");

		
		return conn;
		
	}

	public List<AuthorVO> fetch() throws ClassNotFoundException, SQLException {
		List<AuthorVO> list = new ArrayList<AuthorVO>();
					
		
			Connection conn = getConnection();

			//3 스테이먼트생성
			Statement stmt = conn.createStatement();
		
			//4 쿼리문날리기
			String sql = "select * from author";
			ResultSet rs = stmt.executeQuery(sql);
			
			//5 결과처리
			while(rs.next()){
				Long id = rs.getLong(1);
				String name = rs.getString(2);
				String bio = rs.getString(3);
				
				AuthorVO vo = new AuthorVO();
				vo.setId(id);
				vo.setName(name);
				vo.setBio(bio);
				
				list.add(vo);
			}
			
			
	
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
	
		
		
		
		return list;
	}
	
	
	public void insert( AuthorVO vo ){
		Connection conn = null;
		PreparedStatement ps = null;
	
		try {
			//1 드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2 연결 생성
			String dbURL ="jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dbURL, "icto55", "icto55");

			//3 쿼리문날리기
			String sql = "insert into AUTHOR values(seq_author.nextval,?,?)";
			ps = conn.prepareStatement(sql);
			
			//4 바인딩
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getBio());
			ps.executeUpdate();
		
		} catch (ClassNotFoundException e) {
			System.out.println( "oracle jdbc 라이브러리가 엄씀 ㅋ" );
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			//정리
			try{
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
