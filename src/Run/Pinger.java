package Run;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Objects.MyCalendar;

public class Pinger
{
	private static Logger log;
	private static String command = "";
	private static String coding = "";
	
	public Pinger() {
		ping();		
	}
	
	
	public static void main(String[] args){
		File file = new File("./logs");
	    file.mkdir();
	    	    
	    for (int i=0; i<args.length; i++) {
	    	if (args[i].startsWith("coding="))
	    		coding = args[i];
	    	else
	    		command += args[i] + " ";
	    }
		
	    System.out.println("Programm started "+ MyCalendar.getCurrentDate() + " " + MyCalendar.getCurrentTimeNowWithSS());
		if (args.length == 0){
			System.out.println("No specify command! ");
			System.out.println("Set the full command as an argument by start programm.");
			pressAnyKeyToContinue();
			
		}
		else {	
		System.out.println("Execute: " + command);
		System.out.println("Output writing to /logs/DebugLog.txt");
		Pinger pinger1 = new Pinger();
		}		
	}
	 
	private Logger getLogger() {
		if ( log == null )
			log = LogManager.getLogger(getClass().getName()); 
	    return log;
	 }
	
	private void ping() {

        if (coding.isEmpty())
        	coding = "Cp1251";
        else 
        	coding = coding.substring(7);
        
        getLogger().info("Coding = [" + coding + "]");
        
		try {
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(command);

            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream(),coding));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                getLogger().debug(inputLine);
            }
            in.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }
	
	private static void pressAnyKeyToContinue() { 
		System.out.println("Press Enter key to exit and try again ...");
	    try{
	    	System.in.read();
	    }  
	    catch(Exception e){
	    }  
	 }
	
	
	
	
	
	
	  
}
