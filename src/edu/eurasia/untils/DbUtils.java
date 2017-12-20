package edu.eurasia.untils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

public class DbUtils {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	/**
	 * ���µ�ͨ�÷��� ���µ�sql��䣨update/insert/delete�� paramsValue
	 * sql�����ռλ����Ӧ��ֵ�����û��ռλ��������null��
	 */
	public void update(String sql, Object[] paramsValue) {
		try {
			// ��ȡ����
			conn = JdbcConn.getConnection();
			// ����ִ������
			pstmt = conn.prepareStatement(sql);
			// ����Ԫ����: �õ�ռλ�������ĸ���
			int count = pstmt.getParameterMetaData().getParameterCount();
			// �ж��Ƿ�������
			if (paramsValue != null && paramsValue.length > 0) {
				// ѭ����������ֵ
				for (int i = 0; i < count; i++) {
					pstmt.setObject(i + 1, paramsValue[i]);
				}
			}
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcConn.close(conn, pstmt);

		}
	}

	/**
	 * ��ѯͨ�÷���
	 */
	public <T> List<T> query(String sql, Object[] paramsValue, Class<T> tClass) {
		List<T> list = new ArrayList<T>();
		// ��ȡ����
		conn = JdbcConn.getConnection();
		try {
			// ��������
			pstmt = conn.prepareStatement(sql);
			int count = pstmt.getParameterMetaData().getParameterCount();
			if (paramsValue != null && paramsValue.length > 0) {
				for (int i = 0; i < paramsValue.length; i++) {
					pstmt.setObject(i + 1, paramsValue[i]);
				}
			}
			rs = pstmt.executeQuery();
			// �õ������Ԫ����
			ResultSetMetaData rsmd = rs.getMetaData();
			// ��ȡ�еĸ���
			int culumnCount = rsmd.getColumnCount();
			T t;
			while (rs.next()) {
				t = tClass.newInstance();
				for (int i = 0; i < culumnCount; i++) {
					String coulumnName = rsmd.getColumnName(i + 1);
					Object value = rs.getObject(coulumnName);
					BeanUtils.copyProperty(t, coulumnName, value);
				}
				list.add(t);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcConn.close(conn, pstmt, rs);
		}
		return null;
	}

}
