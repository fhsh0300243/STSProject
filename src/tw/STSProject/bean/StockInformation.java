package tw.STSProject.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stockinformation")
public class StockInformation {

	@Id
	@Column(name = "SIID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int siID;

	@Column(name = "STOCKCODE")
	private String stockCode;

	@Column(name = "STOCKNAME")
	private String stockName;

	@Column(name = "TRADEPRICE")
	private float tradePrice;

	@Column(name = "CHANGE")
	private float change;

	@Column(name = "TRADEVOLUME")
	private int tradeVolume;

	@Column(name = "ACCTRADEVOLUME")
	private int accTradeVolume;

	@Column(name = "OPENINGPRICE")
	private float openingPrice;

	@Column(name = "HIGHESTPRICE")
	private float highestPrice;

	@Column(name = "LOWESTPRICE")
	private float LowestPrice;

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public float getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(float tradePrice) {
		this.tradePrice = tradePrice;
	}

	public float getChange() {
		return change;
	}

	public void setChange(float change) {
		this.change = change;
	}

	public int getTradeVolume() {
		return tradeVolume;
	}

	public void setTradeVolume(int tradeVolume) {
		this.tradeVolume = tradeVolume;
	}

	public int getAccTradeVolume() {
		return accTradeVolume;
	}

	public void setAccTradeVolume(int accTradeVolume) {
		this.accTradeVolume = accTradeVolume;
	}

	public float getOpeningPrice() {
		return openingPrice;
	}

	public void setOpeningPrice(float openingPrice) {
		this.openingPrice = openingPrice;
	}

	public float getHighestPrice() {
		return highestPrice;
	}

	public void setHighestPrice(float highestPrice) {
		this.highestPrice = highestPrice;
	}

	public float getLowestPrice() {
		return LowestPrice;
	}

	public void setLowestPrice(float lowestPrice) {
		LowestPrice = lowestPrice;
	}

	public int getSiID() {
		return siID;
	}

	public void setSiID(int siID) {
		this.siID = siID;
	}

}
