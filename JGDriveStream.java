import java.io.*;  
import java.net.*;
import java.util.*;
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
import java.util.logging.Level;
import java.util.logging.Logger;

public class JGDriveStream{
   private String gurl=null;
   private String jglink=null;
   //set and get methods
   public void setGUrl(String gurl){this.gurl=gurl;}
   public String getJGLink(){return jglink;}
   public void setJGLink(String jglink){this.jglink=jglink;}
   //overloading constructors
   JGDriveStream(){
   }
   JGDriveStream(String gurl){
   this.gurl=gurl;
   }
   //build method
   void buildN(){
      if(this.gurl != null){
          try {
              jDriveTurn(this.gurl);
          } catch (IOException ex) {
              Logger.getLogger(JGDriveStream.class.getName()).log(Level.SEVERE, null, ex);
          } 
      }
   }

   //Md5 hash creator 
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
   // php explode method
   public static String[] explode(String separator, String stringToExplode){     
      return  stringToExplode.split(separator);
   }
   //Get string format belated date param @delay
   public static String getDateStr(String format, long delay){
      String timeStamp;
      timeStamp = new SimpleDateFormat(format).format( new Date( (delay)+( Calendar.getInstance().getTime() ).getTime() ) );
      return timeStamp;
   }
   //Get belated date param @delay 
   public static Date getDate(String format, long delay){
      Date timeStamp =  new Date( (delay)+( Calendar.getInstance().getTime() ).getTime() );
      return timeStamp;
   } 
   //Write @data to @filename
   private static void fwrite(String filename,String data) {
      try {
         Files.write(Paths.get(filename), data.getBytes());
      } 
      catch (IOException e) {
         e.printStackTrace();
      }
   }
   //Read data from @filename
   public static String fileGetContents(String filename) throws IOException{
      String data="";
      List<String> readList = Files.readAllLines(Paths.get(filename));
      for(String r : readList){
         data += r;
      }
      return data;
   }
   //Select element of respond header @uri from given map data   
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
   //Google Execution Trace Controller
   public String jDriveTurn(String link) throws IOException{
      String cache = "";
      long timeout = 900;
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
            }
            else{linkdown = "";}
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
         }
         else{linkdown = "";}
         String create_cache = gdCache(link, linkdown);
         String[] arrays = explode("|", create_cache);
         cache = arrays[0];
      }
      this.jglink=cache;
      return cache;
   }
   //Google streaming link generator
   public static String getlink(String id) throws MalformedURLException, IOException{
      String gurl = "https://drive.google.com/uc?export=download&id="+id;
      String location="";
      URL url;
      HttpURLConnection urlConn = null;
      BufferedReader in;
         
      url=new URL(gurl);
      urlConn = (HttpURLConnection)url.openConnection();
    //Get Html page
      String html = GetHTML(urlConn);
    //Get Url Headers
      Map<String, List<String>> map = urlConn.getHeaderFields();
      String NID=getElementList("Set-Cookie", map);
    //Get Confirm Code
      String jcode=jGConfirmCode(html, 4);
      System.out.println("JCode: "+jcode);
    //Set Cookie
      String setCookie="download_warning_13058876669334088843_11wtw6iY4rmeoAZzhsrodhFAop8CV-kEY="+jcode+"; "+NID;
   
      URL jgurlN=new URL(gurl+"&confirm="+jcode);  
      HttpURLConnection urlConn2=(HttpURLConnection)jgurlN.openConnection();
   
    // Set the cookie value to send
   
      urlConn2.setRequestProperty("Cookie", setCookie);
      urlConn2.setRequestMethod("GET");
      urlConn2.setInstanceFollowRedirects(false);  //you still need to handle redirect manully.
      HttpURLConnection.setFollowRedirects(false);
      Map<String, List<String>> map2 = urlConn2.getHeaderFields();         
      location=getElementList("Location", map2);
   
      return location;
   }
   //Google id generator
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
      } 
      else if ( gurl.contains("/view") ) {
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
   //Google Confirm Code Generator
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
   	File directory = new File("cache");
      if(!directory.exists()){
         directory.mkdir();
      } 
      fwrite("cache/"+filename+".cache", string);
      File file = new File("cache/"+filename+".cache");
      if (file.exists() && file.isFile())
      {
         msn = string;	
      } 
      else {
         msn = string;
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
      }
      else{
            //location = "";
      }
      return location;
   }
   public static String getElementList(String ele, Map<String, List<String>> mapList){
      String listEle="";
      listEle=(mapList.get(ele)).get(0);
      	         
      for(int i=0;i<listEle.length();i++){
         char l=listEle.charAt(i);
         if(l==';'){
            listEle=listEle.substring(0,i+1);
            break;
         }
      }
   
      return listEle;
   }
   // Server request (Get header)
   public static String GetHTML(HttpURLConnection urlConn) throws IOException{
      String HTML="";BufferedReader in;
      in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
      String stringbuffer = "";
      while((stringbuffer = in.readLine()) != null){
         HTML += stringbuffer;
      }
      return HTML;
   }
}