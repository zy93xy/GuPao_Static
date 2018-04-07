package com.zy.mybatis.jdbc;

import java.sql.*;

/**
 * @author zakyoung
 * @Title:
 * @Description: TODO
 * @date 2018-04-01
 */
public class MyTypeHandler implements  BaseTypeHandler {
    @Override
    public void setParameter(PreparedStatement v1, int v2, String parameter, JDBCType type) throws SQLException {
        v1.setString(v2,parameter);
    }

    @Override
    public Object getResult(ResultSet v1, String columnName) throws SQLException {
        return v1.getString(columnName);
    }

    @Override
    public Object getResult(CallableStatement v1, int columnIndex) throws SQLException {
        return v1.getString(columnIndex);
    }
}
