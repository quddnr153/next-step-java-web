package com.bw.jwp.next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bw.jwp.core.jdbc.ConnectionManager;
import com.bw.jwp.next.exception.DataAccessException;

/**
 * @author Byungwook, Lee
 */
public class JdbcTemplate {
	private static final Logger LOG = LoggerFactory.getLogger(JdbcTemplate.class);

	public void update(final String sql, final PreparedStatementSetter pss) {
		try (final Connection con = ConnectionManager.getConnection();
			 final PreparedStatement pstmt = con.prepareStatement(sql)) {
			pss.setValues(pstmt);
			pstmt.executeUpdate();

		} catch (SQLException sqlException) {
			LOG.error(sqlException.getMessage());
		}
	}

	public <T> List<T> query(final String sql, final PreparedStatementSetter pss, final RowMapper<T> rm) throws DataAccessException {
		final List<T> result = new ArrayList<>();
		ResultSet rs = null;

		try (final Connection con = ConnectionManager.getConnection();
			 final PreparedStatement pstms = con.prepareStatement(sql)) {
			pss.setValues(pstms);
			rs = pstms.executeQuery();

			while (rs.next()) {
				result.add(rm.mapRow(rs));
			}

		} catch (SQLException sqlException) {
			LOG.error(sqlException.getMessage());

			throw new DataAccessException(sqlException);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlException) {
					throw new DataAccessException(sqlException);
				}
			}
		}

		return result;
	}

	public <T> T queryForObject(final String sql, final PreparedStatementSetter pss, final RowMapper<T> rm) {
		final List<T> result = query(sql, pss, rm);

		if (result.isEmpty()) {
			return null;
		}

		return result.get(0);
	}
}
