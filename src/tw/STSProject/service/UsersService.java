package tw.STSProject.service;

import java.util.List;

import org.hibernate.Session;

import tw.STSProject.bean.Users;
import tw.STSProject.dao.UsersDAO;

public class UsersService implements IUsersService {
	private UsersDAO uDAO;
	private Session session;
	
	public UsersService(Session session) {
		uDAO=new UsersDAO(session);
	}
	
	public boolean insertUsers(String userName, String userPassword, int totalMoney) {
		return uDAO.insertUsers(userName, userPassword, totalMoney);
	}
	
	public boolean updateUsersPassword(String userName, String oldUserPassword, String newUserPassword) {
		return uDAO.updateUsersPassword(userName, oldUserPassword, newUserPassword);
	}
	
	public int deleteUsers(String userName, String userPassword) {
		return uDAO.deleteUsers(userName, userPassword);
	}
	
	public List<Users> findUsers(String userName, String userPassword) {
		return uDAO.findUsers(userName, userPassword);
	}
	
	public int updateUserMoney(int userID, int totalMoney) {
		return uDAO.updateUserMoney(userID, totalMoney);
	}

	public Session getSession() {
		return session;
	}
}
