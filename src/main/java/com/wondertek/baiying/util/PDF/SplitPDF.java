package com.wondertek.baiying.util.PDF;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by wd on 2018/1/5.
 */
public class SplitPDF {

    public static void splitPDF(String filePath) {
        PdfReader pdfReader = null;
        try {
            pdfReader = new PdfReader(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int pageNumber = pdfReader.getNumberOfPages();
        System.out.println(pageNumber);
        for (int i = 0;i<pageNumber;i++) {
            Document document = new Document(pdfReader.getPageSizeWithRotation(i + 1));
            PdfCopy pdfCopy = null;

            try {
                int len = filePath.length();
                String  noExt = filePath.substring(0, len - 5);
                String newFilePath = noExt + "-" + (i + 1) +".pdf";
                pdfCopy = new PdfCopy(document, new FileOutputStream(newFilePath));
                document.open();
                pdfCopy.addPage(pdfCopy.getImportedPage(pdfReader, i + 1));
                document.close();
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    public static void main(String[] args) {
        splitPDF("E:\\opt\\Microservices Mobike 摩拜架构.pdf");
    }
}
