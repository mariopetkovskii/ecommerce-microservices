package petkovskimariobachelor.productservice.request;

import org.springframework.data.domain.Sort;
import petkovskimariobachelor.commonservice.pageresponse.TableFilterRequest;

import java.util.Objects;

public class ProductFilterRequestDto extends TableFilterRequest {
    public final String name;
    public final String brand;
    public final String code;
    public final Double price;

    public ProductFilterRequestDto(Integer pageNumber, Integer pageSize, Sort.Direction sortDirection, String sortProperty, String name, String brand, String code, Double price) {
        super(pageNumber, pageSize, sortDirection, sortProperty);
        this.name = name;
        this.brand = brand;
        this.code = code;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProductFilterRequestDto that = (ProductFilterRequestDto) o;
        return Objects.equals(name, that.name) && Objects.equals(brand, that.brand) && Objects.equals(code, that.code) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, brand, code, price);
    }
}
