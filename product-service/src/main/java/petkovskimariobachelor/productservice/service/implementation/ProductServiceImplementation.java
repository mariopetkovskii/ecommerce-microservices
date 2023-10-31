package petkovskimariobachelor.productservice.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import petkovskimariobachelor.commonservice.pageresponse.PageResponse;
import petkovskimariobachelor.commonservice.shareddtos.product.ProductSharedDto;
import petkovskimariobachelor.productservice.config.SpecificationFactory;
import petkovskimariobachelor.productservice.entity.Product;
import petkovskimariobachelor.productservice.mappers.ProductResponseDtoMapper;
import petkovskimariobachelor.productservice.repository.ProductRepository;
import petkovskimariobachelor.productservice.request.ProductFilterRequestDto;
import petkovskimariobachelor.productservice.request.ProductRequestDto;
import petkovskimariobachelor.productservice.service.interfaces.ProductService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImplementation implements ProductService {
    private final ProductRepository productRepository;
    private final ProductResponseDtoMapper productResponseDtoMapper;
    private final CacheManager cacheManager;
    private final SpecificationFactory specificationFactory;
    @Cacheable(value = "productCache")
    @Override
    public List<ProductSharedDto> findAll() {
        return this.productRepository.findAll()
                .stream().map(productResponseDtoMapper)
                .collect(Collectors.toList());
    }

    @Override
    public void create(ProductRequestDto productRequestDto) {
        if(this.productRepository.findByCode(productRequestDto.getCode()) != null){
            throw new RuntimeException("Product exists");
        }
        Product product = new Product.Builder()
                .code(productRequestDto.getCode())
                .name(productRequestDto.getName())
                .brand(productRequestDto.getBrand())
                .price(productRequestDto.getPrice())
                .build();
        this.productRepository.save(product);
        this.evictProductCache();
    }

    @Override
    public ProductSharedDto getProduct(ProductRequestDto productRequestDto) {
        return this.productResponseDtoMapper.apply(this.productRepository.findByCode(productRequestDto.getCode()));
    }

    @Cacheable(value = "productPageCache", key = "#productFilterRequestDto")
    @Override
    public PageResponse<ProductSharedDto> findProductWithPaging(ProductFilterRequestDto productFilterRequestDto) {
        var sort = productFilterRequestDto.sortProperty == null ?
                Sort.unsorted() :
                Sort.by(productFilterRequestDto.sortDirection, productFilterRequestDto.sortProperty);
        Pageable pageable =
                PageRequest.of(productFilterRequestDto.pageNumber, productFilterRequestDto.pageSize, sort);

        Specification<Product> spec = this.specificationFactory.and(
                specificationFactory.like("name", productFilterRequestDto.name),
                specificationFactory.like("brand", productFilterRequestDto.brand),
                specificationFactory.like("code", productFilterRequestDto.code)
        );

        Page<Product> products = this.productRepository.findAll(spec, pageable);
        List<ProductSharedDto> productsDto =
                products.stream()
                        .map(this.productResponseDtoMapper)
                        .toList();

        return new PageResponse<>(productsDto, products.getNumber(), products.getSize(), products.getTotalElements(), products.getTotalPages());
    }

    public void evictProductCache() {
        Objects.requireNonNull(this.cacheManager.getCache("productPageCache")).clear();
    }
}
