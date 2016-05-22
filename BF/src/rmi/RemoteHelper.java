package rmi;

import java.rmi.Remote;

import service.IOService;
import service.UserService;
import service.ExecuteService;

public class RemoteHelper {
	private Remote remote;
	private static RemoteHelper remoteHelper = new RemoteHelper();
	public static RemoteHelper getInstance(){
		return remoteHelper;
	}
	
	private RemoteHelper() {	//Default constructor
	}
	
	public void setRemote(Remote remote){
		this.remote = remote;
	}
	
	public IOService getIOService(){
		return (IOService)remote;
	}
	
	public UserService getUserService(){
		return (UserService)remote;
	}
	public ExecuteService getExecuteService(){//理论上应该也要有解析代码的接口
		return (ExecuteService)remote;
	}
}
