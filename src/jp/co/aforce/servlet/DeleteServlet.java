package jp.co.aforce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.Parameter.Messages;
import jp.co.aforce.dao.MemberDAO;
import jp.co.aforce.java.Sky;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/MemberInformation/view/delete.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		//会員番号
		String memberId = request.getParameter("memberId");

		Sky sky = new Sky();
		//会員番号のnullチェック
		String m = sky.nullCheckById(memberId);

		if(!m.equals("")) {
			//nullだった場合の処理。エラーメッセージを返す
			session.setAttribute("deleteMessage", m + Messages.W_CCM0001);
			response.sendRedirect("/MemberInformation/view/delete.jsp");
		} else {
			try {
				MemberDAO dao = new MemberDAO();
				//会員番号を元にデータを削除
				dao.delete(memberId);
				//成功メッセージを返す
				session.setAttribute("deleteMessage", Messages.I_WKK0002);
				//不要なセッションオブジェクトを削除
				session.removeAttribute("deleteMemberId");
				session.removeAttribute("deleteBean");
				response.sendRedirect("/MemberInformation/view/delete.jsp");
			} catch (Exception e) {
				//削除に失敗した場合の処理
				session.setAttribute("deleteMessage", Messages.E_WKK0004);
				response.sendRedirect("/MemberInformation/view/delete.jsp");
			}
		}

	}

}
