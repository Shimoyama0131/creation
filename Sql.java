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
		PreparedStatement userId = null;
		try {
			userId = conn.prepareStatement("SELECT * FROM mydb.order WHERE ORDER_STATUS='ORDERED'");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return userId;
	}
	
}
