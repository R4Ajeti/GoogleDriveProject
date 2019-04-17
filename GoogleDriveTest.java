
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GoogleDriveTest{
   public static void main(String [] args)throws UnsupportedEncodingException,MalformedURLException,IOException{
      String url="https://drive.google.com/open?id=11wtw6iY4rmeoAZzhsrodhFAop8CV-kEY";
      String gid = jDriveId(url);
      System.out.println("gid: " +gid);
      String linkdown = Drive(url);
      System.out.println("Metoda 'linkdown' nga Drive vlera: "+linkdown);
   }
   public static String getMd5(String input) 
   { 
   		try { 
   
   			// Static getInstance method is called with hashing MD5 
   			MessageDigest md = MessageDigest.getInstance("MD5"); 
   
   			// digest() method is called to calculate message digest 
   			// of an input digest() return array of byte 
   			byte[] messageDigest = md.digest(input.getBytes()); 
   
   			// Convert byte array into signum representation 
   			BigInteger no = new BigInteger(1, messageDigest); 
   
   			// Convert message digest into hex value 
   			String hashtext = no.toString(16); 
   			while (hashtext.length() < 32) { 
   				hashtext = "0" + hashtext; 
   			} 
   			return hashtext; 
   		} 
   
   		// For specifying wrong message digest algorithms 
   		catch (NoSuchAlgorithmException e) { 
   			throw new RuntimeException(e); 
   		} 
   }
   public static String[] explode(String separator, String stringToExplode){     
       return  stringToExplode.split(separator);
   }
   
   public static String getDateStr(String format, long delay){
          String timeStamp;
          timeStamp = new SimpleDateFormat(format).format( new Date( (delay)+( Calendar.getInstance().getTime() ).getTime() ) );
          return timeStamp;
   }
   
   public static Date getDate(String format, long delay){
          Date timeStamp =  new Date( (delay)+( Calendar.getInstance().getTime() ).getTime() );
          return timeStamp;
   } 
   
   private static void fwrite(String filename,String data) {
           try {
               Files.write(Paths.get(filename), data.getBytes());
           } catch (IOException e) {
               e.printStackTrace();
           }
   }
   public static String fileGetContents(String filename) throws IOException{
   String data="";
   List<String> readList = Files.readAllLines(Paths.get(filename));
               for(String r : readList){
                	data += r;
               }
   			return data;
   }
   public static Map<String, String> splitQuery(String uri) throws UnsupportedEncodingException, MalformedURLException {
        URL url = new URL(uri);
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        String query = url.getQuery();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }
   
   String link="https://drive.google.com/open?id=11wtw6iY4rmeoAZzhsrodhFAop8CV-kEY";
   //GoogleDriveTest.Drive("link");
   public static String Drive(String link) throws IOException{
       String cache = "";
   	 long timeout = 10;
   	 String filename=getMd5("AA"+link+"A3Code");
   	 // test to see if a file exists
       File file;
       file = new File("cache/"+filename+".cache");
       if (file.exists() && file.isFile())
       {	
   		String fopen = fileGetContents("cache/"+filename+".cache");
   		String[] data;
                data = explode("@@", fopen);
   		Date now = getDate("yyyy-MM-dd HH:mm:ss", 7*3600*1000);
   		long times = now.getTime()/1000 - Long.parseLong( data[0] );
   		if(times >= timeout) {
   			String id = jDriveId(link);
   			String linkdown = getlink(id);
            if(linkdown!=null){
            linkdown = linkdown.trim();
            }else{linkdown = "";}
   			String create_cache	= gdCache(link, linkdown);
   			String[] arrays = explode("|", create_cache);
   			cache = arrays[0];
   		}
   		else{
   			cache = data[1];
   		}
       }
   	else{
   		String id = jDriveId(link);
         String linkdown = getlink(id);
         if(linkdown!=null){
         linkdown = linkdown.trim();
         }else{linkdown = "";}
   		String create_cache = gdCache(link, linkdown);
   		String[] arrays = explode("|", create_cache);
   		cache = arrays[0];
   	}
       
   	return cache;
   }
   
   public static String getlink(String id) throws MalformedURLException, IOException{
   	String get="";
   	String link = "https://drive.google.com/uc?export=download&id="+id;
   	String page="";
   	
   	URL url = new URL(link); 
   	//open the connection to the above URL. 
   	URLConnection urlcon = url.openConnection();
   	//get the inputstream of the open connection. 
   	BufferedReader br = new BufferedReader(new InputStreamReader(urlcon.getInputStream())); 
   	String i; 
   			
   	//print the source code line by line. 
   	while ((i = br.readLine()) != null) 
   	{ 
   		page+=i; 
   	
   	}
      Map<String, List<String>> infoHeader = urlcon.getHeaderFields();
   	
   	get=locheader(infoHeader);
   
   	if (get != ""){
   	} else {
   		String confirm = jGConfirmCode(page,4);
   		String linkdowngoc = "https://drive.google.com/uc?export=download&id=$id&confirm="+confirm;
   		
   		URL urlC = new URL(linkdowngoc); 
   		//open the connection to the above URL. 
   		URLConnection urlconC = urlC.openConnection();
   		//get the inputstream of the open connection. 
   		BufferedReader brC = new BufferedReader(new InputStreamReader(urlconC.getInputStream())); 
   		String iC; 
   		page="";	
   		//print the source code line by line. 
   		while ((iC = brC.readLine()) != null) 
   		{ 
   			page+=iC; 
   		
   		}
   		Map<String, List<String>> infoHeaderC = urlconC.getHeaderFields();
   		get=locheader(infoHeaderC);
   		
   	}
   	return get;
   }
   
   public static String jDriveId(String gurl) throws UnsupportedEncodingException, MalformedURLException {
        String gid, start, end=""; int ini,len; Map<String, String> parts;
        int i1 = gurl.indexOf("/edit");
        int i2 = gurl.indexOf("?id=");
        int i3 = gurl.indexOf("/view");
        if( gurl.contains("/edit") ){
            System.out.println("idijaa2--"+i1+"--2--");
            gurl = gurl.replace("/edit","/view");
        }
        else if( gurl.contains("?id=") ) {
            System.out.println("idijaa3--"+i2+"--3--");

            parts = splitQuery(gurl);
            return parts.get("id");
            //return "dsdsdd2";
        } else if ( gurl.contains("/view") ) {
            System.out.println("idijaa4--"+i3+"--4--");
            gurl = gurl + "/view";
        }
        else{ System.out.println("idijaa5--"+i1+"-*-"+i2+"-*-"+i3+"--5--"); }
        start  = "file/d/";
        end    = "/view";
        gurl = " " + gurl;
        ini    = gurl.indexOf(start);
        if (ini == 0) {
            return "";
        }
        ini += start.length();
        len = gurl.indexOf(end, ini) - ini;

        gid=gurl.substring(ini,ini+len);
        return gid;
    }
   
   public static String jGConfirmCode(String jgsource, int postlen){
         
         String jgcode="",ini="";
         if(jgsource.length()>11){
         int start=-1, len=-1, end=-1, prestart=-1, prelen=-1;
      
         ini="confirm="; len=ini.length(); start = jgsource.indexOf(ini);
         prestart=start+len; end = prestart+postlen;
      
         jgcode = jgcode+jgsource.substring(prestart, end);
         }
      
         return jgcode;
   }
   
   //New cache
   public static String gdCache(String link, String source) {
   	String msn="";
   	Date time = getDate("yyyy-MM-dd HH:mm:ss", 7*3600*1000);
   	String filename = getMd5("AA"+link+"A3Code");
   	String string = time.getTime()/1000+"@@"+source;
   	
   	fwrite("cache/"+filename+".cache", string);
   	
   	File file = new File("cache/"+filename+".cache");
      if (file.exists() && file.isFile())
      {
   		msn = source;	
   	} else {
   		msn = source;
   	}
   	return msn;
   }
   
   public static String locheader(Map<String, List<String>> header){
   	//String[] temp = explode("\r\n", page);String location=null;
   	Map<String, List<String>> infoheader=header;String[] temp2=null; String location = null;
   	  //To get a map of all the fields of http header 

        //print all the fields along with their value. 
        for (Map.Entry<String, List<String>> mp : infoheader.entrySet()) 
        { 
            System.out.print(mp.getKey() + " : "); 
            System.out.println(mp.getValue().toString()); 
        }
        List<String> loc1 = infoheader.get("Location");
        if(loc1!=null){ 
            location = loc1.get(0);
        }else{
            //location = "";
        }
   	return location;
   }
}