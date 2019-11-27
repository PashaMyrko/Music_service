package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "album")
public class Album {

    private Long id;
    private String name;
    private byte[] image;

    private Set<Composition> compositions = new HashSet<>(0);

    public Album() {
    }

    public Album(Long id, String name, byte[] image, Set<Composition> compositions) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.compositions = compositions;
    }

    @Id
    @Column(name = "album_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "album_name", length = 50, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "album_image", length = 1111111, nullable = true)
    @Lob
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "album")
    public Set<Composition> getCompositions() {
        return compositions;
    }

    public void setCompositions(Set<Composition> compositions) {
        this.compositions = compositions;
    }

    public void addCompositions(Composition composition) {
        composition.setAlbum(this);
        this.compositions.add(composition);
    }
}
