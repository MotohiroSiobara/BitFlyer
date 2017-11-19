import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class SubmitUrl {
  public SubmitUrl() {
  }

  public JsonObject callGet(String strUrl){
    HttpURLConnection con = null;
    String result = "";
    try {
      URL url = new URL(strUrl);
      con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
      con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
      con.connect();
      result = parseResponse(con);
    }catch (Exception e1) {
      e1.printStackTrace();
    } finally {
      if (con != null) {
        // コネクションを切断
        con.disconnect();
      }
    }
    return stringToJson(result);
  }

  private String parseResponse(HttpURLConnection con) throws Exception {
    StringBuffer result = new StringBuffer();
    final int status = con.getResponseCode();
    if (status == HttpURLConnection.HTTP_OK) {
      // 通信に成功した
      // テキストを取得する
      final InputStream in = con.getInputStream();
      String encoding = con.getContentEncoding();
      if(null == encoding){
        encoding = "UTF-8";
      }
      final InputStreamReader inReader = new InputStreamReader(in, encoding);
      final BufferedReader bufReader = new BufferedReader(inReader);
      String line = null;
      // 1行ずつテキストを読み込む
      while((line = bufReader.readLine()) != null) {
        result.append(line);
      }
      bufReader.close();
      inReader.close();
      in.close();
    }else{
      System.out.println(status);
    }
    return result.toString();
  }

  private JsonObject stringToJson(String response) {
    JsonObject jsonObj = (JsonObject) new Gson().fromJson(response, JsonObject.class);
    return jsonObj;
  }
}
