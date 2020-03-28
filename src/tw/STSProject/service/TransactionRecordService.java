package tw.STSProject.service;

import java.util.List;

import org.hibernate.Session;

import tw.STSProject.bean.TransactionRecord;
import tw.STSProject.dao.TransactionRecordDAO;

public class TransactionRecordService implements ITransactionRecordService{
	private TransactionRecordDAO trDAO;
	
	public TransactionRecordService(Session session) {
		trDAO=new TransactionRecordDAO(session);
	}
	
	public void insertTransactionRecord(int quantity, String sellOrBuy, float price, String stockCode, int userID) {
		trDAO.insertTransactionRecord(quantity, sellOrBuy, price, stockCode, userID);
	}
	
	public int deleteTransactionRecord(String date) {
		return trDAO.deleteTransactionRecord(date);
	}
	
	public List<TransactionRecord> findAllUserTransactionRecord(int userID){
		return trDAO.findAllUserTransactionRecord(userID);
	}
}
