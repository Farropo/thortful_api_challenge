package code.challenge.thortful.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class Data {
    @JsonProperty("children")
    private List<DataChildren> children;

    public List<DataChildren> getChildren() {
        return children;
    }

    public void setChildren(final List<DataChildren> children) {
        this.children = children;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data searchDTO = (Data) o;
        return Objects.equals(children, searchDTO.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(children);
    }

}
