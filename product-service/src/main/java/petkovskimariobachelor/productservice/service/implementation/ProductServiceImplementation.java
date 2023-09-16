package petkovskimariobachelor.productservice.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import petkovskimariobachelor.commonservice.shared.product.ProductSharedDto;
import petkovskimariobachelor.productservice.entity.Product;
import petkovskimariobachelor.productservice.mappers.ProductResponseDtoMapper;
import petkovskimariobachelor.productservice.repository.ProductRepository;
import petkovskimariobachelor.productservice.request.ProductRequestDto;
import petkovskimariobachelor.productservice.service.interfaces.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImplementation implements ProductService {
    private final ProductRepository productRepository;
    private final ProductResponseDtoMapper productResponseDtoMapper;
    @Override
    public List<ProductSharedDto> findAll() {
        return this.productRepository.findAll()
                .stream().map(productResponseDtoMapper)
                .collect(Collectors.toList());
    }

    @Override
    public void create(ProductRequestDto productRequestDto) {
        Product product = new Product.Builder()
                .code(productRequestDto.getCode())
                .name(productRequestDto.getName())
                .brand(productRequestDto.getBrand())
                .price(productRequestDto.getPrice())
                .build();
        this.productRepository.save(product);
    }

    @Override
    public ProductSharedDto getProduct(ProductRequestDto productRequestDto) {
        return this.productResponseDtoMapper.apply(this.productRepository.findByCode(productRequestDto.getCode()));
    }
}
