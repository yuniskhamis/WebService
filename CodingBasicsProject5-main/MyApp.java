import java.util.InputMismatchException;
import java.util.Scanner;

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
        System.out.println(new Television(cmd).get());
    }

    public static void displayZipcode() {
        System.out.println("Enter a zipcode and use hyphens to represent spaces");
        Scanner in = new Scanner(System.in);
        String cmd = in.next();
        System.out.println(new Zipcode(cmd).get());
    }

    public static void displaySongName() {
        System.out.println("Enter a song name and use hyphens to represent spaces");
        Scanner in = new Scanner(System.in);
        String cmd = in.next();
        System.out.println(new Music(cmd).get());
    }

    public static void menu() {
        System.out.println("\nMain Menu\n");
        System.out.println("""
                List of Commands:
                Retrieve Tv show info (“tv”)
                Retrieve Music info (“music”)
                Retrieve Zipcode info (“zipcode”)
                                
                Enter "quit" or "stop" to exit the program\040
                or continue running the program.""");
        System.out.print("Command> ");
    }
}