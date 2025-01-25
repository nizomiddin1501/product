package zeroone.developers.test.mapper;

import zeroone.developers.test.entity.ProductType;
import zeroone.developers.test.payload.ProductTypeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductTypeMapper {

    ProductTypeDto productTypeToDto(ProductType productType);

    ProductType dtoToProductType(ProductTypeDto productTypeDto);

}
