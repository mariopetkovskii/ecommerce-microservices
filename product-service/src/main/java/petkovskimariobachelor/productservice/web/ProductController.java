package petkovskimariobachelor.productservice.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import petkovskimariobachelor.commonservice.pageresponse.PageResponse;
import petkovskimariobachelor.commonservice.shareddtos.product.ProductSharedDto;
import petkovskimariobachelor.productservice.request.ProductFilterRequestDto;
import petkovskimariobachelor.productservice.request.ProductRequestDto;
import petkovskimariobachelor.productservice.service.interfaces.ProductService;

import java.util.List;

@RestController
@RequestMapping("/rest/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/admin/create")
    private void createProduct(@RequestBody ProductRequestDto productRequestDto){
        this.productService.create(productRequestDto);
    }

    @PostMapping("/get-product")
    private ProductSharedDto getProduct(@RequestBody ProductRequestDto productRequestDto){
        return this.productService.getProduct(productRequestDto);
    }
    @GetMapping("/list")
    private PageResponse<ProductSharedDto> findAllWithPagination(ProductFilterRequestDto request){
        return this.productService.findProductWithPaging(request);
    }
}