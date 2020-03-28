package tw.STSProject.dao;

import java.io.IOException;
import java.sql.SQLException;

public interface IOutputDAO {
	public void outputFavoriteStockToCsv(int userID) throws IOException, SQLException;
	public void outputStockInformationToJSON(int userID) throws IOException, SQLException;
	public void outputInventoryToJSON(int userID) throws IOException, SQLException;
	public void outputTransactionRecordToJSON(int userID) throws IOException, SQLException;
}
