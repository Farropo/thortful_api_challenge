package code.challenge.thortful.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class DataChildren {
    @JsonProperty("data")
    private DataChildrenData dataChildrenData;

    public DataChildrenData getDataChildrenData() {
        return dataChildrenData;
    }

    public void setDataChildrenData(final DataChildrenData data) {
        this.dataChildrenData = data;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataChildren that = (DataChildren) o;
        return Objects.equals(dataChildrenData, that.dataChildrenData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataChildrenData);
    }
}
