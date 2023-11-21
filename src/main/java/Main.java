import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        makeGetRequest();
        makePostRequest();
    }

    private static void makeGetRequest(){
        try{
            URL myURL = new URL("https://sefb-lecture5-http-b68d7d1a9cb4.herokuapp.com/");
            HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "text/html");
            conn.setRequestProperty("charset", "utf-8");

            BufferedReader in = new BufferedReader(new InputStreamReader(myURL.openStream()));
            String inputLine;
            while((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
            in.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());

        }
    }

    private static void makePostRequest(){
        // Set up Body Data
        String message = "Hello Servlet";
        byte[] body = message.getBytes(StandardCharsets.UTF_8);

        URL myURL = null;
        try {
            myURL = new URL("https://sefb-lecture5-http-b68d7d1a9cb4.herokuapp.com/");
            HttpURLConnection conn = null;
            conn = (HttpURLConnection) myURL.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept","text/html");
            conn.setRequestProperty("charset","utf-8");
            conn.setRequestProperty("Content-Length", Integer.toString(body.length));
            conn.setDoOutput(true);
            try(OutputStream outputStream = conn.getOutputStream()){
                outputStream.write(body,0,body.length);
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String inputLine;
            while((inputLine = bufferedReader.readLine()) != null){
                System.out.println(inputLine);
            }
            bufferedReader.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
