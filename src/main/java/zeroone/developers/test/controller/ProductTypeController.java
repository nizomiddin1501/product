package zeroone.developers.test.controller;

import zeroone.developers.test.exceptions.ProductTypeException;
import zeroone.developers.test.payload.CustomApiResponse;
import zeroone.developers.test.payload.ProductTypeDto;
import zeroone.developers.test.service.ProductTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for ProductType operations.
 * Provides RESTful endpoints for creating, updating, retrieving, and deleting productType records.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product-types")
public class ProductTypeController {

    private final ProductTypeService productTypeService;


    /**
     * Retrieve a paginated list of productTypes.
     *
     * @param page the page number to retrieve (default is 0)
     * @param size the number of productTypes per page (default is 10)
     * @return a ResponseEntity containing a CustomApiResponse with the paginated ProductTypeDto list
     */
    @Operation(summary = "Get all ProductTypes with Pagination", description = "Retrieve a paginated list of all productTypes.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of productTypes.")
    @GetMapping
    public ResponseEntity<CustomApiResponse<Page<ProductTypeDto>>> getAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<ProductTypeDto> productTypeDtos = productTypeService.getAll(page,size);
        return new ResponseEntity<>(new CustomApiResponse<>(
                "Successfully retrieved the list of productTypes.",
                true,
                productTypeDtos), HttpStatus.OK);
    }


    /**
     * Retrieve a productType by their unique ID using the provided ProductTypeDto.
     *
     * @param id the ID of the productType to retrieve
     * @return a ResponseEntity containing a CustomApiResponse with the ProductTypeDto and
     *         an HTTP status of OK
     */
    @Operation(summary = "Get ProductType by ID", description = "Retrieve a productType by their unique identifier.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the productType.")
    @ApiResponse(responseCode = "404", description = "ProductType not found.")
    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<ProductTypeDto>> getOne(@PathVariable Long id) {
        ProductTypeDto productTypeDto = productTypeService.getOne(id)
                .orElseThrow(() -> new ProductTypeException("ProductType not found"));
        return new ResponseEntity<>(new CustomApiResponse<>(
                "Successfully retrieved the productType.",
                true,
                productTypeDto), HttpStatus.OK);
    }


    /**
     * Creates a new productType.
     *
     * @param productTypeDto the DTO containing the productType information to be saved
     * @return a ResponseEntity containing a CustomApiResponse with the saved productType data
     */
    @Operation(summary = "Create a new ProductType", description = "Create a new productType record.")
    @ApiResponse(responseCode = "201", description = "ProductType created successfully.")
    @PostMapping
    public ResponseEntity<CustomApiResponse<ProductTypeDto>> create(@Valid @RequestBody ProductTypeDto productTypeDto){
        ProductTypeDto savedProductType = productTypeService.create(productTypeDto);
        return new ResponseEntity<>(new CustomApiResponse<>(
                "ProductType created successfully",
                true,
                savedProductType), HttpStatus.CREATED);
    }

    /**
     * Update the details of an existing productType using the provided ProductTypeDto.
     *
     * @param id the ID of the productType to be updated
     * @param productTypeDto the DTO containing updated productType details
     * @return a ResponseEntity containing a CustomApiResponse with the updated ProductTypeDto
     */
    @Operation(summary = "Update productType", description = "Update the details of an existing productType.")
    @ApiResponse(responseCode = "200", description = "ProductType updated successfully")
    @ApiResponse(responseCode = "404", description = "ProductType not found")
    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<ProductTypeDto>>  update(
            @PathVariable Long id,
            @RequestBody ProductTypeDto productTypeDto) {
        ProductTypeDto updatedProductType = productTypeService.update(id, productTypeDto);
        return new ResponseEntity<>(new CustomApiResponse<>(
                "ProductType updated successfully",
                true,
                updatedProductType), HttpStatus.OK);
    }



    /**
     * Delete a productType by their ID.
     *
     * @param id the ID of the productType to delete
     * @return a ResponseEntity containing a CustomApiResponse with the status of the operation
     */
    @Operation(summary = "Delete ProductType", description = "Delete a productType by its ID.")
    @ApiResponse(responseCode = "204", description = "ProductType deleted successfully.")
    @ApiResponse(responseCode = "404", description = "ProductType not found.")
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomApiResponse<Void>> delete(@PathVariable Long id) {
        productTypeService.delete(id);
        return new ResponseEntity<>(new CustomApiResponse<>(
                "ProductType deleted successfully.",
                true,
                null), HttpStatus.NO_CONTENT);
    }

}
