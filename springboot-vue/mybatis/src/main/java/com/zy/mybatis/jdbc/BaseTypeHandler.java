package com.zy.mybatis.jdbc;

import java.sql.*;

/**
 * @author zakyoung
 * @Title:
 * @Description: TODO
 * @date 2018-04-01
 */
public interface BaseTypeHandler<T> {

    void setParameter(PreparedStatement V1, int v2, String v3, JDBCType type) throws SQLException;

    T getResult(ResultSet v1, String v2)throws SQLException;

    T getResult(CallableStatement v1,int v2)throws SQLException;
}
