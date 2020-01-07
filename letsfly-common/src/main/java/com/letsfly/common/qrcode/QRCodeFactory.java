package com.letsfly.common.qrcode;

import java.io.File;
import java.util.Hashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.letsfly.common.constant.GlobalConstant;

/**
 * QRCode Factory
 * @author kimhu
 * @create 2019/11/13
 * @version 1.0
 */
public final class QRCodeFactory {
    
    /** 日志工具 */
    private static final Log logger = LogFactory.getLog(QRCodeFactory.class);
    
    /**
     * 私有化构造方法，防止被外部实例化
     */
    private QRCodeFactory() {}
    
    /**
     * 获取QRCodeFactory对象
     * @return
     */
    public static QRCodeFactory getInstance() {
        return new QRCodeFactory();
    }
    
    /**
     * 创建二维码
     * @param content 二维码内容
     * @param format 生成二维码的格式
     * @param path 二维码的生成地址
     * @param logPath 二维码中间logo地址
     * @param sizeLength 图片边长
     * @return
     */
    public boolean createQRCode(String content, String format, String path, String logPath, int sizeLength) {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); //指定纠错等级,纠错级别(L 7%、M 15%、Q 25%、H 30%)
        hints.put(EncodeHintType.CHARACTER_SET, GlobalConstant.CHARSET_UTF8);
        hints.put(EncodeHintType.MARGIN, 1); //设置二维码边的空度，非负数
        
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, sizeLength, sizeLength, hints);
        } catch (WriterException e) {
            logger.error("createQRCode Exception", e);
            throw new RuntimeException("createQRCode Exception", e);
        }
        
        File file = new File(path);
        return Matrix2ImageWriter.getInstance().writeToFile(bitMatrix, format, file, logPath);
    }
    
    /**
     * 创建二维码
     * @param content 二维码内容
     * @param format 生成二维码的格式
     * @param path 二维码的生成地址
     * @param sizeLength 图片边长
     * @return
     */
    public boolean createQRCode(String content, String format, String path, int sizeLength) {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); //指定纠错等级,纠错级别(L 7%、M 15%、Q 25%、H 30%)
        hints.put(EncodeHintType.CHARACTER_SET, GlobalConstant.CHARSET_UTF8);
        hints.put(EncodeHintType.MARGIN, 1); //设置二维码边的空度，非负数
        
        BitMatrix bitMatrix = null;
        
        try {
            bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, sizeLength, sizeLength, hints);
        } catch (WriterException e) {
            logger.error("createQRCode Exception", e);
            throw new RuntimeException("createQRCode Exception", e);
        }
        
        File qrCodeFile = new File(path);
        return Matrix2ImageWriter.getInstance().writeToFile(bitMatrix, format, qrCodeFile);
    }
}
