//Java Program to illustrate reading and writing 
// in URLConnection Class 
import java.io.*; 
import java.net.*; 
import java.util.ArrayList; 
import java.util.Date; 
import java.util.HashMap; 
import java.util.List; 
import java.util.Map; 

public class URLConnectionclass 
{ 
	public static void main(String[] args) 
	{ 
		try
		{ 
			URL url = new URL("https://drive.google.com/uc?export=download&id=11wtw6iY4rmeoAZzhsrodhFAop8CV-kEY"); 
			
			//open the connection to the above URL. 
			URLConnection urlcon = url.openConnection(); 
         urlcon.setRequestProperty("Cookie", "user=mary17; domain=airtravelbargains.com; path=/autos");
			
			//Executing the below line would print the value of 
			// Allow User Interaction field. 
			// System.out.println(urlcon.getAllowUserInteraction()); 
			
			//Executing the below line would print 
			// the value of Content Type field. 
			// System.out.println(urlcon.getContentType()); 
			
			//Executing the below line would print the value 
			// of URL of the given connection. 
			// System.out.println(urlcon.getURL()); 
			
			//Executing the below line would 
			// print the value of Do Input field. 
			// System.out.println(urlcon.getDoInput()); 
			
			//Executing the below line would 
			// print the value of Do Output field. 
			// System.out.println(urlcon.getDoOutput()); 
			
			//Executing the below line would 
			// print the value of Last Modified field. 
			// System.out.println(new Date(urlcon.getLastModified())); 
			
			//Executing the below line would 
			// print the value of Content Encoding field. 
			// System.out.println(urlcon.getContentEncoding()); 
			
			//To get a map of all the fields of http header 
			Map<String, List<String>> header = urlcon.getHeaderFields(); 
			
			//print all the fields along with their value. 
			for (Map.Entry<String, List<String>> mp : header.entrySet()) 
			{ 
				System.out.print(mp.getKey() + " : "); 
				System.out.println(mp.getValue().toString()); 
			} 
         List<String> ss=header.get("Set-Cookie22");
			System.out.println(); 
			System.out.println("Complete source code of the URL is-"); 
			System.out.println("---------------------------------");
         if(ss!=null){ 
         System.out.println("sss--"+ss.get(0)+"---sss");
			}
			//get the inputstream of the open connection. 
			BufferedReader br = new BufferedReader(new InputStreamReader(urlcon.getInputStream())); 
			String i; 
			
			//print the source code line by line. 
			while ((i = br.readLine()) != null) 
			{ 
				System.out.println(i); 
			}
         
         URL url1 = new URL(url,"https://drive.google.com/uc?export=download&id=11wtw6iY4rmeoAZzhsrodhFAop8CV-kEY"); 
			
			//open the connection to the above URL. 
			URLConnection urlcon1 = url1.openConnection(); 
         urlcon1.setRequestProperty("Set-Cookie", "user=mary17; domain=airtravelbargains.com; path=/autos"); 
         
         //To get a map of all the fields of http header 
			Map<String, List<String>> header1 = urlcon.getHeaderFields(); 
			
			//print all the fields along with their value. 
			for (Map.Entry<String, List<String>> mp1 : header1.entrySet()) 
			{ 
				System.out.print(mp1.getKey() + " : "); 
				System.out.println(mp1.getValue().toString()); 
			} 
			System.out.println(); 
			System.out.println("Complete source code of the URL is-");
         
         //get the inputstream of the open connection. 
			BufferedReader br1 = new BufferedReader(new InputStreamReader 
												(urlcon1.getInputStream())); 
			String i1; 
			
			//print the source code line by line. 
			while ((i1 = br1.readLine()) != null) 
			{ 
				System.out.println(i1); 
			}
         
            //getUseCaches();
            System.out.println("getUseCaches(): "+urlcon1.getUseCaches());
            //getRequestProperty("Cookie");
            System.out.println("getRequestProperty(String key); "+urlcon1.getRequestProperty("cookie"));
		} 
		
		catch (Exception e) 
		{ 
			System.out.println(e); 
		} 
      
	} 
   
   
} 
