package Main;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.itextpdf.io.exceptions.IOException;

import java.io.File;
import java.nio.file.Paths;
import java.text.Format;

public class tmp {
    public static void main(String[] args) throws WriterException, IOException {
        String data = "https://www.youtube.com/watch?v=qzYpgbP8RHA";
        String path = "QR.png";

        BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 50, 50);

        try {
            MatrixToImageWriter.writeToPath(matrix, "PNG",Paths.get(path));
            System.out.println("QR tạo thành công");
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

}
