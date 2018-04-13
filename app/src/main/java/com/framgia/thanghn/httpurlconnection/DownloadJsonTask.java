package com.framgia.thanghn.httpurlconnection;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static java.lang.System.in;

/**
 * Created by thang on 4/13/2018.
 */

public class DownloadJsonTask extends AsyncTask<Void, Integer, Void> {
    private String data = "";
    private String user = "";
    private String users = "";
    private ProgressBar progressBar;
    Integer i;
    Integer size;

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://api.github.com/users/google/repos");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data += line;
            }

            JSONArray jsonArray = new JSONArray(data);
            size=jsonArray.length();
            for (i = 0; i < jsonArray.length(); i++) {
                publishProgress(i,size);
                JSONObject ob = (JSONObject) jsonArray.get(i);
                user = "id: " + ob.get("id") + "- nam: " + ob.get("name") + "\n" +
                        "url: " + ob.get("url") + "\n";
                users += user + "\n";
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        for (int j=values[0];j<values[1];j++){
//            if (j<values[1])
//            Log.e("ffff",values[0]+" "+values[1]);
//            else
//            {
//                Log.e("ffff","done");
//            }
            MainActivity.progressBar.setProgress(j);
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.txtList.setText(users);
        MainActivity.progressBar.setVisibility(View.GONE);
    }
}