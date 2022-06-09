package jp.co.aforce.java;

import jp.co.aforce.bean.MemberBean;

public class Check {

	//入力されたパラメータを取得し、nullチェックし、
	//生年月日をInteger化してBeanで返すメソッド
	//不正が発生した場合はExceptionを返す
	public MemberBean put(
			String lastName,
			String firstName,
			String sex,
			String year,
			String month,
			String day,
			String job,
			String phoneNumber,
			String mailAddress
			) throws Exception {

		MemberBean bean = null;

		//生年月日をInteger化
		int yearInt = Integer.parseInt(year);
		int monthInt = Integer.parseInt(month);
		int dayInt = Integer.parseInt(day);

		bean = new MemberBean(
				lastName,
				firstName,
				sex,
				yearInt,
				monthInt,
				dayInt,
				job,
				phoneNumber,
				mailAddress
				);


		return bean;
	}

}
