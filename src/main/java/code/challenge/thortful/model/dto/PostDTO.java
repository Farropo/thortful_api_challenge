package code.challenge.thortful.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

public class PostDTO {
    private static final String UTC_PLUS_ZERO = "UTC+0";

    @JsonProperty("title")
    private String title;
    @JsonProperty("author")
    private String author;
    @JsonProperty("permalink")
    private String permalink;
    @JsonProperty("url")
    private String url;

    @JsonProperty("created_utc")
    private String createdUtc;

    @JsonProperty("created_local")
    private LocalDateTime createdLocal;

    // getters and setters
    public void setPermalink(final String permalink) {
        this.permalink = permalink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public String getPermalink() {
        return permalink;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public LocalDateTime getCreatedLocal() {
        return createdLocal;
    }

    public void setCreatedUtc(final String createdUtc) {
        this.createdUtc = createdUtc;
        long timestamp = Double.valueOf(createdUtc).longValue();
        Instant instant = Instant.ofEpochSecond(timestamp);
        ZoneId zone = ZoneId.of(UTC_PLUS_ZERO);
        this.createdLocal = LocalDateTime.ofInstant(instant, zone);
    }

    public String getCreatedUtc() {
        return createdUtc;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostDTO postDTO = (PostDTO) o;
        return Objects.equals(title, postDTO.title) && Objects.equals(author, postDTO.author) && Objects.equals(permalink, postDTO.permalink) && Objects.equals(url, postDTO.url) && Objects.equals(createdUtc, postDTO.createdUtc) && Objects.equals(createdLocal, postDTO.createdLocal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, permalink, url, createdUtc, createdLocal);
    }
}