import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Scanner;

public class CountriesRestAPISolution {
    private static Scanner scanner;

    public static void main(String[] args) throws IOException, JSONException {
        System.out.println("Please enter country name or exit");
        scanner = new Scanner(System.in);
        String userInput = scanner.next();
        getData(userInput);
    }

    private static void getData(String userInput) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://restcountries.eu/rest/v2/name/" + userInput)
                .build();
        Response response = client.newCall(request).execute();
        String jsonData = response.body().string();
        if (response.code() == 404){
            System.out.println("No such country..");
            chooseAgain();
        }
        JSONArray mainJsonArray = new JSONArray(jsonData);
        JSONObject mainJsonObject = (JSONObject) mainJsonArray.get(0);
        String region = mainJsonObject.getString("region");
        JSONArray callingCodes = mainJsonObject.getJSONArray("callingCodes");
        JSONArray borders = mainJsonObject.getJSONArray("borders");
        JSONObject currencies = (JSONObject) mainJsonObject.getJSONArray("currencies").get(0);
        String symbol = (String) currencies.get("symbol");
        System.out.println(region + " " + callingCodes + " " + borders + " " + symbol);
        chooseAgain();
    }

    private static void chooseAgain() throws IOException {
        System.out.println("Please enter country name or exit");
        String userInput = scanner.next();
        if (!userInput.equals("exit")){
            getData(userInput);
        }
        else{
            System.exit(0);
        }
    }
}