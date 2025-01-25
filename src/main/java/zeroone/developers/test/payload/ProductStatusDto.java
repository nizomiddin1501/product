package zeroone.developers.test.payload;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "ProductStatus DTO is used for transferring product status data across the application.")
public class ProductStatusDto {


    @Schema(description = "Unique ID of the product status",
            example = "1")
    private Long id;

    @NotBlank(message = "Status name cannot be blank")
    @Schema(description = "Name of the product status",
            example = "AVAILABLE",
            required = true)
    private String statusName;

    @NotNull(message = "Quantity cannot be null")
    @Schema(description = "Available quantity of the product in stock.",
            example = "100",
            required = true)
    private Integer quantity;

    @NotNull(message = "Created timestamp cannot be null")
    @Schema(description = "The timestamp when the product was created.",
            example = "2024-01-01T10:15:30",
            required = true)
    private LocalDateTime createdAt;

    @Schema(description = "The timestamp when the product was last updated.",
            example = "2024-02-01T15:20:45")
    private LocalDateTime updatedAt;

    //@NotNull(message = "Product cannot be null")
    @Schema(description = "Data of the product",
            required = true)
    private ProductDto productDto;


}
