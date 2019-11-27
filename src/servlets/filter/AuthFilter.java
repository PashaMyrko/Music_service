package servlets.filter;

import dao.UserDao;
import utils.HibernateUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.nonNull;

/**
 * Acidification filter.
 */
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain)

            throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        @SuppressWarnings("unchecked")
        final AtomicReference<UserDao> dao = (AtomicReference<UserDao>) req.getServletContext().getAttribute("dao");

        final HttpSession session = req.getSession();

        if(nonNull(req.getParameter("login")) || nonNull(req.getParameter("password")))
            req.setAttribute("loginError","Incorrect login or password");
        if (nonNull(session) &&
                nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password"))) {
            final Long id = (Long) session.getAttribute("id");
            moveToMenu(req, res, id);
        } else if (dao.get().userIsExist(login, password)) {
            try {
                final Long id = dao.get().getUserByLoginPassword(login, password).getId();
                req.getSession().setAttribute("password", password);
                req.getSession().setAttribute("login", login);
                req.getSession().setAttribute("id", id);

                moveToMenu(req, res, id);
            } catch (NullPointerException e) {
                moveToMenu(req, res, -1l);
            }
        } else {
            moveToMenu(req, res, -1l);
        }
    }

    private void moveToMenu(final HttpServletRequest req, final HttpServletResponse res, final Long id)
            throws ServletException, IOException {
        if (id == 1) {
            req.removeAttribute("loginError");
            req.getRequestDispatcher("admin/admin_menu.jsp").forward(req, res);
        } else if (id != -1) {
            req.removeAttribute("loginError");
            req.getRequestDispatcher("/user_menu.jsp").forward(req, res);
        } else {
            req.getRequestDispatcher("/login.jsp").forward(req, res);
        }
    }

    @Override
    public void destroy() {
        HibernateUtils.shutdown();
    }

}
