package jp.co.aforce.java;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


//会員番号を生成するクラス
public class MemberId {

	public String generateId() {

		// 書式を指定してDateTimeFormatterを取得
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        // 書式を指定してLocalDateTimeのインスタンスを作成する
        LocalDateTime dt = LocalDateTime.now();
        // システム日付を、DateTimeFormatterで指定したフォーマットで文字列に変換
        String str = dt.format(dtf);

		return "A" + str;
	}

}
