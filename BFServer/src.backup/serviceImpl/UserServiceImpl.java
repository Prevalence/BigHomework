package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import service.UserService;

public class UserServiceImpl implements UserService{
		
	@Override
	public boolean login(String username, String password) throws RemoteException {
		boolean flag=false;
		String content = null;
		File f=new File("d:/IOExample/","acpa.txt");
		FileReader fr;
		try {
			fr = new FileReader(f);
			BufferedReader bfr=new BufferedReader(fr);
			content=bfr.readLine();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(content.contains(username+","+password+"."))
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
		File f=new File("d:/IOExample/","acpa.txt");
		try {
			FileWriter fw = new FileWriter(f, true);
			fw.write(username+","+password+".");
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
