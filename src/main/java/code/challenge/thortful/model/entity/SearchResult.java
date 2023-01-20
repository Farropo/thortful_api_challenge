package code.challenge.thortful.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class SearchResult {
    @JsonProperty("data")
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(final Data data) {
        this.data = data;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchResult that = (SearchResult) o;
        return Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

}
