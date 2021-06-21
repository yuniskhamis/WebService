import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/*
 Simplistic utility class to connect to a web server and retrieve data.
 Works for typical websites (but not very useful) and works for service calls that don't require authentication
 -fdg
 */
public class ServiceCommunicator {
    private HttpURLConnection conn;

/*
 Constructor - supply the target URL and try to make the connection
 */
    public ServiceCommunicator(String serviceURL) {
            try {
                URL url = new URL(serviceURL);
                URLConnection urlConnection = url.openConnection();
                conn = (HttpURLConnection)urlConnection;
            } catch(Exception ex) {
                System.err.println("**Error in constructor - Cannot create the URL or make the connection.");
                System.exit(1);
            }
    }
/*
 get() - read the reply from the webserver specifed in the constructor.
        returns a string that captured the output from that service.
 */
    public String get() {
         String urlString = "";
         String current;

         try {
             BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
             while((current = in.readLine()) != null) {
                 urlString += current;
             }
         } catch (IOException iox) {
             System.err.println("**Error in get().  Cannot read from URL");
         }
         return urlString;
      }

/*
 main method used just to test this class in isolation.  You will learn more about testing in another course
 */
      public static void main(String[] argv) {
        System.out.println(new ServiceCommunicator("http://api.zippopotam.us/us/" + "10011").get());
        System.out.println(new ServiceCommunicator("https://itunes.apple.com/search?term=" + "bbking" + "\\&limit=1").get());
        System.err.println("==================================");
        System.out.println(new ServiceCommunicator("http://www.google.com").get());
      }
}
