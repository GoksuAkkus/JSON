package com.example.student.jsonproject;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
ImageView myView;
EditText sehir;
TextView results;
String sehirci="";
    JSONObject myobj;
int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 myView=findViewById(R.id.imageView);
sehir=findViewById(R.id.sehirIsmi);
        results=findViewById(R.id.textView);

    }


    public void resimgetirgotur(View view) {


    }



    public void JSon(View view) {
        sehirci=sehir.getText().toString();

        String ApiUrl = "http://api.openweathermap.org/data/2.5/weather?q="+sehirci+",TR&appid=4e65b42fb4bc648afc90f0f5a5a9ba4f&units=metric";

        RequestQueue requestQueue=Volley.newRequestQueue(MainActivity.this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, ApiUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject object=null;
                try {
                    object=new JSONObject(response);
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
                 myobj=null;
                JSONObject myobj1=null;
                JSONObject myobj2=null;
                JSONObject myobj3=null;
                JSONObject myobj4=null;
                try {
                    myobj=(JSONObject)object.getJSONArray("weather").get(0);
                    myobj1=(JSONObject)object.get("coord");
                    myobj2=(JSONObject)object.get("main");
                    myobj3=(JSONObject)object.get("sys");
                    myobj4=(JSONObject)object.get("wind");

                }
                catch (JSONException e){
                    e.printStackTrace();
                }
                try {

                    results.setText(
                            "id:"+myobj.getString("id")+"\n"+
                            "main:"+ myobj.getString("main")+"\n"+
                            "description:"+ myobj.getString("description")+"\n"  +

                            "lon:"+ myobj1.getString("lon")+"\n"+
                            "lat:"+ myobj1.getString("lat")+"\n"
+
                            "temp:"+ myobj2.getString("temp")+"\n"+
                                    "pressure:"+ myobj2.getString("pressure")+"\n"+
                                    "humidity:"+ myobj2.getString("humidity")+"\n"+
                                    "temp_min:"+ myobj2.getString("temp_min")+"\n"+
                                    "temp_max:"+ myobj2.getString("temp_max")+"\n"+
                                    "type:"+ myobj3.getString("type")+"\n"+
                                    "id:"+ myobj3.getString("id")+"\n"+
                                    "message:"+ myobj3.getString("message")+"\n"+
                                    "country:"+ myobj3.getString("country")+"\n"+
                                    "speed:"+ myobj4.getString("speed")+"\n"+
                                    "deg:"+ myobj4.getString("deg")+"\n"

                    );


                    try {
                        Glide.with(getApplicationContext()).load("http://openweathermap.org/img/w/"+myobj.getString("icon")+".png")
                                .thumbnail(0.5f)
                                .crossFade()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(myView);
                    }catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);


    }

}
