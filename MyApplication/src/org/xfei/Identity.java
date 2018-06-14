package org.xfei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Identity {
	public void printIdentity() 
	{
		Runtime rt = Runtime.getRuntime();
		String[] commands = {"whoami"};

		Process proc;
		try {
			proc = rt.exec(commands);
			InputStream stdin = proc.getInputStream();
			InputStreamReader isr = new InputStreamReader(stdin);
			BufferedReader br = new BufferedReader(isr);

			String line = null;

			while ( (line = br.readLine()) != null){
				 System.out.println(line);//System.out.println(line+" infected by evil!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}
}
