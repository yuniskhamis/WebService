public class Music extends ServiceCommunicator{

    public Music (String songName) {
        super("https://itunes.apple.com/search?term=" + songName + "\\&limit=1");
    }

    public static void main(String[] argv) {
        System.out.println(new Music("Surah-Ya-seen").get());
    }
}