package cz.educanet.spotify;

import cz.educanet.spotify.entities.ArtistEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Named
@ApplicationScoped
public class SpotifyBean {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyArticleApp");
    private EntityManager em = emf.createEntityManager();

    public List<ArtistEntity> getArtists() {
        TypedQuery<ArtistEntity> query = em.createQuery("SELECT artist FROM ArtistEntity AS artist", ArtistEntity.class);
        return query.getResultList();
    }

    public String getTest() {
        return "test";
    }

}