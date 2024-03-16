package droidrocks.com.namaztimeapp.Utils;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class FileUtils {

    public static final String TAG = "FileUtils";

    public static String readTextFromAssets(Context context, String filename) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            // Open the file from assets
            InputStream inputStream = context.getAssets().open(filename);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            // Read each line from the file
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n"); // Add newline character for each line
            }
        } catch (Exception e) {
            Log.d(TAG, "readTextFromAssets: "+e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the BufferedReader
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // Return the contents of the file as a String
        return stringBuilder.toString();
    }

    public static String loadJSONFromAsset(Context context, String jsonFileName) {
        // This method will return JSON data from a local JSON file
        String json = null;
        try {
            InputStream is = context.getAssets().open(jsonFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
