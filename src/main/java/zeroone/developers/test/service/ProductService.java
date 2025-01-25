package zeroone.developers.test.service;

import zeroone.developers.test.payload.ProductDto;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ProductService {

    Page<ProductDto> getAll(int page, int size);

    Optional<ProductDto> getOne(Long productId);

    ProductDto create(ProductDto productDto);

    ProductDto update(Long productId, ProductDto productDto);

    void delete(Long productId);

}
