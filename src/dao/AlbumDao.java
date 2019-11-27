package dao;

import model.Album;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class AlbumDao {

    public static Album findById(Session session, Long id) {
        String sql = "Select a from " + Album.class.getName() + " a "
                + "where a.id =:id ";
        Query<Album> query = session.createQuery(sql);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public static Album findByName(Session session, String name) {
        String sql = "Select a from " + Album.class.getName() + " a "
                + "where a.name =:name ";
        Query<Album> query = session.createQuery(sql);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    public static List<Album> findAll(Session session) {
        String sql = "Select a from " + Album.class.getName() + " a ";
        Query<Album> query = session.createQuery(sql);
        return query.getResultList();
    }

    public static void insert(Session session, Album album) {
        session.save(album);
    }

    public static void update(Session session, Long id, String name) {
        Album album = findById(session, id);
        album.setName(name);
        session.merge(album);
    }

    public static void delete(Session session, Long id) {
        Album album = findById(session, id);
        session.delete(album);
    }
}
