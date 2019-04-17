import java.io.*;  
import java.net.*;
import java.util.*;
public class URLConnectionExample {  
public static void main(String[] args){  
try{
    
   String location = getLink("https://drive.google.com/uc?export=download&id=11wtw6iY4rmeoAZzhsrodhFAop8CV-kEY");
   System.out.println("sd: " + location);

}catch(Exception e){System.out.println(e);}  
}
public static String GetHTML(HttpURLConnection urlConn) throws IOException{
String HTML="";BufferedReader in;
in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
String stringbuffer = "";
while((stringbuffer = in.readLine()) != null){
   HTML += stringbuffer;
}
return HTML;
}
public static String getLink(String gurl) throws MalformedURLException,IOException{
    String location="";
    URL url;
    HttpURLConnection urlConn = null;
    BufferedReader in;
         
    url=new URL(gurl);
    urlConn = (HttpURLConnection)url.openConnection();
    //Get Html page
    String stringText = GetHTML(urlConn);
    //Get Url Headers
    Map<String, List<String>> map = urlConn.getHeaderFields();
    String NID=getElementList("Set-Cookie", map);
    //Get Confirm Code
    String jcode=JGConfirmCode(stringText, 4);
    //Set Cookie
    String setCookie="download_warning_13058876669334088843_11wtw6iY4rmeoAZzhsrodhFAop8CV-kEY="+jcode+"; "+NID;

    URL urlN=new URL(gurl+"&confirm="+jcode);  
    HttpURLConnection urlConn2=(HttpURLConnection)urlN.openConnection();

    // Set the cookie value to send

    urlConn2.setRequestProperty("Cookie", setCookie);
    urlConn2.setRequestMethod("GET");
    urlConn2.setInstanceFollowRedirects(false);  //you still need to handle redirect manully.
    HttpURLConnection.setFollowRedirects(false);
    Map<String, List<String>> map2 = urlConn2.getHeaderFields();         
    location=getElementList("Location", map2);

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
public static String JGConfirmCode(String jgsource, int postlen){
      
      String jgcode="",ini="";
      if(jgsource.length()>11){
      int start=-1, len=-1, end=-1, prestart=-1, prelen=-1;
   
      ini="confirm="; len=ini.length(); start = jgsource.indexOf(ini);
      prestart=start+len; end = prestart+postlen;
   
      jgcode = jgcode+jgsource.substring(prestart, end);
      }
   
      return jgcode;
   }
}
/*
urlcon.setDoOutput(true);
urlcon.setDoInput(true);
urlcon.setRequestProperty("Connection", "Keep-Alive");
urlcon.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
urlcon.setRequestProperty("Accept-Language", "en-US,en;q=0.9");
urlcon.setRequestProperty("Referer", "https://drive.google.com/uc?export=download&id=11wtw6iY4rmeoAZzhsrodhFAop8CV-kEY&confirm="+jcode);
urlcon.setConnectTimeout(15000);
urlcon.setReadTimeout(15000);
urlcon.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36");
*/
/*urlConn.setRequestMethod("GET");
         urlConn.setDoInput (true);
         urlConn.setDoOutput (true);
         urlConn.setRequestProperty ("Content-Type","application/x-www-form-urlencoded");*/