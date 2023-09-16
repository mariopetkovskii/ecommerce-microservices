package petkovskimariobachelor.productservice.service.interfaces;

import petkovskimariobachelor.productservice.request.ProductRequestDto;
import petkovskimariobachelor.productservice.response.ProductResponseDto;

import java.util.List;

public interface ProductService {
    List<ProductResponseDto> findAll();
    void create(ProductRequestDto productRequestDto);
    ProductResponseDto getProduct(ProductRequestDto productRequestDto);
}
