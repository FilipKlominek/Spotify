package cz.educanet.spotify;

import cz.educanet.spotify.entities.AlbumEntity;
import cz.educanet.spotify.entities.ArtistEntity;
import cz.educanet.spotify.entities.SongEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Named
@ApplicationScoped
public class SpotifyBean {

    private String artistName = "";
    private String artistDescription = "";

    private String albumName = "";
    private String albumDescription = "";
    private int albumArtist = 0;

    private String songName = "";
    private String songDescription = "";
    private int songAlbum = 0;


    private int artistId = 0;
    private int albumId = 0;
    private int songId = 0;


    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("MySongApp");
    public List<ArtistEntity> getArtists() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<ArtistEntity> query = em.createQuery("SELECT artist FROM ArtistEntity AS artist", ArtistEntity.class);
        return query.getResultList();
    }

    public List<AlbumEntity> getAlbums() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<AlbumEntity> query = em.createQuery("SELECT album FROM AlbumEntity AS album", AlbumEntity.class);
        return query.getResultList();
    }

    public List<SongEntity> getSongs() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<SongEntity> query = em.createQuery("SELECT song FROM SongEntity AS song", SongEntity.class);
        return query.getResultList();
    }


    public void addArtist() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();

        ArtistEntity artist = new ArtistEntity();
        artist.setName(this.artistName);
        artist.setDescription(this.artistDescription);
        em.persist(artist);
        et.commit();
    }

    public void addAlbum() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();

        TypedQuery<ArtistEntity> query = em.createQuery("SELECT artist FROM ArtistEntity AS artist WHERE artist.id = :id", ArtistEntity.class);
        query.setParameter("id", this.albumArtist);

        AlbumEntity album = new AlbumEntity();
        album.setName(this.albumName);
        album.setDescription(this.albumDescription);
        album.setReleaseDate(LocalDate.now());
        album.setArtist(query.getSingleResult());
        em.persist(album);
        et.commit();
    }

    public void addSong() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();

        TypedQuery<AlbumEntity> query = em.createQuery("SELECT album FROM AlbumEntity AS album WHERE album.id = :id", AlbumEntity.class);
        query.setParameter("id", this.songAlbum);

        SongEntity song = new SongEntity();
        song.setName(this.songName);
        song.setDescription(this.songDescription);
        song.setReleaseDate(LocalDate.now());
        song.setAlbum(query.getSingleResult());
        em.persist(song);
        et.commit();
    }


    public void editArtist() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.createQuery("UPDATE ArtistEntity AS artist SET name = :name, description = :description WHERE artist.id = :id")
                .setParameter("name", this.artistName)
                .setParameter("description", this.artistDescription)
                .setParameter("id", this.artistId)
                .executeUpdate();
        et.commit();
    }

    public void editAlbum() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.createQuery("UPDATE AlbumEntity AS album SET name = :name, description = :description WHERE album.id = :id")
                .setParameter("name", this.albumName)
                .setParameter("description", this.albumDescription)
                .setParameter("id", this.albumId)
                .executeUpdate();
        et.commit();
    }

    public void editSong() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.createQuery("UPDATE SongEntity AS song SET name = :name, description = :description WHERE song.id = :id")
                .setParameter("name", this.songName)
                .setParameter("description", this.songDescription)
                .setParameter("id", this.songId)
                .executeUpdate();
        et.commit();
    }


    public void removeArtist() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.createQuery("DELETE FROM ArtistEntity AS artist WHERE artist.id = :id")
                .setParameter("id", this.artistId)
                .executeUpdate();
        et.commit();
    }

    public void removeAlbum() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.createQuery("DELETE FROM AlbumEntity AS album WHERE album.id = :id")
                .setParameter("id", this.albumId)
                .executeUpdate();
        et.commit();
    }

    public void removeSong() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.createQuery("DELETE FROM SongEntity AS song WHERE song.id = :id")
                .setParameter("id", this.songId)
                .executeUpdate();
        et.commit();
    }


    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistDescription() {
        return artistDescription;
    }

    public void setArtistDescription(String artistDescription) {
        this.artistDescription = artistDescription;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumDescription() {
        return albumDescription;
    }

    public void setAlbumDescription(String albumDescription) {
        this.albumDescription = albumDescription;
    }

    public int getAlbumArtist() {
        return albumArtist;
    }

    public void setAlbumArtist(int albumArtist) {
        this.albumArtist = albumArtist;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongDescription() {
        return songDescription;
    }

    public void setSongDescription(String songDescription) {
        this.songDescription = songDescription;
    }

    public int getSongAlbum() {
        return songAlbum;
    }

    public void setSongAlbum(int songAlbum) {
        this.songAlbum = songAlbum;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }
}