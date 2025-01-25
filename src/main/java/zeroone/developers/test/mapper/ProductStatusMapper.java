package zeroone.developers.test.mapper;

import zeroone.developers.test.entity.ProductStatus;
import zeroone.developers.test.payload.ProductStatusDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductStatusMapper {


    @Mapping(source = "product", target = "productDto")
    ProductStatusDto productStatusToDto(ProductStatus productStatus);

    @Mapping(source = "productDto", target = "product")
    ProductStatus dtoToProductStatus(ProductStatusDto productStatusDto);

}
