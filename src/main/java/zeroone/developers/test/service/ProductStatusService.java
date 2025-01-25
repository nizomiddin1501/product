package zeroone.developers.test.service;

import zeroone.developers.test.payload.ProductStatusDto;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ProductStatusService {

    Page<ProductStatusDto> getAll(int page, int size);

    Optional<ProductStatusDto> getOne(Long productStatusId);

    ProductStatusDto create(ProductStatusDto productStatusDto);

    ProductStatusDto update(Long productStatusId, ProductStatusDto productStatusDto);

    void delete(Long productStatusId);
}
