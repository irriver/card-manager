package org.edwith.webbe.cardmanager.dao;

import org.edwith.webbe.cardmanager.dto.BusinessCard;
import org.edwith.webbe.cardmanager.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BusinessCardManagerDao {

	public List<BusinessCard> searchBusinessCard(String keyword) {

		List<BusinessCard> cardList = new ArrayList<>();
		String selectQuery = "SELECT * FROM businesscard WHERE name like '%" + keyword + "%'";

		try {
			Connection conn = DbUtil.getConnection();
			System.out.println("---- in Dao: Connected ----");

			PreparedStatement pstmt = conn.prepareStatement(selectQuery);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString(1);
				String phone = rs.getString(2);
				String companyName = rs.getString(3);

				BusinessCard newCard = new BusinessCard(name, phone, companyName);
				cardList.add(newCard);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cardList;
	}

	public BusinessCard addBusinessCard(BusinessCard businessCard) {
		String insertQuery = "INSERT INTO businesscard (name, phone, company_name, create_date) VALUES (?, ?, ?, sysdate())";

		try {
			Connection conn = DbUtil.getConnection();
			System.out.println("---- Connected ----");

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
}
