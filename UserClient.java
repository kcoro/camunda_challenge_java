/*
    UserClient is a java client which makes a GET request to a REST API at https://reqres.in/api/users
    Returns a list of first and last names for each user.
    Author: Kevin Coronato
    Date: 04/15/2021
*/
import java.util.List;
import java.util.ArrayList;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import com.google.gson.Gson;

public class UserClient {
    // queryAPI calls a REST API, and retrieves user data
    // storing results in and returning a List of type UserDTO
    public List<UserDTO> queryAPI() {
        List<UserDTO> users = new ArrayList<>();
        var client = HttpClient.newHttpClient();
        int pageNum = 1;
        int total_pages = 1;
        String apiURL = "https://reqres.in/api/users?page=" + pageNum;
        var request = HttpRequest.newBuilder()
            .uri(URI.create(apiURL))
            .GET()
            .build();

        // Make initial GET request to REST API Endpoint
        // Execute additional GET requests if there is more than 1 page of data.
        while (pageNum <= total_pages) {
            try {
                var response = client.send(request, HttpResponse.BodyHandlers.ofString());
                APIDTO resJson = new Gson().fromJson(response.body(), APIDTO.class);
                total_pages = resJson.total_pages;
                pageNum++;
                // Iterate over json results, append each user to users list.
                for (UserDTO user : resJson.data) {
                    users.add(user);
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }

        return users;
    }

    public static void main(String[] args) {
        var users = new UserClient().queryAPI();

        // Iterate over list of users, print first and last name to stdout.
        for (UserDTO user : users) {
            System.out.println(user.first_name + " " + user.last_name);
        }
    }
}
