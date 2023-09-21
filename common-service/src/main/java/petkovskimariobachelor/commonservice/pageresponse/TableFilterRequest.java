package petkovskimariobachelor.commonservice.pageresponse;

import org.springframework.data.domain.Sort;

import java.util.Objects;

public class TableFilterRequest {
    public final Integer pageNumber;
    public final Integer pageSize;
    public final Sort.Direction sortDirection;
    public final String sortProperty;
    public TableFilterRequest(Integer pageNumber, Integer pageSize, Sort.Direction sortDirection, String sortProperty) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sortDirection = sortDirection;
        this.sortProperty = sortProperty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableFilterRequest that = (TableFilterRequest) o;
        return Objects.equals(pageNumber, that.pageNumber) && Objects.equals(pageSize, that.pageSize) && sortDirection == that.sortDirection && Objects.equals(sortProperty, that.sortProperty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageNumber, pageSize, sortDirection, sortProperty);
    }
}
