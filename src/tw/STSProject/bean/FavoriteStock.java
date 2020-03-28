package tw.STSProject.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "favoritestock")
public class FavoriteStock {
	
	@Id
	@Column(name = "FSID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fsID;
	
	@Column(name = "USERID")
	private int userID;
	
	@Column(name = "STOCKCODE")
	private String stockCode;

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
