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

@WebServlet(name = "AddAlbumServlet")
public class AddAlbumServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("submitedCreate") != null) {
            SessionFactory factory = HibernateUtils.getSessionFactory();
            Session hibernateSession = factory.getCurrentSession();
            try {
                if(hibernateSession.getTransaction().getStatus().equals(TransactionStatus.NOT_ACTIVE))
                    hibernateSession.getTransaction().begin();
                Album album = new Album();
                album.setName(request.getParameter("name"));
                AlbumDao.insert(hibernateSession, album);
                hibernateSession.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
                hibernateSession.getTransaction().rollback();
            }
        }
        request.getRequestDispatcher("admin/album/admin_album.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("admin/album/add_album.jsp").forward(request, response);

    }
}
