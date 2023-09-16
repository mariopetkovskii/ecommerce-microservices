package petkovskimariobachelor.productservice.service.interfaces;

import petkovskimariobachelor.commonservice.shared.product.ProductSharedDto;
import petkovskimariobachelor.productservice.request.ProductRequestDto;

import java.util.List;

public interface ProductService {
    List<ProductSharedDto> findAll();
    void create(ProductRequestDto productRequestDto);
    ProductSharedDto getProduct(ProductRequestDto productRequestDto);
}
