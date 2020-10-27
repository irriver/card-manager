package org.edwith.webbe.cardmanager.dao;

import org.edwith.webbe.cardmanager.dto.BusinessCard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BusinessCardManagerDao {
    public List<BusinessCard> searchBusinessCard(String keyword){
    	//'이름'을 받아서 해당 이름의 명함 출력
    	
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	String dbUrl = "jdbc:localhost/3306/carddb?CharEncoding=UTF-8&&ServerTimeZone=UTC";
    	String dbUser = "manager";
    	String dbPw = "card123";
    	String sql = "SELECT * FROM CARDDB.CARD WHERE NAME = ?";	//조건의 인자로 인풋을 넣으려면?? 인자는 like으로 들어옴. 일치하는 모든 이름을 검색할 것.
    	
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPw);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			List<BusinessCard> list = new ArrayList<BusinessCard>();
			while (rs.next()) {
				list.add(1, rs.getString(1));
				list.add(2, rs.getString(2));
				list.add(3, rs.getString(3));
				list.add(4, rs.getString(4));
			}
			//list를 반복해서 출력
			
		} catch (Exception e) {
			// TODO: handle exception
		}
    }

    public BusinessCard addBusinessCard(BusinessCard businessCard){
	// BusinessCard 객체 생성 후 DB에 insert
    }
}
