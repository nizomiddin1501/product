package zeroone.developers.test.entity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_type")
@Schema(description = "ProductType entity represents a category or type of a product.")
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long id;

    @Column(name = "type_name", length = 50, nullable = false)
    @Schema(description = "Name of the product type",
            example = "Electronics",
            required = true)
    private String typeName;

    @Column(name = "description")
    @Schema(description = "Detailed description of the product type",
            example = "Electronic items including laptops, phones, and accessories.")
    private String description;

    @Column(name = "is_digital", nullable = false)
    @Schema(description = "Indicates whether the product type is digital or physical",
            example = "false")
    private Boolean isDigital;



}
