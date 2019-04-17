public class JGDriveTest{
   public static void main(String [] args){
      JGDriveStream JGDStreamObject = new JGDriveStream("https://drive.google.com/open?id=11wtw6iY4rmeoAZzhsrodhFAop8CV-kEY");
      JGDStreamObject.buildN();
      System.out.println(JGDStreamObject.getJGLink());
   }
}