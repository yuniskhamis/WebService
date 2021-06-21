import java.util.InputMismatchException;
import java.util.Scanner;

import org.json.*;

public class MyApp {
    public static void main(String[] argv) {
        try {
            Scanner in = new Scanner(System.in);
            menu();
            String cmd = in.next();

            while (!(cmd.equals("quit") || cmd.equals("stop"))) {

                switch (cmd) {
                    case "tv" -> displayShowName();
                    case "music" -> displaySongName();
                    case "zipcode" -> displayZipcode();
                    case "wiki" -> displayWikipediaTerm();

                    default -> System.out.println("Don't recognize input. Try a valid command. ");
                }
                menu();
                cmd = in.next();
            }
            System.out.println("The program has ended.");
        } catch (InputMismatchException e) {
            e.printStackTrace();
            System.out.println("That was an incorrect entry. Try entering a String data type. Please try again!");
        }
    }

    public static void displayShowName() {
        System.out.println("Enter a tv show and use hyphens to represent spaces");
        Scanner in = new Scanner(System.in);
        String cmd = in.next();
        JSONObject myResponse = new JSONObject(new ServiceCommunicator("http://api.tvmaze.com/singlesearch/shows?q=" + cmd).get());
        System.out.println("Name: " + myResponse.getString("name"));
        System.out.println("Type: " + myResponse.getString("type"));
        System.out.println("Language: " + myResponse.getString("language"));
        System.out.println("Status: " + myResponse.getString("status"));
        System.out.println("Runtime: " + myResponse.getInt("runtime"));
        System.out.println("Average Runtime: " + myResponse.getInt("averageRuntime"));
        System.out.println("Premiered: " + myResponse.getString("premiered"));
        JSONObject values = myResponse.getJSONObject("schedule");
        System.out.println("Time: " + values.getString("time"));
        JSONArray c = myResponse.getJSONObject("schedule").getJSONArray("days");
        System.out.println("Days: " + c.getString(0) + "'s");
        JSONArray elements = myResponse.getJSONArray("genres");
        for (int i = 0; i < elements.length(); i++) {
            System.out.println("Genre " + (i + 1) + ": " + elements.getString(i));
        }
        JSONObject v = myResponse.getJSONObject("rating");
        System.out.println("Average Rating: " + v.getInt("average"));
        JSONObject a = myResponse.getJSONObject("network");
        System.out.println("Network Name: " + a.getString("name"));
        JSONObject b = myResponse.getJSONObject("network").getJSONObject("country");
        System.out.println("Country: " + b.getString("name"));
        System.out.println("Time Zone: " + b.getString("timezone"));

    }

    public static void displayZipcode() {
        System.out.println("Enter a zipcode and use hyphens to represent spaces");
        Scanner in = new Scanner(System.in);
        String cmd = in.next();
        JSONObject myResponse = new JSONObject(new ServiceCommunicator("http://api.zippopotam.us/us/" + cmd).get());
        System.out.println("Country: " + myResponse.getString("country"));
        System.out.println("Country Abbreviation: " + myResponse.getString("country abbreviation"));

        JSONArray values = myResponse.getJSONArray("places");

        for (int i = 0; i < values.length(); i++) {

            JSONObject place = values.getJSONObject(i);
            String state = place.getString("state");
            String stateAbbreviation = place.getString("state abbreviation");
            String placeName = place.getString("place name");
            String longitude = place.getString("longitude");
            String latitude = place.getString("latitude");

            System.out.println("State Abbreviation: " + stateAbbreviation);
            System.out.println("State: " + state);
            System.out.println("Place Name: " + placeName);
            System.out.println("Longitude: " + longitude);
            System.out.println("Latitude: " + latitude);
        }
        System.out.println("Postal Code: " + myResponse.getString("post code"));
    }

    public static void displaySongName() {
        System.out.println("Enter a song name and use hyphens to represent spaces");
        Scanner in = new Scanner(System.in);
        String cmd = in.next();
        JSONObject myResponse = new JSONObject(new ServiceCommunicator("https://itunes.apple.com/search?term=" + cmd + "\\&limit=1").get());

        JSONArray values = myResponse.getJSONArray("results");

        for (int i = 0; i < values.length(); i++) {
            JSONObject result = values.getJSONObject(i);
            String trackName = result.getString("trackName");
            String artistName = result.getString("artistName");
            int trackTimeInMilli = result.getInt("trackTimeMillis");
            String releaseDate = result.getString("releaseDate");
            String kind = result.getString("kind");
            String genre = result.getString("primaryGenreName");
            String country = result.getString("country");

            System.out.println("Track Name: " + trackName);
            System.out.println("Artist Name: " + artistName);
            System.out.println("Track Time in Milliseconds: " + trackTimeInMilli);
            System.out.println("Release Date: " + releaseDate);
            System.out.println("Country: " + country);
            System.out.println("Kind: " + kind);
            System.out.println("Genre: " + genre);
        }
    }

    public static void displayWikipediaTerm() {
        System.out.println("Enter a wikipedia term and use hyphens to represent spaces");
        Scanner in = new Scanner(System.in);
        String cmd = in.next();
        JSONObject myResponse = new JSONObject(new ServiceCommunicator("https://en.wikipedia.org/w/api.php?action=query&list=search&srsearch=" + cmd + "&format=json").get());
        JSONArray values = myResponse.getJSONObject("query").getJSONArray("search");
        for (int i = 0; i < values.length(); i++) {
            JSONObject search = values.getJSONObject(i);
            String title = search.getString("title");
            String snippet = search.getString("snippet");
            String timeStamp = search.getString("timestamp");

            System.out.println("Title : " + title);
            System.out.println("Snippet: " + snippet.replaceAll("\\<.*?\\>", ""));
            System.out.println("Time Stamp: " + timeStamp);

        }
    }

    public static void menu() {
        System.out.println("\nMain Menu\n");
        System.out.println("""
                List of Commands:
                Retrieve Tv show info (“tv”)
                Retrieve Music info (“music”)
                Retrieve Zipcode info (“zipcode”)
                Retrieve Wiki info (“wiki”)
                                
                Enter "quit" or "stop" to exit the program\040
                or continue running the program.""");
        System.out.print("Command> ");
    }
}