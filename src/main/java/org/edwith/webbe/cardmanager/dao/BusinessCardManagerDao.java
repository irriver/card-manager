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
		String selectQuery = "SELECT * FROM businesscard WHERE name like '%" + keyword + "%'";

		try {
			Connection conn = DbUtil.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(selectQuery);
			ResultSet rs = pstmt.executeQuery();
			// rs에는 해당 keyword에 걸리는 모든 명함정보가 담길 것.
			// name, phone, company, date가 한 줄 씩 출력
			while (rs.next()) {
				// 쿼리 결과로 반환된 결과로 dto 객체 생성하기
				String name = rs.getString(1);
				String phone = rs.getString(2);
				String companyName = rs.getString(3);
				String date = rs.getString(4);

				BusinessCard newCard = new BusinessCard(name, phone, companyName);
				newCard.setCreateDate(currDate.parse(date)); // sysdate로 자동입력 되게는?
				// 만든 card dto 객체를 '조회목록'에 추가
				cardList.add(newCard);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cardList;
	}

	// 방금 각 항목의 input값을 받아서 businessCard dto 객체를 하나 만들고, 그걸 인자로 넘김
	// 나는 이제 인자로 들어온 dto 객체를 다시 db로 넘겨서 insert 해야 함
	public BusinessCard addBusinessCard(BusinessCard businessCard) {
		// BusinessCard 객체 생성 후 DB에 insert
		SimpleDateFormat currDate = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
		String insertQuery = "INSERT INTO card (name, phone, company_name, create_date) VALUES (?, ?, ?, ?)";

		try {
			Connection conn = DbUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(insertQuery);
			// alt + shift + A => column line editing
			pstmt.setString(1, businessCard.getName());
			pstmt.setString(2, businessCard.getPhone());
			pstmt.setString(3, businessCard.getCompanyName());
			pstmt.setString(4, currDate.format(businessCard.getCreateDate()));

			pstmt.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return businessCard;
	}
}
