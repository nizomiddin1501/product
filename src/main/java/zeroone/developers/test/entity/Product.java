package zeroone.developers.test.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
@Schema(description = "Product entity represents an item available for purchase.")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long id;

    @Column(name = "product_name", length = 100, nullable = false)
    @Schema(description = "Name of the product",
            example = "Laptop",
            required = true)
    private String name;

    @Column(name = "price", nullable = false)
    @Schema(description = "Price of the product",
            example = "999.99",
            required = true)
    private Double price;

    @Column(name = "manufactured_date", nullable = false)
    @Schema(description = "The date when the product was manufactured.",
            example = "2024-01-01",
            required = true)
    private LocalDate manufacturedDate;

    @Column(name = "expiry_date", nullable = true)
    @Schema(description = "The expiry date of the product, indicating until when it is valid.",
            example = "2025-01-01")
    private LocalDate expiryDate;

    @Column(name = "image", columnDefinition = "text")
    @Schema(description = "URL or path to the image representing the product.",
            example = "https://example.com/images/product1.jpg")
    private String image;

    @ManyToOne
    @JoinColumn(name = "product_type_id", nullable = false)
    @Schema(description = "Type of the product",
            required = true)
    private ProductType productType;




}
