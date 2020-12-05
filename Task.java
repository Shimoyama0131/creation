import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.mysql.cj.log.Log;
import com.mysql.cj.log.LogFactory;

public class Task {

	/**
	 * 
	 * 
	 */
	public static void main(String[] args) {
		
		//DB接続用定数
        String DATABASE_NAME = "mydb";
        String PROPATIES = "?characterEncoding=UTF-8&serverTimezone=JST";
        String URL = "jdbc:mySQL://localhost/" + DATABASE_NAME+PROPATIES;
        //DB接続用・ユーザ定数
        String USER = "tstg";
        String PASS = "";

        try {
            //MySQL に接続する
            Class.forName("com.mysql.cj.jdbc.Driver");
            //データベースに接続
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            // データベースに対する処理
            System.out.println("データベースに接続に成功");
            
            int userId = 1;
            Sql sql = new Sql();
            // SELECT 発注済注文
            PreparedStatement oos = sql.orderedOrderSelect(conn, userId);
            // 結果
            ResultSet rs = oos.executeQuery();

            // API-001 判定結果を取得
            Api_001 api = new Api_001();
            System.out.println(api.result(api.excellentOrderList(rs)));
            rs.close();
            oos.close();
            
            // SELECT ユーザID
            PreparedStatement uis = sql.userIdSelect(conn);
            // 結果
            ResultSet rss = uis.executeQuery();
            
            // Batch-001 優良顧客リストを取得
            Batch_001 bh = new Batch_001();
            for(Order or : bh.result(bh.excellentCustomerList(rss))) {
            	System.out.print(or.getUser_id());
            	System.out.print(",");
            	System.out.print(or.getOrder_id());
            	System.out.print(",");
            	System.out.print(or.getOrder_status());
            	System.out.print(",");
            	System.out.print(or.getMoney());
            	System.out.println();
            }
            rss.close();
            uis.close();
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

	}
	
//	/**
//	 * 発注済注文検索
//	 * 
//	 * @param conn DB接続情報
//	 * @param userId ユーザID
//	 * @return 検索結果
//	 * 
//	 */
//	private static PreparedStatement orderedOrderSelect(Connection conn, int userId) {
//		PreparedStatement ps = null;
//		try {
//			ps = conn.prepareStatement("SELECT * FROM mydb.order WHERE USER_ID="+ userId + " AND ORDER_STATUS='ORDERED'");
//		} catch(SQLException e) {
//			e.printStackTrace();
//		}
//		return ps;
//	}
	
}
