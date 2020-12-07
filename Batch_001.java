
import java.util.*;
import java.sql.*;

public class Batch_001 {

//	/**
//	 * Batch-001 �D�ǌڋq���X�g
//	 * @param orderList �����ϒ������X�g
//	 * @return ���茋��
//	 * 
//	 */
//	public List<Order> result(List<Order> orderList) {
//		List<Order> customerList = new ArrayList<Order>();
//		Br_001 br = new Br_001();
//		for(Order o : orderList) {
//			List<Order> oList = new ArrayList<Order>();
//			oList.add(o);
//			if(!br.excellentCustomer(oList)) {
//				customerList.addAll(oList);
//			}
//		}
//		return customerList;
//	}
//	/**
//	 * API-001 �D�ǌڋq���茋��
//	 * @param orderList �����ϒ������X�g
//	 * @return ���茋��
//	 * 
//	 */
//	public boolean result(List<Order> orderList) {
//		Br_001 br = new Br_001();
//		return br.excellentCustomer(orderList);
//	}

//	/**
//	 * Batch-001 �ڋq��񃊃X�g
//	 * @param rss DB��������
//	 * @param orderList �����ϒ������X�g
//	 * @return ���茋��
//	 * 
//	 */
//	public List<Customer> customerInformationList(ResultSet rss, List<Order> orderList) throws ClassNotFoundException{
//		List<Customer> customerList = new ArrayList<>();
//		try {
//			if(result(orderList)) {
//	        	while(rss.next()) {
//	            	Customer customer = new Customer();
//	            	customer.setUser_id(Integer.valueOf(rss.getString("USER_ID")).intValue());
//	            	customer.setUser_name(rss.getString("USER_NAME"));
//	            	customerList.add(customer);
//	        	}
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
//		return customerList;
//	}

//	/**
//	 * Batch-001 �D�ǌڋq�̒������X�g
//	 * @param rss DB��������
//	 * @return �������X�g
//	 * 
//	 */
//	public List<Order> excellentCustomerList(ResultSet rss) throws ClassNotFoundException {
//        List<Order> orderList = new ArrayList<>();
//        try {
//        	while(rss.next()) {
//            	Order order = new Order();
//            	order.setUser_id(Integer.valueOf(rss.getString("USER_ID")).intValue());
//            	order.setOrder_id(Integer.valueOf(rss.getString("ORDER_ID")).intValue());
//            	order.setOrder_status(rss.getString("ORDER_STATUS"));
//            	order.setMoney(Integer.valueOf(rss.getString("MONEY")).intValue());            	
//            	orderList.add(order);
//            }
//        }catch(SQLException e) {
//        	e.printStackTrace();
//        }
//        return orderList;
//	}

	/**
	 * Batch-001 �ڋq��񃊃X�g
	 * @param rss �����ϒ��� ��������
	 * @param ci ���[�UID ��������
	 * @return �D�ǌڋq�̃��X�g
	 * 
	 */
	public List<Customer> customerInformationList(ResultSet rss, ResultSet ci) throws ClassNotFoundException {
        List<Order> orderList = new ArrayList<>();
        List<Customer> customerList = new ArrayList<>();
        try {
        	while(rss.next()) {
            	Order order = new Order();
            	order.setUser_id(Integer.valueOf(rss.getString("USER_ID")).intValue());
            	order.setOrder_id(Integer.valueOf(rss.getString("ORDER_ID")).intValue());
            	order.setOrder_status(rss.getString("ORDER_STATUS"));
            	order.setMoney(Integer.valueOf(rss.getString("MONEY")).intValue());            	
            	orderList.add(order);
            }

        	Br_001 br = new Br_001();
        	boolean excellentCustomer = br.excellentCustomer(orderList);

        	if(!excellentCustomer) {
            	while(ci.next()) {
                	Customer customer = new Customer();
                	customer.setUser_id(Integer.valueOf(ci.getString("USER_ID")).intValue());
                	customer.setUser_name(ci.getString("USER_NAME"));
                	customerList.add(customer);
            	}
    		}
        }catch(SQLException e) {
        	e.printStackTrace();
        }
        return customerList;
	}


}