
import com.restfb.json.JsonArray;
import java.text.ParseException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Stefania
 */
public class Main {

    private static boolean isdbcreated;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here

        isdbcreated = ConnectionJDBC.createDB();

        if (isdbcreated == true) {

            Scanner reader = new Scanner(System.in);  // Reading from System.in

            System.out.println("Enter an access Token: ");
            String accessToken = reader.next(); // Scans the next token of the input as an int.

            System.out.println("Enter the last X days to fetch posts: ");
            int lastXdays = reader.nextInt();

            System.out.println("Enter the Facebook page or ID to fetch the posts: ");
            String facebookPg = reader.next();

            PostsConsumer.fetchFacePosts(lastXdays, facebookPg, accessToken);

            System.out.println("Enter the date interval to fetch the posts from the table (SINCE then UNTIL): ");
            System.out.println("TYPE SINCE: ");
            String since = reader.next();

            System.out.println("TYPE UNTIL: ");
            String until = reader.next();

            JsonArray myjsonarray = PostsConsumer.getPosts(since, until);

            String jsonResultListValue;

            jsonResultListValue = myjsonarray.toString();
            System.out.println("Output 1 -> " + jsonResultListValue);

            myjsonarray = PostsConsumer.getVolume(since, until);

            jsonResultListValue = myjsonarray.toString();
            System.out.println("Output 2 -> " + jsonResultListValue);

        }else{
            System.out.println("DB has not been created successfully!!");
        }
    }

}
