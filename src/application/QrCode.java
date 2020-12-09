package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.EncodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.multi.GenericMultipleBarcodeReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.image.BufferedImage;

public class QrCode {
	
	
	
		public static void start(BufferedImage frame)   {
			
				
			    LuminanceSource source = new BufferedImageLuminanceSource(frame);
			    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
			    Reader reader = new MultiFormatReader();
			    GenericMultipleBarcodeReader readerM = new GenericMultipleBarcodeReader(reader);
			    //Result result;
				try {
					Result[] result = readerM.decodeMultiple(bitmap);
					// System.out.println("Barcode text is " + result.getText());
					for (Result r : result) {
						
						System.out.println("Barcode text is " + r.getText());
					}
				} catch (NotFoundException e) {
					// TODO Auto-generated catch block
					
				}
			   
		
						
			
			
			
		}




						// TODO Auto-generated catch block
	          /*  try {
	              //  String filePath = "D:\\QRCODE\\chillyfacts.png";
	                String charset = "UTF-8";
	                Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
	                hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
	               System.out.println (readQRCode(frame, charset, hintMap));
	              //  System.out.println("Data read from QR Code: " + readQRCode(filePath, charset, hintMap));
	            } catch (Exception e) {
	                // TODO: handle exception
	            }

	}

	    public static String readQRCode(BufferedImage frame, String charset, Map hintMap)
	    throws  NotFoundException {
	        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
	            new BufferedImageLuminanceSource(
	               frame)));
	        Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap, hintMap);
	        return qrCodeResult.getText();
	}*/
	

}
