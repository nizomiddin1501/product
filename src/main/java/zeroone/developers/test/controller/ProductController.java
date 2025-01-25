package zeroone.developers.test.controller;

import zeroone.developers.test.exceptions.ProductException;
import zeroone.developers.test.payload.CustomApiResponse;
import zeroone.developers.test.payload.ProductDto;
import zeroone.developers.test.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for Product operations.
 * Provides RESTful endpoints for creating, updating, retrieving, and deleting product records.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
//@CrossOrigin(origins = "http://localhost:8081")
public class ProductController {

    private final ProductService productService;


    /**
     * Retrieve a paginated list of products.
     *
     * @param page the page number to retrieve (default is 0)
     * @param size the number of products per page (default is 10)
     * @return a ResponseEntity containing a CustomApiResponse with the paginated ProductDto list
     */
    @Operation(summary = "Get all Products with Pagination", description = "Retrieve a paginated list of all products.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of products.")
    @GetMapping
    public ResponseEntity<CustomApiResponse<Page<ProductDto>>> getAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<ProductDto> productDtos = productService.getAll(page,size);
        return new ResponseEntity<>(new CustomApiResponse<>(
                "Successfully retrieved the list of products.",
                true,
                productDtos), HttpStatus.OK);
    }


    /**
     * Retrieve a product by their unique ID using the provided ProductDto.
     *
     * @param id the ID of the product to retrieve
     * @return a ResponseEntity containing a CustomApiResponse with the ProductDto and
     *         an HTTP status of OK
     */
    @Operation(summary = "Get Product by ID", description = "Retrieve a product by their unique identifier.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the product.")
    @ApiResponse(responseCode = "404", description = "Product not found.")
    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<ProductDto>> getOne(@PathVariable Long id) {
        ProductDto productDto = productService.getOne(id)
                .orElseThrow(() -> new ProductException("Product not found"));
        return new ResponseEntity<>(new CustomApiResponse<>(
                "Successfully retrieved the product.",
                true,
                productDto), HttpStatus.OK);
    }


    /**
     * Creates a new product.
     *
     * @param productDto the DTO containing the product information to be saved
     * @return a ResponseEntity containing a CustomApiResponse with the saved product data
     */
    @Operation(summary = "Create a new Product", description = "Create a new product record.")
    @ApiResponse(responseCode = "201", description = "Product created successfully.")
    @PostMapping
    public ResponseEntity<CustomApiResponse<ProductDto>> create(@Valid @RequestBody ProductDto productDto){
        ProductDto savedProduct = productService.create(productDto);
        return new ResponseEntity<>(new CustomApiResponse<>(
                "Product created successfully",
                true,
                savedProduct), HttpStatus.CREATED);
    }

    /**
     * Update the details of an existing product using the provided ProductDto.
     *
     * @param id the ID of the product to be updated
     * @param productDto the DTO containing updated product details
     * @return a ResponseEntity containing a CustomApiResponse with the updated ProductDto
     */
    @Operation(summary = "Update product", description = "Update the details of an existing product.")
    @ApiResponse(responseCode = "200", description = "Product updated successfully")
    @ApiResponse(responseCode = "404", description = "Product not found")
    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<ProductDto>>  update(
            @PathVariable Long id,
            @RequestBody ProductDto productDto) {
        ProductDto updatedProduct = productService.update(id, productDto);
        return new ResponseEntity<>(new CustomApiResponse<>(
                "Product updated successfully",
                true,
                updatedProduct), HttpStatus.OK);
    }



    /**
     * Delete a product by their ID.
     *
     * @param id the ID of the product to delete
     * @return a ResponseEntity containing a CustomApiResponse with the status of the operation
     */
    @Operation(summary = "Delete Product", description = "Delete a product by its ID.")
    @ApiResponse(responseCode = "204", description = "Product deleted successfully.")
    @ApiResponse(responseCode = "404", description = "Product not found.")
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomApiResponse<Void>> delete(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>(new CustomApiResponse<>(
                "Product deleted successfully.",
                true,
                null), HttpStatus.NO_CONTENT);
    }
}
