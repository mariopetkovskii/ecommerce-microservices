package petkovskimariobachelor.productservice.request;

import lombok.Getter;

@Getter
public class ProductRequestDto {
    private String code;
    private String name;
    private String brand;
    private Double price;
}
