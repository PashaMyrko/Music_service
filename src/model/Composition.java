package model;

import javax.persistence.*;

@Entity
@Table(name = "composition")
public class Composition {

    private Long id;
    private String name;

    private Album album;

    public Composition() {
    }

    public Composition(Long id, String name, Album album) {
        this.id = id;
        this.name = name;
        this.album = album;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "composition_id", nullable = false, insertable = true, updatable = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "composition_name", length = 50, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "album_id", referencedColumnName = "album_id")
    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
