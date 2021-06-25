import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RandomWiki {

    public static void main( String[] args ) {
         getRandomWikiInfo();
    }

    public static void getRandomWikiInfo(){
        Document doc = null;
        try {
            doc = Jsoup.connect("https://en.wikipedia.org/wiki/Special:Random").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert doc != null;
        Elements paragraphs = doc.select("p:not(:has(#coordinates))");
        Elements firstheading = doc.select("h1:not(:has(#firstheading.firstheading))");
        Element fh = firstheading.get(0);
        System.out.println(fh.text() + "\n");

        StringBuilder sb;

        for (Element allParagraphs : paragraphs) {
            sb = new StringBuilder(String.valueOf(allParagraphs.text()));
            int j = 0;
            while ((j = sb.indexOf(" ", j + 100)) != -1) {
                sb.replace(j, j + 1, "\n");
                System.out.println();
            }
            System.out.println(sb);
        }
    }
}