package org.edwith.webbe.cardmanager.dao;

import org.edwith.webbe.cardmanager.dto.BusinessCard;
import org.edwith.webbe.cardmanager.util.DbUtil;

import java.awt.dnd.peer.DropTargetPeer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BusinessCardManagerDao {
	//1. 명함 조회: 이름으로
	public List<BusinessCard> searchBusinessCard(String keyword) {

		List<BusinessCard> cardList = new ArrayList<>();
		String selectQuery = "SELECT * FROM businesscard WHERE name like '%" + keyword + "%'";

		try {
			Connection conn = DbUtil.getConnection();
			System.out.println(".... DB Connected");

			PreparedStatement pstmt = conn.prepareStatement(selectQuery);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String companyName = rs.getString("company_name");

				BusinessCard newCard = new BusinessCard(name, phone, companyName);
				cardList.add(newCard);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cardList;
	}
	//2. 명함 추가
	public BusinessCard addBusinessCard(BusinessCard businessCard) {
		String insertQuery = "INSERT INTO businesscard (name, phone, company_name, create_date) VALUES (?, ?, ?, sysdate())";

		try {
			Connection conn = DbUtil.getConnection();
			System.out.println(".... Connected");

			PreparedStatement pstmt = conn.prepareStatement(insertQuery);
			pstmt.setString(1, businessCard.getName());
			pstmt.setString(2, businessCard.getPhone());
			pstmt.setString(3, businessCard.getCompanyName());

			pstmt.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return businessCard;
	}
	
	public BusinessCard updateBusinessCard(BusinessCard newCard) {
		String updateQuery = "UPDATE carddb SET name = ?, phone = ?, company_name = ? WHERE idx = primeKey";
		
		try {
			Connection conn = DbUtil.getConnection();
			System.out.println(".... Connected");
			
			PreparedStatement pstmt = conn.prepareStatement(updateQuery);
			pstmt.setString(1, newCard.getName());
			pstmt.setString(2, newCard.getPhone());
			pstmt.setString(3, newCard.getCompanyName());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newCard;
	}
}
