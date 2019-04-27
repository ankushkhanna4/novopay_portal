package in.novopay.platform_ui.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DBUtils extends JavaUtils {
	protected Connection conn;
	protected Statement stmt;
	// private String codeId;
	private static String organizationId;

	public String getOrganizationId() {
		return DBUtils.organizationId;
	}

	public void setOrganizationId(String organizationId) {
		DBUtils.organizationId = organizationId;
	}

	public Connection createConnection(String dbSchemaName) throws ClassNotFoundException {

		String dbSchema = configProperties.get("dbUrl") + dbSchemaName;
		String jdbcDriver = configProperties.get("jdbcDriver");
		try {
			if ((null == conn) || (!conn.getCatalog().equalsIgnoreCase(dbSchemaName))) {
				Class.forName(jdbcDriver);
				conn = DriverManager.getConnection(dbSchema, configProperties.get("dbUserName"),
						configProperties.get("dbPassword"));
				stmt = conn.createStatement();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void closeConnection(Connection conn) {

		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("Unable to close the connection due to below error..!");
			e.printStackTrace();
		}
	}

	public String changeBCAgentId(String mobileNum, String partner) throws ClassNotFoundException {
		try {
			conn = createConnection(configProperties.get("master"));
			String queryRBLB = "", queryAxisB = "", queryAxisT = "";
			if (partner.equalsIgnoreCase("RBL")) {
				queryRBLB = "UPDATE `organization_attribute` SET `attr_value` = 'NOV1000704' WHERE `attr_key` = 'RBL_BCAGENT_ID' AND `orgnization_id` = (SELECT u.`organization` FROM `master`.`user` AS u JOIN `master`.`user_attribute` AS ua ON u.`id`=ua.`user_id` JOIN `master`.organization o ON u.organization = o.id WHERE ua.`attr_value`='"
						+ mobileNum + "' ORDER BY u.id DESC LIMIT 1);";
			} else {
				queryAxisB = "UPDATE `organization_attribute` SET `attr_value` = '11510008' WHERE `attr_key` = 'AXIS_BC_AGENT_ID' AND `orgnization_id` = (SELECT u.`organization` FROM `master`.`user` AS u JOIN `master`.`user_attribute` AS ua ON u.`id`=ua.`user_id` JOIN `master`.organization o ON u.organization = o.id WHERE ua.`attr_value`='"
						+ mobileNum + "' ORDER BY u.id DESC LIMIT 1);";
				queryAxisT = "UPDATE `organization_attribute` SET `attr_value` = '00047666' WHERE `attr_key` = 'AXIS_BC_TERMINAL_ID' AND `orgnization_id` = (SELECT u.`organization` FROM `master`.`user` AS u JOIN `master`.`user_attribute` AS ua ON u.`id`=ua.`user_id` JOIN `master`.organization o ON u.organization = o.id WHERE ua.`attr_value`='"
						+ mobileNum + "' ORDER BY u.id DESC LIMIT 1);";
			}
			stmt = conn.createStatement();
			if (partner.equalsIgnoreCase("RBL")) {
				stmt.executeUpdate(queryRBLB);
			} else {
				stmt.executeUpdate(queryAxisB);
				stmt.executeUpdate(queryAxisT);
			}
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB!! BC Agent ID update  failed..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public String deleteFromOrgDevices(String mobileNum) throws ClassNotFoundException {
		try {
			conn = createConnection(configProperties.get("master"));
			String query1 = "DELETE FROM `master`.`organization_devices` WHERE msisdn='" + mobileNum + "'";
			String query2 = "UPDATE master.`organization_attribute` SET attr_value = 'ABCD' WHERE `orgnization_id`=(SELECT u.`organization` FROM `master`.`user` AS u JOIN `master`.`user_attribute` AS ua ON u.`id`=ua.`user_id` JOIN `master`.organization o ON u.organization = o.id WHERE ua.`attr_value`='"
					+ mobileNum + "' ORDER BY o.id DESC LIMIT 1) AND attr_key='VIRTUAL_ACC_NUM';";
			stmt = conn.createStatement();
			stmt.executeUpdate(query1);
			stmt.executeUpdate(query2);
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB!! Query run failed..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public String updateMPIN(String mobNum) throws ClassNotFoundException {
		try {
			conn = createConnection(configProperties.get("master"));
			String query = "UPDATE `master`.`user_auth_mechanism` "
					+ "SET `value` = '0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c' "
					+ "WHERE user_id = (SELECT user_id FROM master.user_attribute WHERE attr_value = '" + mobNum
					+ "' ORDER BY id DESC LIMIT 1);";
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB!! BC Agent ID update  failed..!");
			sqe.printStackTrace();

		}
		return null;
	}

	public String updateWalletBalance(String mobNum) throws ClassNotFoundException {
		try {
			conn = createConnection(configProperties.get("master"));
			String query = "UPDATE wallet.`m_savings_account` SET `account_balance_derived` = '1000000.000000' "
					+ "WHERE `account_no` = (SELECT oa.attr_value FROM `master`.`organization_attribute` oa "
					+ "WHERE oa.`attr_key` = 'WALLET_ACCOUNT_NUMBER' AND oa.`orgnization_id` "
					+ "IN (SELECT `organization` FROM `master`.`user` WHERE `id` IN (SELECT `user_id` "
					+ "FROM `master`.`user_attribute` WHERE `attr_value` = '" + mobNum
					+ "')) ORDER BY oa.id DESC LIMIT 1);";
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException sqe) {
			System.out.println("Error connecting DB!! BC Agent ID update  failed..!");
			sqe.printStackTrace();

		}
		return null;
	}
}
