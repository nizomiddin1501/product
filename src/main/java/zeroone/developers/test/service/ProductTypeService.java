package zeroone.developers.test.service;

import zeroone.developers.test.payload.ProductTypeDto;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ProductTypeService {

    Page<ProductTypeDto> getAll(int page, int size);

    Optional<ProductTypeDto> getOne(Long productTypeId);

    ProductTypeDto create(ProductTypeDto productTypeDto);

    ProductTypeDto update(Long productTypeId, ProductTypeDto productTypeDto);

    void delete(Long productTypeId);

}
