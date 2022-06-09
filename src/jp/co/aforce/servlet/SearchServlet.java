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

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/MemberInformation/view/update.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			//会員番号
			String memberId = request.getParameter("memberId");
			if(memberId.equals("") || memberId.equals(null)) {
				//会員番号がnullまたは空で渡された場合
				session.setAttribute("updateMessage", "会員番号" + Messages.W_CCM0001);
				response.sendRedirect("/MemberInformation/view/update.jsp");
			} else {
				//会員番号が入力されていた場合
				MemberBean bean = new MemberBean();
				MemberDAO dao = new MemberDAO();

				try {
					//検索情報をbeanに格納
					bean = dao.findByMemberId(memberId);
					//beanにデータが入ってなかった場合は、例外をスローする
					if(bean == null) {
						throw new Exception();
					}
					//会員番号に続くデータを渡す
					session.setAttribute("memberId", memberId);
					session.setAttribute("updateBean", bean);
					response.sendRedirect("/MemberInformation/view/update.jsp");
				} catch (Exception e) {
					e.printStackTrace();
					//検索結果が見つからなかった場合のエラーメッセージを返す
					session.setAttribute("updateMessage", Messages.E_WKK0003);
					response.sendRedirect("/MemberInformation/view/update.jsp");
				}
			}
		} catch (Exception e) {
			//会員番号が入力されてないかった場合エラーメッセージを返す
			session.setAttribute("updateMessage", "会員番号" + Messages.W_CCM0001);
			response.sendRedirect("/MemberInformation/view/update.jsp");
		}




	}

}
