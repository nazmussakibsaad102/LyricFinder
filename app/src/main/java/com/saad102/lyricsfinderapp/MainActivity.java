package com.saad102.lyricsfinderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    private EditText mEditTextArtistName;
    private EditText mEditTextSongName;
    private Button mButtonGetLyrics;
    private TextView mTextViewLyrics;
//https://api.lyrics.ovh/v1/Rihanna/Diamond#
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditTextArtistName = findViewById(R.id.edtArtistName);
        mEditTextSongName = findViewById(R.id.edtSongName);
        mButtonGetLyrics = findViewById(R.id.getLyrics);
        mTextViewLyrics = findViewById(R.id.txtLyrics);

        mButtonGetLyrics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://api.lyrics.ovh/v1/" + mEditTextArtistName.getText().toString() + "/" + mEditTextSongName.getText().toString();
                url.replaceAll(" ", "%20");
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            mTextViewLyrics.setText(response.getString("lyrics"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
                requestQueue.add(jsonObjectRequest);
            }
        });
    }
}