package serviceImpl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import service.UserService;

public class UserServiceImpl implements UserService{
		ArrayList<String> account=new ArrayList<String>();
		ArrayList<String> passwordAl=new ArrayList<String>();
	@Override
	public boolean login(String username, String password) throws RemoteException {
		if(password.equals(passwordAl.get(account.indexOf(username))))
			System.out.println("login successfully");
		else
			System.out.println("wrong username or password");
		return true;
	}

	@Override
	public boolean logout(String username) throws RemoteException {
		return true;
	}
	public boolean registration(String username,String password)throws RemoteException{
		account.add(username);
		passwordAl.add(password);
		System.out.println("registrated");
		return true;
	}
}
