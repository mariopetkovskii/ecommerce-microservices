package petkovskimariobachelor.commonservice.shareddtos.product;


import java.io.Serializable;

public record ProductSharedDto(String code,
                               String name,
                               String brand,
                               Double price) implements Serializable {
}
