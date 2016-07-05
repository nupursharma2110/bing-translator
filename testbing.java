package bing;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.testng.Assert;

import org.testng.annotations.Test;

public class testbing {
	
        @Test
		public void f() throws IOException 
		{
			File file_code = new File("/home/nupursharma/Desktop/gui.txt");
		    FileInputStream f1=new FileInputStream(file_code);		
			File file_code1 = new File("/home/nupursharma/Desktop/api1.txt");
		    FileInputStream f2=new FileInputStream(file_code1);
			while((f1.read()!=-1)&&(f2.read()!=-1))
			{
				Assert.assertTrue(f1.read()==f2.read());  
			}
			f1.close();
			f2.close();
		}
	}

