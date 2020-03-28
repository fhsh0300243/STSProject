package tw.STSProject.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import tw.STSProject.bean.TransactionRecord;

public class TransactionRecordDAO implements ITransactionRecordDAO {
	
	private Session session;
	
	public TransactionRecordDAO(Session session) {
		this.session=session;
	}
	
	public Session getSession() {
		return session;
	}
	
	public void insertTransactionRecord(int quantity, String sellOrBuy, float price, String stockCode, int userID) {
		TransactionRecord tTemp = new TransactionRecord();
		tTemp.setQuantity(quantity);
		tTemp.setSellOrBuy(sellOrBuy);
		tTemp.setPrice(price);
		tTemp.setStockCode(stockCode);
		tTemp.setUserID(userID);
		tTemp.setRecordDay(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
		session.save(tTemp);
	}
	
	public int deleteTransactionRecord(String date) {
		Query<TransactionRecord> query = session.createQuery("delete from TransactionRecord where RecordDay < :date", TransactionRecord.class);
		query.setParameter("date", date);
		int numData=query.executeUpdate();
		return numData;
	}
	
	public List<TransactionRecord> findAllUserTransactionRecord(int userID) {
		Query<TransactionRecord> query = session.createQuery("from TransactionRecord where UserID = :userID", TransactionRecord.class);
		query.setParameter("userID", userID);
		List<TransactionRecord> list = query.list();
		return list;
	}
}
