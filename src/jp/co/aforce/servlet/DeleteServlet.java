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
		//nullチェック
		Sky sky = new Sky();
		String m = sky.nullCheckById(memberId);

		if(!m.equals("")) {
			session.setAttribute("deleteMessage", m + Messages.W_CCM0001);
			response.sendRedirect("/MemberInformation/view/delete.jsp");
		} else {
			//TODO データベースの行を削除する処理
			try {
				MemberDAO dao = new MemberDAO();
				dao.delete(memberId);
				session.setAttribute("deleteMessage", Messages.I_WKK0002);
				session.removeAttribute("deleteMemberId");
				session.removeAttribute("deleteBean");
				response.sendRedirect("/MemberInformation/view/delete.jsp");
			} catch (Exception e) {
				// TODO: handle exception
				session.setAttribute("deleteMessage", Messages.E_WKK0004);
				response.sendRedirect("/MemberInformation/view/delete.jsp");
			}
		}

	}

}
