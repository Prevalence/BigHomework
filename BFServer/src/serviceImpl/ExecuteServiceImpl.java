//璇蜂笉瑕佷慨鏀规湰鏂囦欢鍚�
package serviceImpl;

import java.rmi.RemoteException;

import service.ExecuteService;
import service.UserService;
import java.util.*;

public class ExecuteServiceImpl implements ExecuteService {

	/**
	 * 璇峰疄鐜拌鏂规硶
	 */
	@Override
	public String execute(String code, String param) throws RemoteException {
		ArrayList<String> input=new ArrayList<String>();
		ArrayList<Integer> keyboard=new ArrayList<Integer>();
		int ptr=0;
		//将code中的每一个字符拆分出来，加入一个ArrayList中
		for(int i=0;i<code.length();i++){		
			input.add(code.substring(i, i+1));
		}
		
		
		for(int j=0;j<input.size();j++){
			switch(input.get(j)){
				//打印出当前指针指向的值的Ascii码
				case ".":{
					System.out.println(keyboard.get(ptr));
					break;
				}
				//输入参数的Ascii值到当前指针指向的单元
				case",":{
					//此时做了字符与Ascii值之间的转换
					keyboard.add(Integer.valueOf(param));
					break;
				}
				case">":{
					ptr++;
					break;
				}
				case"<":{
					ptr--;
				}
				case"[":{
					
					break;
				}
				case"]":{
					
					break;
				}
				case"+":{
					keyboard.set(ptr, keyboard.get(ptr)+1);
					break;
				}
				case"-":{
					keyboard.set(ptr, keyboard.get(ptr)-1);
					break;
				}
			}
		}
		// TODO Auto-generated method stub
		return null;
	}

}
