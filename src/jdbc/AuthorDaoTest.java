package jdbc;

import java.sql.SQLException;
import java.util.List;

import stc.icto.dao.AuthorDAO;
import stc.icto.vo.AuthorVO;

public class AuthorDaoTest {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		 //insertTest();
		selectTest();
	}
	
	public static void insertTest(){
		
		AuthorDAO dao = new AuthorDAO();
		
		//1. vo생성 하나입력
		AuthorVO vo = new AuthorVO();
		vo.setName("장자");
		vo.setBio("");
		dao.insert(vo);
		
		
		//2 입력2
		AuthorVO vo2 = new AuthorVO();
		vo2.setName("순자");
		vo2.setBio("");
		dao.insert(vo2);
	}
	public static void selectTest() throws ClassNotFoundException, SQLException{
		AuthorDAO dao = new AuthorDAO();
		List<AuthorVO>list = dao.fetch();
		
		for (AuthorVO vo : list) {
			System.out.println(vo.getId() +" : "+ vo.getName() +" : "+ vo.getBio());
		}
	}

}
