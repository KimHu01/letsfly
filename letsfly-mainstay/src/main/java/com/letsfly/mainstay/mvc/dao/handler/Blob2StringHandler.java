package com.letsfly.mainstay.mvc.dao.handler;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.letsfly.common.constant.GlobalConstant;

/**
 * mybatis自定义类型转换handler
 * @author KimHu
 * @create 2019/11/13
 * @version 1.0
 */
public class Blob2StringHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
            throws SQLException {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(parameter.getBytes(GlobalConstant.CHARSET_UTF8));
            ps.setBinaryStream(i, bis, parameter.length());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return this.blob2String(rs.getBlob(columnName));
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return this.blob2String(rs.getBlob(columnIndex));
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return this.blob2String(cs.getBlob(columnIndex));
    }
    
    /**
     * blob2String
     * @param blobValue
     * @return
     */
    private String blob2String(Blob blobValue) {
        if(null == blobValue) {
            return null;
        }
        
        try {
            byte[] byteValue = blobValue.getBytes(1L, (int) blobValue.length());
            if(null == byteValue) {
                return null;
            }
            
            return new String(byteValue, GlobalConstant.CHARSET_UTF8);
        } catch (UnsupportedEncodingException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
