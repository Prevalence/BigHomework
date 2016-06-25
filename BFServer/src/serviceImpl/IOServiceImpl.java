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
	public boolean writeFile(String file, String userId, String fileName) {//此时的filename其实是filename+"_"+version
		String justfileName=fileName.split("_")[0];
		File f = new File("D:/IOExample/"+userId+"/"+justfileName);
		System.out.println(f.mkdirs());
		File f2=new File("D:/IOExample/"+userId+"/"+justfileName,fileName+".txt");
		if(!f2.exists()){
			try {
				f2.createNewFile();
				FileWriter fw = new FileWriter(f2, false);
				fw.write(file);
				System.out.println(file);
				fw.flush();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				return false;
			}
		}
			
			return true;
	}


	@Override
	public String readFile(String userId, String fileName) {//此处的filename其实传入的是filename+"_"+version
		String justfileName=fileName.split("_")[0];
		File f=new File("D:/IOExample/"+userId+"/"+justfileName,fileName+".txt");
		try {
			f.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(!f.exists()){
			return "Sorry,this version don't existed.";
		}
		String content=null;
		try {
			FileReader fr=new FileReader(f);
			BufferedReader bfr=new BufferedReader(fr);
			content=bfr.readLine();
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
