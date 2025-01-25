package zeroone.developers.test.payload;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Product DTO is used for transferring product data across the application.")
public class ProductDto {

    @Schema(description = "Unique ID of the product",
            example = "1")
    private Long id;

    @NotBlank(message = "Product name cannot be blank")
    @Size(max = 100, message = "Product name must be less than or equal to 100 characters")
    @Schema(description = "Name of the product",
            example = "Laptop",
            required = true)
    private String name;

    @NotNull(message = "Price cannot be null")
    @Schema(description = "Price of the product",
            example = "999.99",
            required = true)
    private Double price;

    @NotNull(message = "Manufactured date cannot be null")
    @Schema(description = "The date when the product was manufactured.",
            example = "2024-01-01",
            required = true)
    private LocalDate manufacturedDate;

    @Schema(description = "The expiry date of the product, indicating until when it is valid.",
            example = "2025-01-01")
    private LocalDate expiryDate;

    @Schema(description = "URL or path to the image representing the product.",
            example = "https://example.com/images/product1.jpg")
    private String image;

    @NotNull(message = "Product type cannot be null")
    @Schema(description = "Type of the product",
            required = true)
    private ProductTypeDto productTypeDto;

//    @NotNull(message = "Product status cannot be null")
//    @Schema(description = "Status of the product",
//            required = true)
//    private ProductStatusDto productStatusDto;

    @NotNull(message = "Product type cannot be null")
    @Schema(description = "Type of the product",
            required = true)
    private Long productTypeId;

    @NotNull(message = "Product status cannot be null")
    @Schema(description = "Status of the product", required = true)
    private Long productStatusId;


}
