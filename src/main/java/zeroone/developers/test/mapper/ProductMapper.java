package zeroone.developers.test.mapper;

import zeroone.developers.test.entity.Product;
import zeroone.developers.test.payload.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        uses = {ProductTypeMapper.class, ProductStatusMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    @Mapping(source = "productType", target = "productTypeDto")
    ProductDto productToDto(Product product);

    @Mapping(source = "productTypeDto", target = "productType")
    Product dtoToProduct(ProductDto productDto);
}
