public class Television extends ServiceCommunicator{

    public Television(String showName) {
        super("http://api.tvmaze.com/singlesearch/shows?q=" + showName);
    }


    public static void main(String[] argv) {
        System.out.println(new Television("flash").get());
    }
}