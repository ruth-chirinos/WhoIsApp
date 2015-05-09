package com.ruth.android.whoisapp.tasks;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by RUTH on 15/05/08.
 */
public class SearchTask extends AsyncTask<String, Void, JSONObject>  {

    public static final String LOG_TAG = SearchTask.class.getSimpleName();

    //Variables


    @Override
    protected JSONObject doInBackground(String... params) {
        Log.i(LOG_TAG,"******** doInBackground");
        //Validamos q el valor del dominio llegue
        if (params.length ==  0) {
            return null;
        }

        String domain = params[0];
        HttpURLConnection httpURLConnection = null;
        BufferedReader reader = null;
        // Will contain the raw JSON response as a string.
        String forecastJsonStr = null;

        try{

        //a6572eed52e9555f0e6e3ca3b4e2ee53
            final String FORECAST_BASE_URL = "https://jsonwhois.p.mashape.com/api/v1/whois?";
            final String QUERY_PARAM = "domain";
            final String API_KEY = "Authorization";

            Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, domain)
                    .build();

            URL url = new URL(builtUri.toString());

            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("X-Mashape-Key", "jso3vIhziamshNGwJ4fNPTc253eap1N2QVqjsn8cLGQ0jSiO7h");
            httpURLConnection.setRequestProperty("Authorization","Token token=a6572eed52e9555f0e6e3ca3b4e2ee53" );
            httpURLConnection.setRequestProperty("Accept","application/json" );
            httpURLConnection.connect();

            // These code snippets use an open-source library. http://unirest.io/java
//            HttpResponse<JsonNode> response = Unirest.get("https://jsonwhois.p.mashape.com/api/v1/whois?domain=google.com")
//                    .header("X-Mashape-Key", "jso3vIhziamshNGwJ4fNPTc253eap1N2QVqjsn8cLGQ0jSiO7h")
//                    .header("Authorization", "Token token=a6572eed52e9555f0e6e3ca3b4e2ee53")
//                    .header("Accept", "application/json")
//                    .asJson();

            InputStream inputStream = httpURLConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                Log.i(LOG_TAG, "No se ha recuperado nada.");
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                Log.i(LOG_TAG, "El buffer no tiene contenido.");
                return null;
            }
            forecastJsonStr = buffer.toString();

        }catch(IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            return null;
        }catch(Exception e) {
            Log.e(LOG_TAG, "Error ", e);
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }

        try {
            return getDataFromJson(forecastJsonStr);
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }

        return null;
    }

    /*Obtener los datos en formato JSON*/
    public JSONObject getDataFromJson(String data) throws JSONException
    {
        JSONObject dataJson = new JSONObject(data);
        return dataJson;
    }


    @Override
    protected void onPostExecute(JSONObject results)
    {
        Log.i(LOG_TAG,"******** PostExecute");
        if(results!=null)
        {

        }
    }

}
