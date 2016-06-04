package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import service.IOService;

public class IOServiceImpl implements IOService{
	
	@Override
	public boolean writeFile(String file, String userId, String fileName) {
		File f = new File("D:/IOExample",userId+"_"+fileName+".txt");
		try {
			f.createNewFile();//�½��ļ�
			FileWriter fw = new FileWriter(f, false);
			fw.write(file);
			System.out.println(file);//Added code----------------------------
			fw.flush();
			fw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String readFile(String userId, String fileName) {
		File f=new File("D:/IOExample",userId+"_"+fileName+".txt");
		if(!f.exists()){
			return "Sorry,this version don't existed.";
		}
		String content=null;
		try {
			FileReader fr=new FileReader(f);
			BufferedReader bfr=new BufferedReader(fr);
			content=bfr.readLine();
			System.out.println(content);
			bfr.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return content;
		// TODO Auto-generated method stub
		
	}

	@Override
	public String readFileList(String userId) {
		
		// TODO Auto-generated method stub
		return "OK";
	}
	
}
