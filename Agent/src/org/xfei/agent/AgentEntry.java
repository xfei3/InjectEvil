package org.xfei.agent;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

public class AgentEntry {
	 public static void agentmain(String agentArgs, Instrumentation inst) 
	           throws ClassNotFoundException, UnmodifiableClassException, 
	           InterruptedException { 
	       inst.addTransformer(new Transformer (), true); 
	        Class[] loadedClasses = inst.getAllLoadedClasses();
	        for (Class c : loadedClasses) {
	            if (c.getName().equals("org.xfei.Identity")) {
	                try {
	                    inst.retransformClasses(c);
	                    System.out.println("Class found and infected."); 
	                } catch (Exception e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	            }
	        }
	       System.out.println("Infection complete."); 
	   } 
}
