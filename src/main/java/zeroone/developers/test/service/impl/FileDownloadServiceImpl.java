package zeroone.developers.test.service.impl;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import zeroone.developers.test.mapper.ProductMapper;
import zeroone.developers.test.payload.ProductDto;
import zeroone.developers.test.repository.ProductRepository;
import zeroone.developers.test.service.FileDownloadService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;

@Service
@RequiredArgsConstructor
public class FileDownloadServiceImpl implements FileDownloadService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public void generateCSV(Long productId, HttpServletResponse response) throws IOException {
        ProductDto product = productMapper.productToDto(productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found")));

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"product_data.csv\"");

        PrintWriter writer = response.getWriter();
        writer.println("Product Name, Price, Manufactured Date, Expiry Date, Image URL, Product Type, Product Status");

        writer.println(product.getName() + "," + product.getPrice() + "," + product.getManufacturedDate() + "," +
                (product.getExpiryDate() != null ? product.getExpiryDate() : "") + "," + product.getImage() + "," +
                product.getProductTypeDto().getTypeName());

        writer.flush();
        writer.close();
    }

    @Override
    public void generateExcel(Long productId, HttpServletResponse response) throws IOException {
        ProductDto product = productMapper.productToDto(productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found")));

        Workbook workbook = new XSSFWorkbook();
        Sheet productSheet = workbook.createSheet("Product");

        Row header = productSheet.createRow(0);
        header.createCell(0).setCellValue("Product Name");
        header.createCell(1).setCellValue("Price");
        header.createCell(2).setCellValue("Manufactured Date");
        header.createCell(3).setCellValue("Expiry Date");
        header.createCell(4).setCellValue("Image URL");
        header.createCell(5).setCellValue("Product Type");
        header.createCell(6).setCellValue("Product Status");

        Row row = productSheet.createRow(1);
        row.createCell(0).setCellValue(product.getName());
        row.createCell(1).setCellValue(product.getPrice());
        row.createCell(2).setCellValue(product.getManufacturedDate().toString());
        row.createCell(3).setCellValue(product.getExpiryDate() != null ? product.getExpiryDate().toString() : "");
        row.createCell(4).setCellValue(product.getImage());
        row.createCell(5).setCellValue(product.getProductTypeDto().getTypeName());

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=\"product_data.xlsx\"");

        workbook.write(response.getOutputStream());
        workbook.close();
    }

    @Override
    public void generatePDF(Long productId, HttpServletResponse response) throws IOException, DocumentException {
        ProductDto product = productMapper.productToDto(productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found")));

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"product_data.pdf\"");

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        document.add(new Paragraph("Product Data"));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Name: " + product.getName()));
        document.add(new Paragraph("Price: " + product.getPrice()));
        document.add(new Paragraph("Manufactured Date: " + product.getManufacturedDate()));
        document.add(new Paragraph("Expiry Date: " + (product.getExpiryDate() != null ? product.getExpiryDate() : "N/A")));
        document.add(new Paragraph("Image URL: " + product.getImage()));
        document.add(new Paragraph("Product Type: " + product.getProductTypeDto().getTypeName()));
        document.add(new Paragraph(" "));

        document.close();
    }
}
