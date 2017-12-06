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
	
	public Pinger(String address) {
		ping(address);		
	}
	
	
	public static void main(String[] args){
		File file = new File("./logs");
	    file.mkdir();
		System.out.println("Programm started "+ MyCalendar.getCurrentDate() + " " + MyCalendar.getCurrentTimeNowWithSS());
		if (args.length == 0){
			System.out.println("No specify address for 'ping' command! ");
			System.out.println("Set the address as an argument by start programm.");
			pressAnyKeyToContinue();
			
		}
		else {	
		System.out.println("Execute: ping -t "+ args[0]);
		System.out.println("Output writing to /logs/DebugLog.txt");
		Pinger pinger1 = new Pinger(args[0]);
		}		
	}
	 
	private Logger getLogger() {
		if ( log == null )
			log = LogManager.getLogger(getClass().getName()); 
	    return log;
	 }
	
	private void ping(String address) {

        String pingCmd = "ping " + address + " -t ";
        try {
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(pingCmd);

            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream(),"Cp1251"));
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
