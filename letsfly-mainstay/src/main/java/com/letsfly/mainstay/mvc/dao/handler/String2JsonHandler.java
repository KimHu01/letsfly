package com.letsfly.mainstay.mvc.dao.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.alibaba.fastjson.JSONArray;

/**
 * Json Type Handler
 * @author KimHu
 * @create 2019/11/13
 * @version 1.0
 */
public class String2JsonHandler extends BaseTypeHandler<JSONArray> {
    
    /**
     * setNonNullParameter
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, JSONArray parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setString(i, JSONArray.toJSONString(parameter));
    }

    /**
     * getNullableResult
     */
    @Override
    public JSONArray getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return JSONArray.parseArray(rs.getString(columnName));
    }

    /**
     * getNullableResult
     */
    @Override
    public JSONArray getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return JSONArray.parseArray(rs.getString(columnIndex));
    }

    /**
     * getNullableResult
     */
    @Override
    public JSONArray getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return JSONArray.parseArray(cs.getString(columnIndex));
    }
}
