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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		//会員番号
		String memberId = request.getParameter("memberId");
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

		//取得した値のnullチェック
		Sky sky = new Sky();
		String c = sky.nullCheck(lastName, firstName, sex, year, month, day, job, phoneNumber, mailAddress);
		String m = sky.nullCheckById(memberId);
		if(!c.equals("")) {
			session.setAttribute("updateMessage", c + Messages.W_CCM0001);
			response.sendRedirect("/MemberInformation/view/update.jsp");
		} else if(!m.equals("")) {
			session.setAttribute("updateMessage", m + Messages.W_CCM0001);
			response.sendRedirect("/MemberInformation/view/update.jsp");
		} else {
			//Beanに入れやすいように加工
			Check check = new Check();
			try {
				//会員番号以外の要素をBeanに格納
				MemberBean bean = check.put(lastName, firstName, sex, year, month, day, job, phoneNumber, mailAddress);
				MemberDAO dao = new MemberDAO();

				dao.update(bean, memberId);
				session.setAttribute("updateMessage", Messages.I_WKK0003);
				//不要になったセッションオブジェクトをキル
				session.removeAttribute("updateBean");
				session.removeAttribute("memberId");
				response.sendRedirect("/MemberInformation/view/update.jsp");

			} catch (Exception e) {
				session.setAttribute("updateMessage", Messages.E_WKK0005);
				response.sendRedirect("/MemberInformation/view/update.jsp");
			}
		}
	}

}
