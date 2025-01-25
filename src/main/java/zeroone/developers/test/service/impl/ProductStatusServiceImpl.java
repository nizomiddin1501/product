package zeroone.developers.test.service.impl;
import zeroone.developers.test.entity.ProductStatus;
import zeroone.developers.test.entity.enums.StatusName;
import zeroone.developers.test.exceptions.ResourceNotFoundException;
import zeroone.developers.test.mapper.ProductStatusMapper;
import zeroone.developers.test.payload.ProductStatusDto;
import zeroone.developers.test.repository.ProductStatusRepository;
import zeroone.developers.test.service.ProductStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductStatusServiceImpl implements ProductStatusService {

    private final ProductStatusRepository productStatusRepository;
    private final ProductStatusMapper productStatusMapper;


    @Override
    public Page<ProductStatusDto> getAll(int page, int size) {
        Page<ProductStatus> productStatusesPage = productStatusRepository.findAll(PageRequest.of(page, size));
        return productStatusesPage.map(productStatusMapper::productStatusToDto);
    }

    @Override
    public Optional<ProductStatusDto> getOne(Long productStatusId) throws ResourceNotFoundException {
        ProductStatus productStatus = productStatusRepository.findById(productStatusId)
                .orElseThrow(() -> new ResourceNotFoundException("ProductStatus", " Id ", productStatusId));
        return Optional.of(productStatusMapper.productStatusToDto(productStatus));
    }

    @Override
    public ProductStatusDto create(ProductStatusDto productStatusDto) {
        ProductStatus productStatus = productStatusMapper.dtoToProductStatus(productStatusDto);
        ProductStatus savedProductStatus = productStatusRepository.save(productStatus);
        return productStatusMapper.productStatusToDto(savedProductStatus);
    }

    @Override
    public ProductStatusDto update(Long productStatusId, ProductStatusDto productStatusDto) throws ResourceNotFoundException {
        ProductStatus existingProductStatus = productStatusRepository.findById(productStatusId)
                .orElseThrow(() -> new ResourceNotFoundException("ProductStatus", "Id", productStatusId));

        existingProductStatus.setStatusName(StatusName.valueOf(productStatusDto.getStatusName().toUpperCase()));
        existingProductStatus.setQuantity(productStatusDto.getQuantity());
        existingProductStatus.setCreatedAt(productStatusDto.getCreatedAt());
        existingProductStatus.setUpdatedAt(LocalDateTime.now());

        ProductStatus updatedProductStatus = productStatusRepository.save(existingProductStatus);
        return productStatusMapper.productStatusToDto(updatedProductStatus);
    }

    @Override
    public void delete(Long productStatusId) throws ResourceNotFoundException {
        ProductStatus productStatus = productStatusRepository.findById(productStatusId)
                .orElseThrow(() -> new ResourceNotFoundException("ProductStatus", " Id ", productStatusId));
        productStatusRepository.delete(productStatus);
    }
}
