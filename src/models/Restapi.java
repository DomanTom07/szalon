package models;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Restapi {
    String host;
    public Restapi() {
        host = "http://[::1]:3000/";
    }
    public String getVehiclesAsString() {
        String response = null;
        try {
            response = tryGetVehiclesAsString();
        } catch (MalformedURLException e) {
            System.err.println("Hiba! Érvénytelen URL!");
        } catch (IOException e) {
            System.err.println("Hiba! A REST API elérése sikertelen!");
        }
        return response;
    }
    public String tryGetVehiclesAsString()
    throws MalformedURLException, IOException {
        String endpoint = "vehicles";
        String urlStr = host + endpoint;
        URL url = new URL(urlStr);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.connect();
        int responseCode = http.getResponseCode();
        // System.out.println(responseCode);
        String text = null;
        if (responseCode == 200) {
            InputStream inputStream = http.getInputStream();
            text = convertStreamToString(inputStream);
        } else {
            text = "--HIBA--";
        }
        // System.out.println(text);
        return text;
    }
    private String convertStreamToString(InputStream inputStream) {
        Reader reader = new InputStreamReader(inputStream);
        Scanner scanner = new Scanner(reader);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }
        scanner.close();
        return stringBuilder.toString();
    }
    public ArrayList<Vehicle> getVehicles() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Vehicle[] vehiclesArray = gson.fromJson(getVehiclesAsString(), Vehicle[].class);
        ArrayList<Vehicle> vehicleList = new ArrayList<>(Arrays.asList(vehiclesArray));
        return vehicleList;
    }
}
