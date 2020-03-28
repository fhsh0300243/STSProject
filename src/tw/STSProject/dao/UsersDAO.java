package tw.STSProject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import tw.STSProject.bean.Users;

public class UsersDAO implements IUsersDAO{
	private Session session;
	
	public UsersDAO(Session session) {
		this.session=session;
	}
	
	public Session getSession() {
		return session;
	}
	
	public boolean insertUsers(String userName, String userPassword, int totalMoney) {	
		Query<Users> query = session.createQuery("from Users where UserName = :userName and UserPassword = :userPassword", Users.class);
		query.setParameter("userName", userName);
		query.setParameter("userPassword", userPassword);
		List<Users> list = query.list();
		if(list.size()<=0) {
			Users uTemp = new Users();
			uTemp.setUserName(userName);
			uTemp.setUserPassword(userPassword);
			uTemp.setTotalMoney(totalMoney);
			session.save(uTemp);
			return true;
		}
		return false;
	}
	
	public boolean updateUsersPassword(String userName, String oldUserPassword, String newUserPassword) {
		Query<Users> query = session.createQuery("from Users where UserName = :userName and UserPassword = :userPassword", Users.class);
		query.setParameter("userName", userName);
		query.setParameter("userPassword", oldUserPassword);
		List<Users> list = query.list();
		if(list.size()<=0) {
			return false;
		}
		query  = session.createQuery("update Users set UserPassword=:newUserPassword where UserName = :userName and  UserPassword = :oldUserPassword", Users.class);
		query.setParameter("userName", userName);
		query.setParameter("newUserPassword", newUserPassword);
		query.setParameter("oldUserPassword", oldUserPassword);
		query.executeUpdate();
		return true;
	}
	
	public int deleteUsers(String userName, String userPassword) {
		Query<?> query = session.createQuery("delete from Users where UserName = :userName and UserPassword = :userPassword");
		query.setParameter("userName", userName);
		query.setParameter("userPassword", userPassword);
		int numData=query.executeUpdate();
		return numData;
	}
	
	public List<Users> findUsers(String userName, String userPassword) {
		Query<Users> query = session.createQuery("from Users where UserName = :userName and UserPassword = :userPassword", Users.class);
		query.setParameter("userName", userName);
		query.setParameter("userPassword", userPassword);
		List<Users> list = query.list();
		return list;
	}
	
	public int updateUserMoney(int userID, int totalMoney) {
		Query<?> query  = session.createQuery("update Users set TotalMoney=:totalMoney where UserID = :userID");
		query.setParameter("totalMoney", totalMoney);
		query.setParameter("userID", userID);
		int numData=query.executeUpdate();
		return numData;
	}
	
}
