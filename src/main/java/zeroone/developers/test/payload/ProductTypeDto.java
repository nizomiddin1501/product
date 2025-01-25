package zeroone.developers.test.payload;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "ProductType DTO is used for transferring product type data across the application.")
public class ProductTypeDto {

    @Schema(description = "Unique ID of the product type",
            example = "1")
    private Long id;

    @NotBlank(message = "Type name cannot be blank")
    @Size(max = 50, message = "Type name must be less than or equal to 50 characters")
    @Schema(description = "Name of the product type",
            example = "Electronics",
            required = true)
    private String typeName;

    @Schema(description = "Detailed description of the product type",
            example = "Electronic items including laptops, phones, and accessories.")
    private String description;

    @NotNull(message = "Is digital status cannot be null")
    @Schema(description = "Indicates whether the product type is digital or physical",
            example = "false",
            required = true)
    private Boolean isDigital;

}
