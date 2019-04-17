import java.net.MalformedURLException; 
import java.net.URL;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.*;
import java.util.*;
import java.util.logging.*;

public class Tssde{
URL url;
HttpURLConnection urlConn;
DataOutputStream  printout;
DataInputStream   input;
 InputStream in;  
 RandomAccessFile out;
public static void main(String [] args){

Tssde ss = new Tssde();
ss.donkey();

}


    public void donkey(){
        try {
            url = new URL("ht-tp://rs289cg.rapidshare.com/files/70947297/1550378/Wise_Intelligent_-_Blessed_Be_The_Poor__2007_.rar?mirror=on&x=45&y=44");
        } catch (MalformedURLException ex) {
            Logger.getLogger(fuckifiknow.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // URL connection channel.
            urlConn = (HttpURLConnection) url.openConnection();
        } catch (IOException ex) {
            Logger.getLogger(fuckifiknow.class.getName()).log(Level.SEVERE, null, ex);
        }
    // Let the run-time system (RTS) know that we want input.
    urlConn.setDoInput (true);
    // Let the RTS know that we want to do output.
    urlConn.setDoOutput (true);
    // No caching, we want the real thing.
    urlConn.setUseCaches (false);
       
        //urlConn.setRequestProperty("Range", "bytes=" + "-" + "4500");
       
        try {
            urlConn.setRequestMethod("POST");
        } catch (ProtocolException ex) {
            Logger.getLogger(fuckifiknow.class.getName()).log(Level.SEVERE, null, ex);
        }
    // Specify the content type.
     
    urlConn.setRequestProperty("Connection", "keep-alive");    
    urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        try {
            urlConn.connect();
        } catch (IOException ex) {
            Logger.getLogger(fuckifiknow.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // Send POST output.
            printout = new DataOutputStream(urlConn.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(fuckifiknow.class.getName()).log(Level.SEVERE, null, ex);
        }
    String content = null;
        try {
        content = "name=dlf" + URLEncoder.encode(url.getQuery(), "UTF-8");
        } catch (UnsupportedEncodingException exp) {
        Logger.getLogger(fuckifiknow.class.getName()).log(Level.SEVERE, null, exp);       }
    try {
 
            printout.writeBytes(content);
            printout.flush();
            printout.close();
        } catch (IOException exp) {
            Logger.getLogger(fuckifiknow.class.getName()).log(Level.SEVERE, null, exp);
        }
     
        try {
             
             
            // Get response data.
            in = urlConn.getInputStream();
             
        } catch (IOException ex) {
            Logger.getLogger(fuckifiknow.class.getName()).log(Level.SEVERE, null, ex);
        }
    String str;
        try {
            out = new RandomAccessFile("something.rar", "rw");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(fuckifiknow.class.getName()).log(Level.SEVERE, null, ex);
        }
    byte[] buffer = new byte[1024];
    int downloaded = 0;
        try {
            int numRead;
         
              
                   while ((numRead = in.read(buffer)) != -1) {
                
                out.write(buffer, 0, numRead);
                downloaded += numRead;
                   
                         
                  System.out.println("Downloaded " + downloaded);
            }
        } catch (IOException ex) {
            Logger.getLogger(fuckifiknow.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            in.close();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(fuckifiknow.class.getName()).log(Level.SEVERE, null, ex);
        }

}
}