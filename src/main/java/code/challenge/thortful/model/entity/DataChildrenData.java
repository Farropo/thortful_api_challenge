package code.challenge.thortful.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class DataChildrenData {
    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("url")
    private String url;
    @JsonProperty("author")
    private String author;
    @JsonProperty("created_utc")
    private String createdUtc;
    @JsonProperty("thumbnail")
    private String thumbnail;
    @JsonProperty("num_comments")
    private String numComments;
    @JsonProperty("permalink")
    private String permalink;

    // getters and setters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public String getCreatedUtc() {
        return createdUtc;
    }

    public void setCreatedUtc(final String createdUtc) {
        this.createdUtc = createdUtc;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(final String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getNumComments() {
        return numComments;
    }

    public void setNumComments(final String numComments) {
        this.numComments = numComments;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(final String permalink) {
        this.permalink = permalink;
        String[] parts = permalink.split("/");
        this.id = parts[parts.length - 2];
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataChildrenData that = (DataChildrenData) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(url, that.url) && Objects.equals(author, that.author) && Objects.equals(createdUtc, that.createdUtc) && Objects.equals(thumbnail, that.thumbnail) && Objects.equals(numComments, that.numComments) && Objects.equals(permalink, that.permalink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, url, author, createdUtc, thumbnail, numComments, permalink);
    }
}
