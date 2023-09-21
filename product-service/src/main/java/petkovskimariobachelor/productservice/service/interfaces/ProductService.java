package petkovskimariobachelor.productservice.service.interfaces;

import petkovskimariobachelor.commonservice.pageresponse.PageResponse;
import petkovskimariobachelor.commonservice.shareddtos.product.ProductSharedDto;
import petkovskimariobachelor.productservice.request.ProductFilterRequestDto;
import petkovskimariobachelor.productservice.request.ProductRequestDto;

import java.util.List;

public interface ProductService {
    List<ProductSharedDto> findAll();
    void create(ProductRequestDto productRequestDto);
    ProductSharedDto getProduct(ProductRequestDto productRequestDto);

    PageResponse<ProductSharedDto> findProductWithPaging(ProductFilterRequestDto productFilterRequestDto);
}
