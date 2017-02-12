package com.ce.sdu.news.activity;
/**
 * Created by Rauan on 09/02/17.
 */
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ce.sdu.news.R;
import com.ce.sdu.news.adapter.NewsAdapter;
import com.ce.sdu.news.model.NewsItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private List<NewsItem> list;
    RecyclerView recyclerView;
    NewsAdapter myAdapter;
    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        new NewsGet().execute();
        return rootView;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }














    class NewsGet extends AsyncTask<Void, Void, String> {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";
        @Override
        protected String doInBackground(Void... params) {
            // получаем данные с внешнего ресурса
            try {
                URL url = new URL("https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=c1f8af39381d4b38a73f1d23f02fb0bd");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                resultJson = buffer.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultJson;
        }
        @Override
        protected void onPostExecute(String strJson) {
            super.onPostExecute(strJson);
            JSONObject dataJsonObj = null;
            try {
                dataJsonObj = new JSONObject(strJson);
                JSONArray lineItems = dataJsonObj.getJSONArray("articles");
                list = new ArrayList<>();
                for(int i = 0; i<lineItems.length();i++) {
                    JSONObject c = lineItems.getJSONObject(i);
                    String urlimg = c.getString("urlToImage");
                    String author = c.getString("author");
                    String title = c.getString("title");
                    String desc = c.getString("description");
                    String url = c.getString("url");
                    list.add(new NewsItem(title,desc,urlimg ,url));
                }
                recyclerView = (RecyclerView) getActivity().findViewById(R.id.rcview);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                myAdapter = new NewsAdapter(list,getActivity());
                recyclerView.setAdapter(myAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (NewsItem n :list){
                        Log.d("LOGS",n.getTitle());
                    }

                }
            }).start();
        }
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
    @Override
    public void onDetach() {
        super.onDetach();
    }
}