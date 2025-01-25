package zeroone.developers.test.controller;

import zeroone.developers.test.exceptions.ProductStatusException;
import zeroone.developers.test.payload.CustomApiResponse;
import zeroone.developers.test.payload.ProductStatusDto;
import zeroone.developers.test.service.ProductStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for ProductStatus operations.
 * Provides RESTful endpoints for creating, updating, retrieving, and deleting productStatus records.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product-statuses")
public class ProductStatusController {

    private final ProductStatusService productStatusService;

    /**
     * Retrieve a paginated list of productStatuses.
     *
     * @param page the page number to retrieve (default is 0)
     * @param size the number of productStatuses per page (default is 10)
     * @return a ResponseEntity containing a CustomApiResponse with the paginated ProductStatusDto list
     */
    @Operation(summary = "Get all ProductStatuses with Pagination", description = "Retrieve a paginated list of all productStatuses.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of productStatuses.")
    @GetMapping
    public ResponseEntity<CustomApiResponse<Page<ProductStatusDto>>> getAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<ProductStatusDto> productStatusDtos = productStatusService.getAll(page,size);
        return new ResponseEntity<>(new CustomApiResponse<>(
                "Successfully retrieved the list of productStatuses.",
                true,
                productStatusDtos), HttpStatus.OK);
    }


    /**
     * Retrieve a productStatus by their unique ID using the provided ProductStatusDto.
     *
     * @param id the ID of the productStatus to retrieve
     * @return a ResponseEntity containing a CustomApiResponse with the ProductStatusDto and
     *         an HTTP status of OK
     */
    @Operation(summary = "Get ProductStatus by ID", description = "Retrieve a productStatus by their unique identifier.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the productStatus.")
    @ApiResponse(responseCode = "404", description = "ProductStatus not found.")
    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<ProductStatusDto>> getOne(@PathVariable Long id) {
        ProductStatusDto productStatusDto = productStatusService.getOne(id)
                .orElseThrow(() -> new ProductStatusException("ProductStatus not found"));
        return new ResponseEntity<>(new CustomApiResponse<>(
                "Successfully retrieved the productStatus.",
                true,
                productStatusDto), HttpStatus.OK);
    }


    /**
     * Creates a new productStatus.
     *
     * @param productStatusDto the DTO containing the productStatus information to be saved
     * @return a ResponseEntity containing a CustomApiResponse with the saved productStatus data
     */
    @Operation(summary = "Create a new ProductStatus", description = "Create a new productStatus record.")
    @ApiResponse(responseCode = "201", description = "ProductStatus created successfully.")
    @PostMapping
    public ResponseEntity<CustomApiResponse<ProductStatusDto>> create(@Valid @RequestBody ProductStatusDto productStatusDto){
        ProductStatusDto savedProductStatus = productStatusService.create(productStatusDto);
        return new ResponseEntity<>(new CustomApiResponse<>(
                "ProductStatus created successfully",
                true,
                savedProductStatus), HttpStatus.CREATED);
    }

    /**
     * Update the details of an existing product using the provided ProductDto.
     *
     * @param id the ID of the product to be updated
     * @param productStatusDto the DTO containing updated product details
     * @return a ResponseEntity containing a CustomApiResponse with the updated ProductDto
     */
    @Operation(summary = "Update productStatus", description = "Update the details of an existing productStatus.")
    @ApiResponse(responseCode = "200", description = "ProductStatus updated successfully")
    @ApiResponse(responseCode = "404", description = "ProductStatus not found")
    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<ProductStatusDto>>  update(
            @PathVariable Long id,
            @RequestBody ProductStatusDto productStatusDto) {
        ProductStatusDto updatedProductStatus = productStatusService.update(id, productStatusDto);
        return new ResponseEntity<>(new CustomApiResponse<>(
                "ProductStatus updated successfully",
                true,
                updatedProductStatus), HttpStatus.OK);
    }



    /**
     * Delete a productStatus by their ID.
     *
     * @param id the ID of the productStatus to delete
     * @return a ResponseEntity containing a CustomApiResponse with the statusStatus of the operation
     */
    @Operation(summary = "Delete ProductStatus", description = "Delete a productStatus by its ID.")
    @ApiResponse(responseCode = "204", description = "ProductStatus deleted successfully.")
    @ApiResponse(responseCode = "404", description = "ProductStatus not found.")
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomApiResponse<Void>> delete(@PathVariable Long id) {
        productStatusService.delete(id);
        return new ResponseEntity<>(new CustomApiResponse<>(
                "ProductStatus deleted successfully.",
                true,
                null), HttpStatus.NO_CONTENT);
    }

}
