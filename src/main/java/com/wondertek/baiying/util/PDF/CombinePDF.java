package com.wondertek.baiying.util.PDF;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by wd on 2018/1/8.
 */
public class CombinePDF {

    public static void combinePDF(String[] subFile) {

        String newFile = "E:\\opt\\新建文档.pdf";

        Document document = new Document();

        try {
            PdfCopy pdfCopy = new PdfCopy(document, new FileOutputStream(newFile));
            document.open();
           for (int i = 0; i<subFile.length;i++) {
               PdfReader pdfReader = new PdfReader(subFile[i]);
               int totalPages = pdfReader.getNumberOfPages();
               System.out.println(totalPages);
               for (int p = 1; p <= totalPages;p++) {
                   pdfCopy.addPage(pdfCopy.getImportedPage(pdfReader,p));
               }
           }
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String[] subFile = {"E:\\opt\\1-100.pdf","E:\\opt\\101-150.pdf"};
        combinePDF(subFile);
    }
}
