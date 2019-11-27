package servlets.servlet.admin.album;

import dao.AlbumDao;
import model.Album;
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

@WebServlet(name = "AlbumActionServlet")
public class AlbumActionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("update")!=null) {
            request.getRequestDispatcher("admin/album/update_album.jsp").forward(request, response);
        }
        if(request.getParameter("submitedUpdate")!=null) {
            SessionFactory factory = HibernateUtils.getSessionFactory();
            Session hibernateSession = factory.getCurrentSession();
            try {
                if(hibernateSession.getTransaction().getStatus().equals(TransactionStatus.NOT_ACTIVE))
                    hibernateSession.getTransaction().begin();
                AlbumDao.update(hibernateSession, Long.parseLong(request.getParameter("id")), request.getParameter("name"));
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
                AlbumDao.delete(hibernateSession, Long.parseLong(request.getParameter("id")));
                hibernateSession.getTransaction().commit();
            } catch (Exception e) {
                request.setAttribute("deleteError", "It is not possible to delete data from the parent table as long as there is data in the child");
                e.printStackTrace();
                hibernateSession.getTransaction().rollback();
            }
        }
        if(request.getParameter("list") != null) {
            request.getRequestDispatcher("admin/composition/admin_composition.jsp").forward(request, response);
        }
        request.getRequestDispatcher("admin/album/admin_album.jsp").forward(request, response);
    }
}
