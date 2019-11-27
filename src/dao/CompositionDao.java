package dao;

import model.Album;
import model.Composition;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CompositionDao {

    public static Composition findById(Session session, Long id) {
        String sql = "Select c from " + Composition.class.getName() + " c "
                + "where c.id =:id ";
        Query<Composition> query = session.createQuery(sql);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public static Composition findByName(Session session, String name) {
        String sql = "Select c from " + Composition.class.getName() + " c "
                + "where c.name =:name ";
        Query<Composition> query = session.createQuery(sql);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    public static List<Composition> findAll(Session session) {
        String sql = "Select c from " + Composition.class.getName() + " c ";
        Query<Composition> query = session.createQuery(sql);
        return query.getResultList();
    }

    public static List<Composition> findByAlbumId(Session session, Long id) {
        String sql = "Select c from " + Composition.class.getName() + " c "
                + "where c.album.id=:id";
        Query<Composition> query = session.createQuery(sql);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public static void insert(Session session, Composition comp) {
        session.save(comp);
    }

    public static void update(Session session, Long id, String name, String album_name) {
        Album album = AlbumDao.findByName(session, album_name);
        Composition composition = findById(session, id);
        composition.setName(name);
        composition.setAlbum(album);
        session.merge(album);
    }

    public static void delete(Session session, Long id) {
        Composition composition = findById(session, id);
        session.delete(composition);
    }
}
