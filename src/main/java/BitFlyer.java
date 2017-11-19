import com.google.gson.JsonObject;

public class BitFlyer {
  String host;
  public BitFlyer() {
    this.host = "https://api.bitflyer.jp/";
  }

  public double fetchMidPrice() {
    SubmitUrl submitUrl = new SubmitUrl();
    JsonObject jsonObj = submitUrl.callGet(host + "v1/board");
    String strMidPrice = jsonObj.get("mid_price").getAsString();
    return Double.parseDouble(strMidPrice);
  }
}
