import java.io.File;

public class Main {
  public static void main(String[] args) {
    BitFlyer client = new BitFlyer();
    double price = client.fetchMidPrice();
    String strPrice = String.valueOf(price);
    String path = new File(".").getAbsoluteFile().getParent();
    // ファイルパスの設定を各自で行う
    String filepath = path + "/apple_script/mac_alert.script";

    try {
      Process process = new ProcessBuilder("osascript", filepath, strPrice).start();
    } catch(Exception ex) {
      System.out.println(ex);
    }
  }
}
