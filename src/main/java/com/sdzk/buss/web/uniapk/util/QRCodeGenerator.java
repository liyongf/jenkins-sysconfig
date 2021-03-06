package com.sdzk.buss.web.uniapk.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRCodeGenerator {



    private static final String QR_CODE_IMAGE_PATH = "D:\\java\\mineManage\\mineManage\\target\\mineManage\\upload\\files\\MyQRCode.png";

    public static String generateQRCodeImage(String text, int width, int height, String filePath, HttpServletRequest request ) throws WriterException, IOException {
        filePath = request.getSession().getServletContext().getRealPath("/")+"upload\\files\\MyQRCode.png";
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);

        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

       return "upload\\files\\MyQRCode.png";
    }
    public static void main(String[] args) {
 /*       try {
            generateQRCodeImage("This is my first QR Code", 350, 350, QR_CODE_IMAGE_PATH);
        } catch (WriterException e) {
            System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
        }*/

    }

}
