package com.letsfly.common.qrcode;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.zxing.common.BitMatrix;

/**
 * MatrixToImageWriter
 * @author kimhu
 * @create 2019/11/13
 * @version 1.0
 */
public final class Matrix2ImageWriter {
    
    /** 日志工具 */
    private static final Log logger = LogFactory.getLog(Matrix2ImageWriter.class);
    
    /** 用于设置图案的颜色 */
    private static final int QRCODE_BLACK = 0xFF000000;
    
    /** 用于背景色 */
    private static final int QRCODE_WHITE = 0xFFFFFFFF;
    
    /**
     * 私有化构造方法，防止被外部实例化
     */
    private Matrix2ImageWriter() {}
    
    /**
     * 获取Matrix2ImageWriter对象
     * @return
     */
    static Matrix2ImageWriter getInstance() {
        return new Matrix2ImageWriter();
    }
    
    /**
     * 将matrix写入文件
     * @param matrix
     * @param format
     * @param file
     * @param logPath
     * @return
     * @throws IOException
     */
    public boolean writeToFile(BitMatrix matrix, String format, File file, String logPath) {
        try {
            BufferedImage image = this.toBufferedImage(matrix);
            image = this.setMatrixLogo(image, logPath);
            
            return ImageIO.write(image, format, file);
        } catch (IOException e) {
            logger.error("writeToFile Exception", e);
            throw new RuntimeException("writeToFile Exception", e);
        }
    }
    
    /**
     * 将matrix写入文件
     * @param matrix
     * @param format
     * @param file
     * @return
     * @throws IOException
     */
    public boolean writeToFile(BitMatrix matrix, String format, File file) {
        try {
            BufferedImage image = this.toBufferedImage(matrix);
            return ImageIO.write(image, format, file);
        } catch (IOException e) {
            logger.error("writeToFile Exception", e);
            throw new RuntimeException("writeToFile Exception", e);
        }
    }
    
    /**
     * 将matrix转为BufferedImage
     * @param matrix
     * @return BufferedImage
     */
    private BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                image.setRGB(x, y,  (matrix.get(x, y) ? QRCODE_BLACK : QRCODE_WHITE));
            }
        }
        
        return image;
    }
    
    /**
     * 给生成的二维码添加中间的logo
     * @param matrixImage 生成的二维码
     * @param logPath logo地址
     * @return 
     * @throws IOException
     */
    private BufferedImage setMatrixLogo(BufferedImage matrixImage, String logPath) throws IOException {
        int matrixWidth = matrixImage.getWidth();
        int matrixHeigh = matrixImage.getHeight();
        BufferedImage logo = ImageIO.read(new File(logPath));
        
        BasicStroke stroke = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        RoundRectangle2D.Float round = new RoundRectangle2D.Float(matrixWidth / 5 * 2, matrixHeigh / 5 * 2, 
                matrixWidth / 5, matrixHeigh / 5, 20, 20);
        
        Graphics2D g2 = matrixImage.createGraphics();
        g2.drawImage(logo, matrixWidth / 5 * 2, matrixHeigh / 5 * 2, matrixWidth / 5, matrixHeigh / 5, null);
        g2.setStroke(stroke);
        g2.setColor(Color.white);
        g2.draw(round);
        
        BasicStroke stroke2 = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(matrixWidth / 5 * 2 + 2, matrixHeigh / 5 * 2 + 2, 
                matrixWidth / 5 - 4, matrixHeigh / 5 - 4, 20, 20);
        
        g2.setStroke(stroke2);
        g2.setColor(new Color(128, 128, 128));
        g2.draw(round2);// 绘制圆弧矩形
        g2.dispose();
        
        matrixImage.flush();
        return matrixImage;
    }
}
