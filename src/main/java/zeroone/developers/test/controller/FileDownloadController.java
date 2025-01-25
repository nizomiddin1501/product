package zeroone.developers.test.controller;

import com.itextpdf.text.DocumentException;
import zeroone.developers.test.payload.CustomApiResponse;
import zeroone.developers.test.service.FileDownloadService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Controller for handling file download operations.
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product/files")
public class FileDownloadController {


    private final FileDownloadService fileDownloadService;

    /**
     * Download a CSV file.
     *
     * @param productId the ID of the product for the report
     * @param response to write the CSV file to
     * @return ResponseEntity with the operation status
     */
    @GetMapping("/download/csv/{productId}")
    public ResponseEntity<CustomApiResponse<Void>> downloadCSV(
            @PathVariable Long productId,
            HttpServletResponse response) throws IOException {
        fileDownloadService.generateCSV(productId, response);
        return ResponseEntity.ok(new CustomApiResponse<>(
                "CSV file generated successfully.",
                true,
                null));
    }

    /**
     * Download an Excel file.
     *
     * @param productId the ID of the product for the report
     * @param response to write the Excel file to
     * @return ResponseEntity with the operation status
     */
    @GetMapping("/download/excel/{productId}")
    public ResponseEntity<CustomApiResponse<Void>> downloadExcel(
            @PathVariable Long productId,
            HttpServletResponse response) throws IOException {
        fileDownloadService.generateExcel(productId, response);
        return ResponseEntity.ok(new CustomApiResponse<>(
                "Excel file generated successfully.",
                true,
                null));
    }

    /**
     * Download a PDF file.
     *
     * @param productId the ID of the product for the report
     * @param response to write the PDF file to
     * @return ResponseEntity with the operation status
     * @throws IOException if an error occurs during PDF generation
     */
    @GetMapping("/download/pdf/{productId}")
    public ResponseEntity<CustomApiResponse<Void>> downloadPDF(
            @PathVariable Long productId,
            HttpServletResponse response) throws IOException, DocumentException{
        fileDownloadService.generatePDF(productId, response);
        return ResponseEntity.ok(new CustomApiResponse<>(
                "PDF file generated successfully.",
                true,
                null));
    }
}
