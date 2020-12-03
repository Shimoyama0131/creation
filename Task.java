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
            
            // SELECT
            PreparedStatement pstmt = orderedOrderSelect(conn, userId);
            
            
            
//            ResultSet res = pstmt.executeQuery();
//            
//        	List<String> id = new ArrayList<String>();
//        	List<String> name = new ArrayList<String>();
//            while(res.next()) {            	
//            	id.add(res.getString("USER_ID"));
//            	name.add(res.getString("USER_NAME"));
//            }
//        	System.out.print(id);
//        	System.out.print(name);
//        	System.out.println();
            
//        	String sqlProcess = args[0];
//        	String sqlId = args[1];
//        	String sqlName = args[2];
//        	String sqlMoney = args[3];
//        	
//        	if(sqlProcess.equals("0")) {
//        		pstmt = DeleteStatus(conn, Integer.parseInt(sqlId));
//        		System.out.println("削除完了");
//        	}else {
//        		if(!id.contains(sqlId)) {
//            		System.out.println(sqlId);
//            		pstmt = UpdateInsert(conn, Integer.parseInt(sqlId), sqlName, Integer.parseInt(sqlMoney));
//            		System.out.println("新規登録");
//            	}else {
//            		pstmt = UpdateStatus(conn, Integer.parseInt(sqlId), Integer.parseInt(sqlMoney));
//            		System.out.println("更新完了");
//            	}
//        	}
            
            // 結果
            ResultSet rs = pstmt.executeQuery();

            List<Order> orderList = new ArrayList<>();
            while(rs.next()) {
            	
            	Order order = new Order();
            	order.setUser_id(Integer.valueOf(rs.getString("USER_ID")).intValue());
            	order.setOrder_id(Integer.valueOf(rs.getString("ORDER_ID")).intValue());
            	order.setOrder_status(rs.getString("ORDER_STATUS"));
            	order.setMoney(Integer.valueOf(rs.getString("MONEY")).intValue());            	
            	orderList.add(order);
            	
            	
//            	System.out.print(order.getUser_id());
//            	System.out.print(" ");
//            	System.out.print(order.getOrder_id());
//            	System.out.print(" ");
//            	System.out.print(order.getOrder_status());
//            	System.out.print(" ");
//            	System.out.print(order.getMoney());
//            	System.out.println();
            }
            
            
            excellentCustomer(orderList);
            
            
            rs.close();
            pstmt.close();
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

	}

	/**
	 * 発注済注文検索
	 * 
	 * @param conn DB接続情報
	 * @param userId ユーザID
	 * @return 検索結果
	 * 
	 */
	private static PreparedStatement orderedOrderSelect(Connection conn, int userId) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM mydb.order WHERE USER_ID="+ userId + " AND ORDER_STATUS='ORDERED'");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	
	/**
	 * BR-001 優良顧客判定ルール
	 * 
	 * @param orderList 発注済注文リスト
	 * @return 優良顧客か否か
	 * 
	 */
	private static boolean excellentCustomer(List<Order> orderList) {
		int amountThreshold = 1000;
		int countThreshold = 3;
        long highOrderCount = orderList.stream().filter(order->order.getMoney()>=amountThreshold).count();
        return highOrderCount >= countThreshold;
	}
	
}
