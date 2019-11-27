package servlets.servlet.admin.composition;

import dao.AlbumDao;
import dao.CompositionDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import utils.HibernateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CompositionActionServlet")
public class CompositionActionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("update")!=null) {
            request.getRequestDispatcher("admin/composition/update_composition.jsp").forward(request, response);
        }
        if(request.getParameter("submitedUpdate")!=null) {
            SessionFactory factory = HibernateUtils.getSessionFactory();
            Session hibernateSession = factory.getCurrentSession();
            try {
                if(hibernateSession.getTransaction().getStatus().equals(TransactionStatus.NOT_ACTIVE))
                    hibernateSession.getTransaction().begin();
                CompositionDao.update(hibernateSession, Long.parseLong(request.getParameter("id")), request.getParameter("name"), request.getParameter("album"));
                hibernateSession.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
                hibernateSession.getTransaction().rollback();
            }
        }
        if(request.getParameter("delete") != null) {
            SessionFactory factory = HibernateUtils.getSessionFactory();
            Session hibernateSession = factory.getCurrentSession();
            try {
                if(hibernateSession.getTransaction().getStatus().equals(TransactionStatus.NOT_ACTIVE))
                    hibernateSession.getTransaction().begin();
                CompositionDao.delete(hibernateSession, Long.parseLong(request.getParameter("id")));
                hibernateSession.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
                hibernateSession.getTransaction().rollback();
            }
        }
        request.getRequestDispatcher("admin/composition/admin_composition.jsp").forward(request, response);
    }
}
