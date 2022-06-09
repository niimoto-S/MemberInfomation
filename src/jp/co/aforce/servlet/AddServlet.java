package jp.co.aforce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.Parameter.Messages;
import jp.co.aforce.bean.MemberBean;
import jp.co.aforce.dao.MemberDAO;
import jp.co.aforce.java.Check;
import jp.co.aforce.java.Sky;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/MemberInformation/view/add.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		//姓
		String lastName = request.getParameter("lastName");
		//名
		String firstName = request.getParameter("firstName");
		//性別:男=1 / 女=2
		String sex = request.getParameter("sex");
		//生年月日 年
		String year = request.getParameter("year");
		//生年月日 月
		String  month = request.getParameter("month");
		//生年月日 日
		String day = request.getParameter("day");
		//職業:会社員=100 / 自営業=200 / 学生=300 / その他=400
		String job = request.getParameter("job");
		//電話番号
		String phoneNumber = request.getParameter("phoneNumber");
		//メールアドレス
		String mailAddress = request.getParameter("mailAddress");

		Sky sky = new Sky();
		//空の文字列を姓の順番から見ていき、順番が若いほうから文字列を返す。また空が見つからなかった場合は""が返ってくる
		String c = sky.nullCheck(lastName, firstName, sex, year, month, day, job, phoneNumber, mailAddress);
		if(!c.equals("")) {
			//文字列が入っていた場合は、エラーメッセージに空の要素を乗せて返す
			session.setAttribute("message", c + Messages.W_CCM0001);
			response.sendRedirect("/MemberInformation/view/add.jsp");
		} else {
			//文字列が入っていなかった場合の処理
			Check check = new Check();

			try {
				//beanに入るようにそれぞれ型に変換し、beanに格納
				MemberBean bean = check.put(lastName, firstName, sex, year, month, day, job, phoneNumber, mailAddress);
				//daoのnew
				MemberDAO dao = new MemberDAO();

				//登録前チェック 1以上が返ってきた場合は同じデータがデータベースに入っていることを示す
				int count = dao.search(bean);
				if(count == 0) {
					//同じデータが入っていなかった場合の処理
					//登録処理
					dao.insert(bean);
					//登録完了メッセージを返す
					session.setAttribute("message", Messages.I_WKK0001);
					response.sendRedirect("/MemberInformation/view/add.jsp");
				} else {
					//重複メッセージを返す
					session.setAttribute("message", Messages.E_WKK0001);
					response.sendRedirect("/MemberInformation/view/add.jsp");
				}

			} catch (Exception e) {
				//登録失敗のメッセージを返す
				e.printStackTrace();
				session.setAttribute("message", Messages.E_WKK0002);
				response.sendRedirect("/MemberInformation/view/add.jsp");
			}
		}

	}

}
