package kdu.backend.hw2.q3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class APIResponseParser {

    private static final Logger logger = LogManager.getLogger(APIResponseParser.class);
    public static Book parse(String response) {
        Book book = new Book();

        //title
        String endRule = "<";
        String startRule = "<title>";
        String title = parse(response, startRule, endRule);
        book.setTitle(title);

        //name
        endRule = "<";
        startRule = "<name>";
        String authorName = parse(response, startRule, endRule);
        book.setAuthor(authorName);

        //publication year
        endRule = "<";
        startRule = "<original_publication_year type=\"integer\">";
        String publicationYear = parse(response, startRule, endRule);
        book.setPublicationYear(Integer.parseInt(publicationYear));

        //average rating
        endRule = "<";
        startRule = "<average_rating>";
        String averageRating = parse(response, startRule, endRule);
        book.setAverageRating(Double.parseDouble(averageRating));

        //ratings count
        endRule = "<";
        startRule = "<ratings_count type=\"integer\">";
        String ratingsCount = parse(response, startRule, endRule);
        ratingsCount = ratingsCount.replace(",","");
        book.setRatingsCount(Integer.parseInt(ratingsCount));

        //image url
        endRule = "<";
        startRule = "<image_url>";
        String imageUrl = parse(response, startRule, endRule);
        book.setImageUrl(imageUrl);

        // Your code
        return book;
    }

    private static String parse(String response,String startRule, String endRule){
        int startIndex = response.indexOf(startRule);
        String stringFromStartRule = response.substring(startIndex+startRule.length());
        int endIndex = stringFromStartRule.indexOf(endRule);
        return stringFromStartRule.substring(0,endIndex);
    }

    public static void main(String[] args) {
        String response = "<work>" +
        "<id type=\"integer\">2361393</id>" +
        "<books_count type=\"integer\">813</books_count>" +
        "<ratings_count type=\"integer\">1,16,315</ratings_count>" +
        "<text_reviews_count type=\"integer\">3439</text_reviews_count>" +
        "<original_publication_year type=\"integer\">1854</original_publication_year>" +
        "<original_publication_month type=\"integer\" nil=\"true\"/>" +
        "<original_publication_day type=\"integer\" nil=\"true\"/>" +
        "<average_rating>3.79</average_rating>" +
        "<best_book type=\"Book\">" +
        "<id type=\"integer\">16902</id>" +
        "<title>Walden</title>" +
        "<author>" +
        "<id type=\"integer\">10264</id>" +
        "<name>Henry David Thoreau</name>" +
        "</author>" +
        "<image_url>" +
        "http://images.gr-assets.com/books/1465675526m/16902.jpg" +
        "</image_url>" +
        "<small_image_url>" +
        "http://images.gr-assets.com/books/1465675526s/16902.jpg" +
        "</small_image_url>" +
        "</best_book>" +
        "</work>";
        Book book = APIResponseParser.parse(response);

        String str = "\n"+"Title : "+book.getTitle()+"\n"+
                    "Author : "+book.getAuthor()+"\n"+
                    "Publication Year : "+book.getPublicationYear()+"\n"+
                    "Average Rating : "+book.getAverageRating()+"\n"+
                    "Ratings Count : "+book.getRatingsCount()+"\n"+
                    "Image URL : "+book.getImageUrl()+"\n";

        logger.info(str);
    }

}
