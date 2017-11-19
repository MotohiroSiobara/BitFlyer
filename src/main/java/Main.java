public class Main {
  public static void main(String[] args) {
    BitFlyer client = new BitFlyer();
    double price = client.fetchMidPrice();
    String strPrice = String.valueOf(price);
    String homePath = System.getenv("HOME");
    String filepath = homePath + "/projects/AppleScript/MacAlert.script";
    try {
      Process process = new ProcessBuilder("osascript", filepath, strPrice).start();
    } catch(Exception ex) {
      System.out.println(ex);
    }
  }
}
