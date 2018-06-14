package org.xfei;

import java.io.IOException;

public class Whoami {
	

	public static void main(String[] args) {
		try {
			Identity id=new Identity();
			while(true){
			id.printIdentity();
			Thread.sleep(3000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
