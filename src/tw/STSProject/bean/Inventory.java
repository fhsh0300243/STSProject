package tw.STSProject.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "inventory")
public class Inventory {
	
	@Id
	@Column(name = "IID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int iID;
	
	@Column(name = "USERID")
	private int userID;

	@Column(name = "STOCKCODE")
	private String stockCode;

	@Column(name = "QUANTITY")
	private int quantity;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

}
