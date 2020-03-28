package tw.STSProject.service;

import java.io.IOException;
import java.sql.SQLException;

import org.hibernate.Session;

import tw.STSProject.dao.OuputDAO;

public class OutputService implements IOutputService{
	private OuputDAO oDAO;
	
	public OutputService(Session session) {
		oDAO=new OuputDAO(session);
	}
	
	public void outputFavoriteStockToCsv(int userID) throws IOException, SQLException{
		oDAO.outputFavoriteStockToCsv(userID);
	}
	
	public void outputStockInformationToJSON(int userID) throws IOException, SQLException{
		oDAO.outputStockInformationToJSON(userID);
	}
	
	public void outputInventoryToJSON(int userID) throws IOException, SQLException{
		oDAO.outputInventoryToJSON(userID);
	}
	
	public void outputTransactionRecordToJSON(int userID) throws IOException, SQLException{
		oDAO.outputTransactionRecordToJSON(userID);
	}
}
