package com.sallefy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import static com.sallefy.domain.Track_.*;
import static com.sallefy.domain.graphs.UserGraph.GRAPH_TRACK_ALL;
import static com.sallefy.domain.graphs.UserGraph.GRAPH_TRACK_GENRE;

/**
 * A Track.
 */
@Entity
@NamedEntityGraphs({
    @NamedEntityGraph(
        name = GRAPH_TRACK_GENRE,
        attributeNodes = {
            @NamedAttributeNode(GENRES)
        }
    ),
    @NamedEntityGraph(
        name = GRAPH_TRACK_ALL,
        attributeNodes = {
            @NamedAttributeNode(GENRES),
            @NamedAttributeNode(PLAYBACKS),
            @NamedAttributeNode(LIKE_TRACKS),

        }
    )}
)
@Table(name = "track")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Track implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "rating")
    private String rating;

    @Column(name = "url")
    private String url;

    @Column(name = "popularity")
    private String popularity;

    @Column(name = "thumbnail")
    private String thumbnail;

    @CreationTimestamp
    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "released")
    private ZonedDateTime released;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "color")
    private String color;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("tracks")
    private User user;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "track_genre",
        joinColumns = @JoinColumn(name = "track_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
    private Set<Genre> genres = new HashSet<>();

    @OneToMany(mappedBy = "track", orphanRemoval = true)
    @JsonIgnore
    private Set<Playback> playbacks = new HashSet<>();

    @OneToMany(mappedBy = "track", orphanRemoval = true)
    @JsonIgnore
    private Set<LikeTrack> likeTracks = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Track name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public Track rating(String rating) {
        this.rating = rating;
        return this;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getUrl() {
        return url;
    }

    public Track url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPopularity() {
        return popularity;
    }

    public Track popularity(String popularity) {
        this.popularity = popularity;
        return this;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public Track thumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public Track createdAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getReleased() {
        return released;
    }

    public Track released(ZonedDateTime released) {
        this.released = released;
        return this;
    }

    public void setReleased(ZonedDateTime released) {
        this.released = released;
    }

    public Integer getDuration() {
        return duration;
    }

    public Track duration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getColor() {
        return color;
    }

    public Track color(String color) {
        this.color = color;
        return this;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public User getUser() {
        return user;
    }

    public Track user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public Track genres(Set<Genre> genres) {
        this.genres = genres;
        return this;
    }

    public Track addGenre(Genre genre) {
        this.genres.add(genre);
        return this;
    }

    public Track removeGenre(Genre genre) {
        this.genres.remove(genre);
        return this;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<Playback> getPlaybacks() {
        return playbacks;
    }

    public Track playbacks(Set<Playback> playbacks) {
        this.playbacks = playbacks;
        return this;
    }

    public Track addPlayback(Playback playback) {
        this.playbacks.add(playback);
        playback.setTrack(this);
        return this;
    }

    public Track removePlayback(Playback playback) {
        this.playbacks.remove(playback);
        playback.setTrack(null);
        return this;
    }

    public void setPlaybacks(Set<Playback> playbacks) {
        this.playbacks = playbacks;
    }

    public Set<LikeTrack> getLikeTracks() {
        return likeTracks;
    }

    public Track likeTracks(Set<LikeTrack> likeTracks) {
        this.likeTracks = likeTracks;
        return this;
    }

    public Track addLikeTrack(LikeTrack likeTrack) {
        this.likeTracks.add(likeTrack);
        likeTrack.setTrack(this);
        return this;
    }

    public Track removeLikeTrack(LikeTrack likeTrack) {
        this.likeTracks.remove(likeTrack);
        likeTrack.setTrack(null);
        return this;
    }

    public void setLikeTracks(Set<LikeTrack> likeTracks) {
        this.likeTracks = likeTracks;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Track)) {
            return false;
        }
        return id != null && id.equals(((Track) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Track{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", rating='" + getRating() + "'" +
            ", url='" + getUrl() + "'" +
            ", popularity='" + getPopularity() + "'" +
            ", thumbnail='" + getThumbnail() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", released='" + getReleased() + "'" +
            ", duration=" + getDuration() +
            ", color='" + getColor() + "'" +
            "}";
    }
}
