package zeroone.developers.test.service;

import com.itextpdf.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface FileDownloadService {


    void generateCSV(Long productId, HttpServletResponse response) throws IOException;

    void generateExcel(Long productId, HttpServletResponse response) throws IOException;

    void generatePDF(Long productId, HttpServletResponse response) throws IOException, DocumentException;





}
