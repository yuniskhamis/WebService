public class Wiki extends ServiceCommunicator{


    public Wiki (String wikipediaTerm) {
        super("https://en.wikipedia.org/w/api.php?action=query&list=search&srsearch=" + wikipediaTerm+ "&format=json");
    }
    public static void main(String[] argv) {
        System.out.println(new Wiki("json").get());
    }



}
