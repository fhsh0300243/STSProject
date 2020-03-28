package tw.STSProject.dao;

import java.util.List;

import tw.STSProject.bean.TransactionRecord;

public interface ITransactionRecordDAO {
	public void insertTransactionRecord(int quantity, String sellOrBuy, float price, String stockCode, int userID);
	public int deleteTransactionRecord(String date);
	public List<TransactionRecord> findAllUserTransactionRecord(int userID);
}
