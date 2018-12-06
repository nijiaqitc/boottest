package com.njq.boottest.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.text.MessageFormat;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@RestController
public class TestController {

	@RequestMapping("/test")
	public String test() {
		return "是是是";
	}

	private static final int WIDTH = 300;
	private static final int HEIGHT = 300;
	private static final String FORMAT = "png";

	public String getInviteQRCodeUrl(String info, String inviteCode, String mobile) {
		byte[] bytes = getInviteQRCodeBytes(info);

		if (bytes == null) {
			return null;
		}

		String fileName = MessageFormat.format("{0}.png", mobile + "_" + inviteCode);
		String path = "images/qrcode";
		return "";
	}

	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;
	// 图片宽度的一般  
	  private static final int IMAGE_WIDTH = 80;  
	  private static final int IMAGE_HEIGHT = 80;  
	  private static final int IMAGE_HALF_WIDTH = IMAGE_WIDTH / 2;  
	  private static final int FRAME_WIDTH = 2;  
	
	public byte[] getInviteQRCodeBytes(String info) {
		if (StringUtils.isEmpty(info)) {
			return null;
		}

		try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
			QRCodeWriter writer = new QRCodeWriter();
			BitMatrix matrix = writer.encode(info, BarcodeFormat.QR_CODE, 150, 150);
			
			 int halfW = matrix.getWidth() / 2;  
		      int halfH = matrix.getHeight() / 2;  
		      int width = matrix.getWidth();
		      int height = matrix.getHeight();
		      int[] pixels = new int[width * height];  
		      int[][] srcPixels = new int[IMAGE_WIDTH][IMAGE_HEIGHT];
		      for (int y = 0; y < matrix.getHeight(); y++) {  
		          for (int x = 0; x < matrix.getWidth(); x++) {  
		              // 读取图片  
		              if (x > halfW - IMAGE_HALF_WIDTH  
		                      && x < halfW + IMAGE_HALF_WIDTH  
		                      && y > halfH - IMAGE_HALF_WIDTH  
		                      && y < halfH + IMAGE_HALF_WIDTH) {  
		                  pixels[y * width + x] = srcPixels[x - halfW  
		                          + IMAGE_HALF_WIDTH][y - halfH + IMAGE_HALF_WIDTH];  
		              }   
		              // 在图片四周形成边框  
		              else if ((x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH  
		                      && x < halfW - IMAGE_HALF_WIDTH + FRAME_WIDTH  
		                      && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH  
		                      + IMAGE_HALF_WIDTH + FRAME_WIDTH)  
		                      || (x > halfW + IMAGE_HALF_WIDTH - FRAME_WIDTH  
		                              && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH  
		                              && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH  
		                              + IMAGE_HALF_WIDTH + FRAME_WIDTH)  
		                      || (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH  
		                              && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH  
		                              && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH  
		                              - IMAGE_HALF_WIDTH + FRAME_WIDTH)  
		                      || (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH  
		                              && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH  
		                              && y > halfH + IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH  
		                              + IMAGE_HALF_WIDTH + FRAME_WIDTH)) {  
		                  pixels[y * width + x] = 0xfffffff;  
		              } else {  
		                  // 此处可以修改二维码的颜色，可以分别制定二维码和背景的颜色；  
		                  pixels[y * width + x] = matrix.get(x, y) ? 0xff000000  
		                          : 0xfffffff;  
		              }  
		          }  
		      }  
		 
		      BufferedImage image = new BufferedImage(width, height,  
		              BufferedImage.TYPE_INT_RGB);  
		      image.getRaster().setDataElements(0, 0, width, height, pixels);  
			
			
			MatrixToImageWriter.writeToStream(matrix, FORMAT, stream);
			return stream.toByteArray();
		} catch (Exception ex) {
			return null;
		}
	}
}
