package zeroone.developers.test.service.impl;

import zeroone.developers.test.entity.Product;
import zeroone.developers.test.entity.ProductType;
import zeroone.developers.test.exceptions.ResourceNotFoundException;
import zeroone.developers.test.mapper.ProductMapper;
import zeroone.developers.test.payload.ProductDto;
import zeroone.developers.test.repository.ProductRepository;
import zeroone.developers.test.repository.ProductTypeRepository;
import zeroone.developers.test.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductTypeRepository productTypeRepository;
    private final ProductMapper productMapper;

    @Override
    public Page<ProductDto> getAll(int page, int size) {
        Page<Product> productsPage = productRepository.findAll(PageRequest.of(page, size));
        return productsPage.map(productMapper::productToDto);
    }

    @Override
    public Optional<ProductDto> getOne(Long productId)  throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", " Id ", productId));
        return Optional.of(productMapper.productToDto(product));
    }

    @Override
    public ProductDto create(ProductDto productDto) {
        Product product = productMapper.dtoToProduct(productDto);
        Product savedProduct = productRepository.save(product);
        return productMapper.productToDto(savedProduct);
    }

    //TODO checking method
    @Override
    public ProductDto update(Long productId, ProductDto productDto) throws ResourceNotFoundException {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Id", productId));

        existingProduct.setName(productDto.getName());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setManufacturedDate(productDto.getManufacturedDate());
        existingProduct.setExpiryDate(productDto.getExpiryDate());
        existingProduct.setImage(productDto.getImage());

        ProductType existingProductType = productTypeRepository.findById(productDto.getProductTypeDto().getId())
                .orElseThrow(() -> new ResourceNotFoundException("ProductType", " Id ", productDto.getProductTypeDto().getId()));

        existingProduct.setProductType(existingProductType);
        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.productToDto(updatedProduct);
    }

    @Override
    public void delete(Long productId) throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", " Id ", productId));
        productRepository.delete(product);
    }
}
