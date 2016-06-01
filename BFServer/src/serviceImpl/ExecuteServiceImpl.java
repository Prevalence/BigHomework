//请不要修改本文件名
package serviceImpl;

import java.rmi.RemoteException;

import service.ExecuteService;
import service.UserService;
import java.util.*;

public class ExecuteServiceImpl implements ExecuteService {

	/**
	 * 请实现该方法
	 */
	@Override
	public String execute(String code, String param) throws RemoteException {
		ArrayList<String> input=new ArrayList<String>();
		ArrayList<Integer> content=new ArrayList<Integer>();
		int ptr=0;
		String s="";
		char[] result=new char[20];//结果char数组
		int te=0;
		ArrayList<String> para= new ArrayList<String>();
		
		
		
		String[] paramSequ=param.split(" ");//一个参数的时候为参数本身，转化ascii有关
		if(paramSequ.length==1){
			para.add(paramSequ[0]);
		}
		else{
			para.add(paramSequ[0]);
			para.add("char");
			para.add(paramSequ[1]);
		}
		para.add(" ");
		int sequ=0;
		
		
		
		ArrayList<Integer> SaveBegin=new ArrayList<Integer>();
		ArrayList<Integer> SaveEnd=new ArrayList<Integer>();	//[]有关
		
		
		
		
		//将code中的每一个字符拆分出来，加入一个ArrayList，名为input
		for(int i=0;i<code.length();i++){		
			input.add(code.substring(i, i+1));
		}
		for(int i=0;i<=20;i++){
			content.add(0);
		}
		
		
		//主要程序
		for(int j=0;j<input.size();j++){
			switch(input.get(j)){
				//打印出当前指针指向的值的Ascii码
				case ".":{
					s=content.get(ptr).toString();
					result[te]=(char)Integer.parseInt(s);
					te++;
					break;
				}
				//输入参数的Ascii值到当前指针指向的单元
				case",":{
					//此时不能做字符与Ascii值之间的转换
					//add到后面了，有bug,应该为修改这个初始化的格子的值
					int ascii=para.get(sequ).toCharArray()[0];
					content.set(ptr,ascii);
					sequ++;
					break;
				}
				case">":{
					ptr++;
					break;
				}
				case"<":{
					ptr--;
					break;
				}
				case"[":{
					if(content.get(ptr)!=0){		//保存[出现的位置，准备跳回,嵌套循环时有问题
						SaveBegin.add(j);//压栈
						break;
					}
					
					else{						
						j=SaveEnd.get(SaveEnd.size()-1);//跳转到对应的]号之后的内容
							SaveEnd.removeAll(SaveEnd);//此时一块嵌套循环已经做完，SaveBegin与SaveEnd要重置
						break;
					}
				}
				case"]":{
					if(SaveEnd.isEmpty())		//避免重复压栈
						SaveEnd.add(j);			//压栈；
					else if(SaveEnd.contains(j)==false)
						SaveEnd.add(j);
						j=SaveBegin.get(SaveBegin.size()-1)-1;//最接近的那一个[
						SaveBegin.remove(SaveBegin.size()-1);//SaveBegin出栈
						break;
				}
				case"+":{
					content.set(ptr, content.get(ptr)+1);//指针所指的值加一
					//System.out.println(content.get(ptr));//Test
					break;
				}
				case"-":{
					content.set(ptr, content.get(ptr)-1);//指针所指的值减一
					//System.out.println(content.get(ptr));//Test
					break;
				}
			}
		}
			//以下模块实现返回结果字符串
			String results="";
			String temp=null;
			for(int i=0;i<result.length;i++)
			{
				if(!(result[i]=='\0'))
				{
					temp=String.valueOf(result[i]);
					results=results+temp;
				}
			
			}
			return results;
	}
}


