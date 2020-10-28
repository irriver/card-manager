package org.edwith.webbe.cardmanager.dao;

import org.edwith.webbe.cardmanager.dto.BusinessCard;
import org.edwith.webbe.cardmanager.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BusinessCardManagerDao {
	public List<BusinessCard> searchBusinessCard(String keyword) {
		List<BusinessCard> cardList = new ArrayList<>();
		SimpleDateFormat currDate = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
		String sql = "SELECT * FROM carddb WHERE name like '%" + keyword + "%'";

		try {
				Connection conn = DbUtil.getConnection();
		
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String name = rs.getString(1);
				String phone = rs.getString(2);
				String companyName = rs.getString(3);
				String date = rs.getString(4);

				BusinessCard newCard = new BusinessCard(name, phone, companyName);
				newCard.setCreateDate(currDate.parse(date));

				cardList.add(newCard);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cardList;
	}

	public BusinessCard addBusinessCard(BusinessCard businessCard) {
		// BusinessCard 객체 생성 후 DB에 insert
	}
}
