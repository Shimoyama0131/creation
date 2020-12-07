import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sql {
	/**
	 * 発注済注文検索
	 * 
	 * @param conn DB接続情報
	 * @param userId ユーザID
	 * @return 検索結果
	 * 
	 */
	public PreparedStatement orderedOrderSelect(Connection conn, int userId) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM mydb.order WHERE USER_ID="+ userId + " AND ORDER_STATUS='ORDERED'");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	
	/**
	 * 注文リスト検索
	 * 
	 * @param conn DB接続情報
	 * @return 検索結果
	 * 
	 */
	public PreparedStatement userIdSelect(Connection conn) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM mydb.order WHERE ORDER_STATUS='ORDERED'");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	
	/**
	 * ユーザ検索
	 * 
	 * @param conn DB接続情報
	 * @param userId ユーザID
	 * @return 顧客情報
	 * 
	 */
	public PreparedStatement customerInf(Connection conn, int userId) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM mydb.user WHERE USER_ID=" + userId);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	
}
