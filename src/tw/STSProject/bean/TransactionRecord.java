package tw.STSProject.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transactionrecord")
public class TransactionRecord {
	
	@Id
	@Column(name = "TRID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int trID;
	
	@Column(name = "QUANTITY")
	private int quantity;

	@Column(name = "SELLORBUY")
	private String sellOrBuy;

	@Column(name = "PRICE")
	private float price;

	@Column(name = "STOCKCODE")
	private String stockCode;

	@Column(name = "USERID")
	private int userID;
	
	@Column(name = "RECORDDAY")
	private String recordDay;
	

	public String getRecordDay() {
		return recordDay;
	}

	public void setRecordDay(String recordDay) {
		this.recordDay = recordDay;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSellOrBuy() {
		return sellOrBuy;
	}

	public void setSellOrBuy(String sellOrBuy) {
		this.sellOrBuy = sellOrBuy;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

}
