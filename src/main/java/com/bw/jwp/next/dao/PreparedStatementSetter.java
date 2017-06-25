package com.bw.jwp.next.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Byungwook, Lee
 */
public interface PreparedStatementSetter {
	void setValues(final PreparedStatement pstmt) throws SQLException;
}
