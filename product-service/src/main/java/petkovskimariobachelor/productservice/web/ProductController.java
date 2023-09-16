package petkovskimariobachelor.productservice.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import petkovskimariobachelor.productservice.request.ProductRequestDto;
import petkovskimariobachelor.productservice.response.ProductResponseDto;
import petkovskimariobachelor.productservice.service.interfaces.ProductService;

import java.util.List;

@RestController
@RequestMapping("/rest/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/list")
    private List<ProductResponseDto> productRecordList(){
        return this.productService.findAll();
    }

    @PostMapping("/create")
    private void createProduct(@RequestBody ProductRequestDto productRequestDto){
        this.productService.create(productRequestDto);
    }

    @PostMapping("/get-product")
    private ProductResponseDto getProduct(@RequestBody ProductRequestDto productRequestDto){
        return this.productService.getProduct(productRequestDto);
    }
}