public class Zipcode extends ServiceCommunicator{


    public Zipcode (String zipCode) {

        super("http://api.zippopotam.us/us/" + zipCode);
    }
    public static void main(String[] argv) {
        System.out.println(new Zipcode("43123").get());
    }
}
