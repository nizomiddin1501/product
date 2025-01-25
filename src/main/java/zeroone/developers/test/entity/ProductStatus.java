package zeroone.developers.test.entity;
import zeroone.developers.test.entity.enums.StatusName;
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
@Table(name = "product_status")
@Schema(description = "ProductStatus entity represents the availability status of a product.")
public class ProductStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_name", length = 50, nullable = false)
    @Schema(description = "Name of the product status",
            example = "AVAILABLE",
            required = true)
    private StatusName statusName;

    @Column(name = "quantity", nullable = false)
    @Schema(description = "Available quantity of the product in stock.",
            example = "100",
            required = true)
    private Integer quantity;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Schema(description = "The timestamp when the product was created.",
            example = "2024-01-01T10:15:30",
            required = true)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Schema(description = "The timestamp when the product was last updated.",
            example = "2024-02-01T15:20:45")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @Schema(description = "Data of the product",
            required = true)
    private Product product;


}
