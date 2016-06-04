package serviceImpl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import service.UserService;

public class UserServiceImpl implements UserService{
		ArrayList<String> account=new ArrayList<String>();
		ArrayList<String> passwordAl=new ArrayList<String>();
	@Override
	public boolean login(String username, String password) throws RemoteException {
		boolean flag=false;
		if(password.equals(passwordAl.get(account.indexOf(username))))
			flag=true;
		else
			flag=false;
		return flag;
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
	public String nextversion(String version)throws RemoteException{
		String lastversion=String.valueOf(Integer.valueOf(version)+1);
		return lastversion;
	}
	public String lastversion(String version)throws RemoteException{
		String lastversion=String.valueOf(Integer.valueOf(version)-1);
		return lastversion;
	}
}
