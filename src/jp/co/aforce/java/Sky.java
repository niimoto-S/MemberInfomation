package jp.co.aforce.java;

//値が空の場合、空の要素名を返す
public class Sky {

	public String nullCheck(
			String lastName,
			String firstName,
			String sex,
			String year,
			String month,
			String day,
			String job,
			String phoneNumber,
			String mailAddress
			) {
		if(lastName.equals("")) {
			return "姓";
		}
		if(firstName.equals("")) {
			return "名";
		}
		try {
			if(sex.equals("")) {
				return "性別";
			}
		} catch (Exception e) {
			return "性別";
		}
		if(year.equals("")) {
			return "年";
		}
		if(month.equals("")) {
			return "月";
		}
		if(day.equals("")) {
			return "日";
		}
		if(job.equals("")) {
			return "職業";
		}
		if(phoneNumber.equals("")) {
			return "電話番号";
		}
		if(mailAddress.equals("")) {
			return "メールアドレス";
		}

		return "";

	}

	public String nullCheckById(String memberId) {
		if(memberId.equals("")) {
			return "会員番号";
		}
		return "";
	}

}
