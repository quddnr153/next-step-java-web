package com.bw.jwp.next.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Byungwook, Lee
 */
public interface RowMapper<T> {
	T mapRow(final ResultSet rs) throws SQLException;
}
