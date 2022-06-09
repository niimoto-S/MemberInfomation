package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.aforce.Parameter.MemberInfo;
import jp.co.aforce.bean.MemberBean;
import jp.co.aforce.java.MemberId;

public class MemberDAO extends DAO {


	//重複した値を持つ会員が存在しないかをチェック
	public int search(MemberBean bean) throws Exception {

		Connection con = getConnection();
		String sql = "select count(*) from "
				+ MemberInfo.MEMBER_INFO
				+ " where "
				+ MemberInfo.LAST_NAME + " = ?"
				+ " and "
				+ MemberInfo.FIRST_NAME + " = ?"
				+ " and "
				+ MemberInfo.SEX + " = ?"
				+ " and "
				+ MemberInfo.BIRTH_YEAR + " = ?"
				+ " and "
				+ MemberInfo.BIRTH_MONTH + " = ?"
				+ " and "
				+ MemberInfo.BIRTH_DAY + " = ?"
				+ " and "
				+ MemberInfo.JOB + " = ?"
				+ " and "
				+ MemberInfo.PHONE_NUMBER + " = ?"
				+ " and "
				+ MemberInfo.MAIL_ADDRESS + " = ?"
				;

		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, bean.getLastName());
		st.setString(2, bean.getFirstName());
		st.setString(3, bean.getSex());
		st.setInt(4, bean.getYear());
		st.setInt(5, bean.getMonth());
		st.setInt(6, bean.getDay());
		st.setString(7, bean.getJob());
		st.setString(8, bean.getPhoneNumber());
		st.setString(9, bean.getMailAddress());

		ResultSet rs = st.executeQuery();

		int count = 0;
		while(rs.next()) {
			count = rs.getInt("count(*)");
		}
		st.close();
		con.close();

		return count;
	}

	//新規会員登録
	public void insert(MemberBean bean) throws SQLException, Exception {

		Connection con = getConnection();

		String sql = "insert into member_info values(? ,?, ?, ?, ?, ?, ?, ?, ?, ?)";
		MemberId memberId = new MemberId();
		String id = memberId.generateId();

		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, id);
		st.setString(2, bean.getLastName());
		st.setString(3, bean.getFirstName());
		st.setString(4, bean.getSex());
		st.setInt(5, bean.getYear());
		st.setInt(6, bean.getMonth());
		st.setInt(7, bean.getDay());
		st.setString(8, bean.getJob());
		st.setString(9, bean.getPhoneNumber());
		st.setString(10, bean.getMailAddress());

		st.executeUpdate();

		st.close();
		con.close();

	}

	//会員番号で会員を検索
	public MemberBean findByMemberId(String memberId) throws SQLException, Exception {

		MemberBean bean = null;

		Connection con = getConnection();
		String sql = "select "
				+ MemberInfo.LAST_NAME + ","
				+ MemberInfo.FIRST_NAME + ","
				+ MemberInfo.SEX + ","
				+ MemberInfo.BIRTH_YEAR + ","
				+ MemberInfo.BIRTH_MONTH + ","
				+ MemberInfo.BIRTH_DAY + ","
				+ MemberInfo.JOB + ","
				+ MemberInfo.PHONE_NUMBER + ","
				+ MemberInfo.MAIL_ADDRESS
				+ " from "
				+ MemberInfo.MEMBER_INFO
				+ " where "
				+ MemberInfo.MEMBER_ID
				+ " = ?";

		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, memberId);
		ResultSet rs = st.executeQuery();

		while(rs.next()) {
			bean = new MemberBean(
					rs.getString(MemberInfo.LAST_NAME),
					rs.getString(MemberInfo.FIRST_NAME),
					rs.getString(MemberInfo.SEX),
					rs.getInt(MemberInfo.BIRTH_YEAR),
					rs.getInt(MemberInfo.BIRTH_MONTH),
					rs.getInt(MemberInfo.BIRTH_DAY),
					rs.getString(MemberInfo.JOB),
					rs.getString(MemberInfo.PHONE_NUMBER),
					rs.getString(MemberInfo.MAIL_ADDRESS)
					);
		}
		st.close();
		con.close();

		return bean;
	}

	//会員情報を更新
	public void update(MemberBean bean, String memberId) throws SQLException, Exception {

		Connection con = getConnection();

		String sql =
				"update "
				+ MemberInfo.MEMBER_INFO
				+ " set "
				+ MemberInfo.LAST_NAME + " = ?,"
				+ MemberInfo.FIRST_NAME + " = ?,"
				+ MemberInfo.SEX + " = ?,"
				+ MemberInfo.BIRTH_YEAR + " = ?,"
				+ MemberInfo.BIRTH_MONTH + " = ?,"
				+ MemberInfo.BIRTH_DAY + " = ?,"
				+ MemberInfo.JOB + " = ?,"
				+ MemberInfo.PHONE_NUMBER + " = ?,"
				+ MemberInfo.MAIL_ADDRESS + " = ?"
				+ " where "
				+ MemberInfo.MEMBER_ID + " = ?";
				;

		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, bean.getLastName());
		st.setString(2, bean.getFirstName());
		st.setString(3, bean.getSex());
		st.setInt(4, bean.getYear());
		st.setInt(5, bean.getMonth());
		st.setInt(6, bean.getDay());
		st.setString(7, bean.getJob());
		st.setString(8, bean.getPhoneNumber());
		st.setString(9, bean.getMailAddress());
		st.setString(10, memberId);

		st.executeUpdate();

		st.close();
		con.close();
	}

	//会員情報を削除
	public void delete(String memberId) throws Exception {
		Connection con = getConnection();

		String sql =
				"delete"
				+ " from "
				+ MemberInfo.MEMBER_INFO
				+ " where "
				+ MemberInfo.MEMBER_ID
				+ " = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, memberId);

		st.executeUpdate();

		st.close();
		con.close();
	}

}
