package servlets.servlet.admin.composition;

import dao.AlbumDao;
import dao.CompositionDao;
import model.Album;
import model.Composition;
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

@WebServlet(name = "AddCompositionServlet")
public class AddCompositionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("submitedCreate") != null) {
            SessionFactory factory = HibernateUtils.getSessionFactory();
            Session hibernateSession = factory.getCurrentSession();
            try {
                if(hibernateSession.getTransaction().getStatus().equals(TransactionStatus.NOT_ACTIVE))
                    hibernateSession.getTransaction().begin();
                Composition comp = new Composition();
                comp.setName(request.getParameter("name"));
                comp.setAlbum(AlbumDao.findByName(hibernateSession, request.getParameter("album")));
                CompositionDao.insert(hibernateSession, comp);
                hibernateSession.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
                hibernateSession.getTransaction().rollback();
            }
        }
        request.getRequestDispatcher("admin/composition/admin_composition.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("admin/composition/add_composition.jsp").forward(request, response);

    }
}
