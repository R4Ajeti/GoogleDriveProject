import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.IOException;
import java.io.*;
 
/**
 * @author Crunchify.com
 *
 */
 
public class CrunchifyHTTPResponseHeader {
   
   // HTTP GET request
	private void sendGet(String url2) throws Exception {

		String url = url2;
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		//con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}
 
   public static void main(String[] args) {
      try {
         
        
         //First
         String gurlN="https://drive.google.com/uc?export=download&id=11wtw6iY4rmeoAZzhsrodhFAop8CV-kEY";
         URL textUrl = new URL(gurlN);
         InputStreamReader inStrReader = new InputStreamReader(textUrl.openStream());
         BufferedReader bufferReader = new BufferedReader(inStrReader);
         String stringbuffer;
         String stringText = "";
         while((stringbuffer = bufferReader.readLine()) != null){
            stringText += stringbuffer;
         }
         
         System.out.println("First "+stringText);
         String jcode=JGConfirmCode(stringText, 4);
         System.out.println("First "+jcode);
         
         
         //Second
         /*String NIDstatic="NID=144=fhiFYhbldQeLIINMhBz--DD6o_JwdVlvOa124LjbYiE-T10J_BJPaygNqEQll1YLHS-NCcQu09aR5HBctUCLZoMwSIDLzdsk1GgYPaOELKI0rNJ4IcTqeQhN4l_z88K56E36QU1fcFRjMtadazqy13UIwyhErxmMEd3DOye6v2k;";
         String NID=;
         String setCookie="download_warning_13058876669334088843_11wtw6iY4rmeoAZzhsrodhFAop8CV-kEY="+jcode+"; "+NID+" DRIVE_STREAM=7dlwk1WviXE; 1P_JAR=2018-11-2-17";
         URL textUrl1 = new URL(gurlN+"&confirm="+jcode);
         URLConnection conn2 = textUrl1.openConnection();

         // Set the cookie value to send

         conn2.setRequestProperty("Cookie", setCookie);

         // Send the request to the server

         conn2.connect();

         InputStreamReader inStrReader1 = new InputStreamReader(conn2.getInputStream());
         BufferedReader bufferReader1 = new BufferedReader(inStrReader1);
         String stringbuffer1;
         String stringText1 = "";
         while((stringbuffer1 = bufferReader1.readLine()) != null){
            stringText1 += stringbuffer1;
         }
      
         
         System.out.println("Second "+stringText1);
         String jcode1=JGConfirmCode(stringText1, 4);
         System.out.println("Second "+jcode1);
         */
         
         //third
         URL textUrl2 = new URL(gurlN+"&confirm="+jcode);
         InputStreamReader inStrReader2 = new InputStreamReader(textUrl2.openStream());
         BufferedReader bufferReader2 = new BufferedReader(inStrReader2);
         String stringbuffer2;
         String stringText2 = "";
         while((stringbuffer2 = bufferReader2.readLine()) != null){
            stringText2 += stringbuffer2;
         }
      
         System.out.println("Third "+stringText2);         
         String jcode2=JGConfirmCode(stringText2, 4);
         System.out.println("Third "+jcode2);
         
         
         
         //First
         URL url=new URL("https://drive.google.com/uc?export=download&id=11wtw6iY4rmeoAZzhsrodhFAop8CV-kEY");  
         URLConnection urlcon=url.openConnection();  
         InputStream stream=urlcon.getInputStream();
         String textN="";
         int n;  
         while((n=stream.read())!=-1){  
         //System.out.print((char)i); 
         textN += (char)n; 
         }  
         System.out.println("First:" + textN);
         
         String jcode3=JGConfirmCode(textN, 4);
         System.out.println("First:" + jcode3);
         
         
         //Second
         URL url1=new URL("https://drive.google.com/uc?export=download&id=11wtw6iY4rmeoAZzhsrodhFAop8CV-kEY&confirm="+ jcode3);  
         URLConnection urlcon1=url1.openConnection();  
         InputStream stream1=urlcon1.getInputStream();
         String textN1="";
         int m;  
         while((m=stream1.read())!=-1){  
         //System.out.print((char)i); 
         textN += (char)m; 
         }  
         System.out.println("Second:" + textN1);
         
         String jcode4=JGConfirmCode(textN1, 4);
         System.out.println("Second:" + jcode4);
         
         
         
         String urlN1="https://drive.google.com/uc?export=download&id=11wtw6iY4rmeoAZzhsrodhFAop8CV-kEY";
         URL obj = new URL(urlN1);
         URLConnection conn = obj.openConnection();
         
         
         Map<String, List<String>> map = urlcon1.getHeaderFields();
      
         System.out.println("Printing All Response Header for URL: " + url1.toString() + "\n");
         for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
         }
         String NID=(map.get("Set-Cookie")).get(1);
      	System.out.println("\n  ..."+(map.get("Set-Cookie")).get(1)+"\n");
         
         for(int i=0;i<NID.length();i++){
            char l=NID.charAt(i);
            if(l==';'){
               NID=NID.substring(0,i+1);
               break;
            }
         }
         
         System.out.println("\n  ..."+NID+"\n");
         System.out.println("\nGet Response Header By Key ...\n");
         List<String> contentLength = map.get("Content-Length");
         if (contentLength == null) {
            System.out.println("'Content-Length' doesn't present in Header!");
         } 
         else {
            for (String header : contentLength) {
               System.out.println("Content-Lenght: " + header);
            }
         }
         
         
          System.out.println("NID: " + NID );
          /*URL url = new URL("https://drive.google.com/uc?export=download&id=11wtw6iY4rmeoAZzhsrodhFAop8CV-kEY");
          HttpURLConnection connection = (HttpURLConnection)url.openConnection();
          HttpURLConnection.setFollowRedirects( true );
          connection.setDoOutput( true );
          connection.setRequestMethod("GET"); 
         
          PrintStream ps = new PrintStream( connection.getOutputStream() );
          ps.print(5);
          ps.close();
          connection.connect();*/
          //TODO: do next request with other url, but in same connection
          
          System.out.println("Starting LecNotify");
         //URL url = new URL("www.myurl.com");
         HttpURLConnection urlConn = null;
         BufferedReader in;
         urlConn = (HttpURLConnection)url.openConnection();
         urlConn.setRequestMethod("GET");
         urlConn.setDoInput (true);
         urlConn.setDoOutput (true);
         urlConn.setRequestProperty ("Content-Type","application/x-www-form-urlencoded");
     
         in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
         
         String stringbuffer5,stringText5 = "";
         while((stringbuffer5 = in.readLine()) != null){
            stringText5 += stringbuffer5;
         }
         
         System.out.println("Done: "+stringText5);
         
         String jcode5=JGConfirmCode(stringText5, 4);
         System.out.println("Done: " + jcode5);
          
      
      
         System.out.println("Starting LecNotify");
         //URL url = new URL("www.myurl.com");
         HttpURLConnection urlConn1 = null;
         BufferedReader in1;
         urlConn1 = (HttpURLConnection)url1.openConnection();
         urlConn1.setRequestMethod("GET");
         urlConn1.setDoInput (true);
         urlConn1.setDoOutput (true);
         urlConn1.setRequestProperty ("Content-Type","application/x-www-form-urlencoded");
         
         //DataOutputStream out = new DataOutputStream(urlConn.getOutputStream());
         //String content = "MYNAME=RYANBOHNERT";
         //out.writeBytes (content);
         //out.flush();
         //out.close();
     
         in1 = new BufferedReader(new InputStreamReader(urlConn1.getInputStream()));
         
         String stringbuffer6,stringText6 = "";
         while((stringbuffer6 = in1.readLine()) != null){
            stringText6 += stringbuffer6;
         }
         
         System.out.println("Done: "+stringText6);
         
         String jcode6=JGConfirmCode(stringText6, 4);
         System.out.println("Done: " + jcode6);

      
      
      
      
      } 
      catch (Exception e) {
         e.printStackTrace();
      }
   }
   //https://drive.google.com/uc?export=download&id=11wtw6iY4rmeoAZzhsrodhFAop8CV-kEY
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