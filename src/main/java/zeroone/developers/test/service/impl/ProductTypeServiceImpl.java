package zeroone.developers.test.service.impl;

import zeroone.developers.test.entity.ProductType;
import zeroone.developers.test.exceptions.ResourceNotFoundException;
import zeroone.developers.test.mapper.ProductTypeMapper;
import zeroone.developers.test.payload.ProductTypeDto;
import zeroone.developers.test.repository.ProductTypeRepository;
import zeroone.developers.test.service.ProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductTypeServiceImpl implements ProductTypeService {

    private final ProductTypeRepository productTypeRepository;
    private final ProductTypeMapper productTypeMapper;


    @Override
    public Page<ProductTypeDto> getAll(int page, int size) {
        Page<ProductType> productTypesPage = productTypeRepository.findAll(PageRequest.of(page, size));
        return productTypesPage.map(productTypeMapper::productTypeToDto);
    }

    @Override
    public Optional<ProductTypeDto> getOne(Long productTypeId) throws ResourceNotFoundException {
        ProductType productType = productTypeRepository.findById(productTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("ProductType", " Id ", productTypeId));
        return Optional.of(productTypeMapper.productTypeToDto(productType));
    }

    @Override
    public ProductTypeDto create(ProductTypeDto productTypeDto) {
        ProductType productType = productTypeMapper.dtoToProductType(productTypeDto);
        ProductType savedProductType = productTypeRepository.save(productType);
        return productTypeMapper.productTypeToDto(savedProductType);
    }

    @Override
    public ProductTypeDto update(Long productTypeId, ProductTypeDto productTypeDto) throws ResourceNotFoundException {
        ProductType existingProductType = productTypeRepository.findById(productTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("ProductType", "Id", productTypeId));

        existingProductType.setTypeName(productTypeDto.getTypeName());
        existingProductType.setDescription(productTypeDto.getDescription());
        existingProductType.setIsDigital(productTypeDto.getIsDigital());

        ProductType updatedProductType = productTypeRepository.save(existingProductType);
        return productTypeMapper.productTypeToDto(updatedProductType);
    }

    @Override
    public void delete(Long productTypeId) throws ResourceNotFoundException {
        ProductType productType = productTypeRepository.findById(productTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("ProductType", " Id ", productTypeId));
        productTypeRepository.delete(productType);
    }
}
