package edison.vidya.cloud_httppost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private JSONArray jsonarray;
    private ArrayList ar_date,ar_devname,ar_swbnum,ar_devtype,ar_modetype;
    private String str_date,str_devname,str_swbnum,str_devtype,str_modetype;
    private ListView listview;
    Button day,week,month,year;
    private String buttontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview= (ListView) findViewById(R.id.listview);
        day= (Button) findViewById(R.id.btn_day);
        week= (Button) findViewById(R.id.btn_week);
        month= (Button) findViewById(R.id.btn_month);
        year= (Button) findViewById(R.id.btn_year);

        day.setOnClickListener(this);
        week.setOnClickListener(this);
        month.setOnClickListener(this);
        year.setOnClickListener(this);


      /*  Thread t = new Thread() {
            public void run() {
                try {
                    post();
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
            }


        };
        t.start();
*/


    }

    public void post() throws JSONException, IOException {

        // Creating HTTP client
        HttpClient httpClient = new DefaultHttpClient();
        // Creating HTTP Post
        HttpPost httpPost = new HttpPost("http://androidblog.esy.es/AndroidJSon/Subjects.php");
        List<NameValuePair> nameValuePair = new ArrayList<>(2);
        nameValuePair.add(new BasicNameValuePair("id", "1"));
        nameValuePair.add(new BasicNameValuePair("type", buttontext));
        // Url Encoding the POST parameters
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }
        // Making HTTP Request
        try {
            final HttpResponse response = httpClient.execute(httpPost);
            String responseBody = null;

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseBody = EntityUtils.toString(entity);
                jsonarray = new JSONArray(responseBody);
                ar_date = new ArrayList();
                ar_devname = new ArrayList();
                ar_swbnum = new ArrayList();
                ar_devtype = new ArrayList();
                ar_modetype = new ArrayList();


                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    //   num1 = jsonobject.getString("DeviceName");

                    str_date = jsonobject.getString("id");
                    str_devname = jsonobject.getString("subjects");
                    str_swbnum = jsonobject.getString("id");
                    str_devtype = jsonobject.getString("id");
                    str_modetype = jsonobject.getString("id");

                    ar_date.add(str_date);
                    ar_devname.add(str_devname);
                    ar_swbnum.add(str_swbnum);
                    ar_devtype.add(str_devtype);
                    ar_modetype.add(str_modetype);

                }

                    }


                Log.d("TAG", "Http post Response: " + responseBody);

            Log.d("TAG", responseBody);

            //final String finalResponseBody = responseBody;

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    listview.setAdapter(new CustomAdapter(MainActivity.this, ar_date, ar_devname, ar_swbnum, ar_devtype, ar_modetype));

                }
            });
        } catch (IOException e) {
            // writing exception to log
            e.printStackTrace();

        } /*catch (JSONException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_day:
               // DataAdapterClassList.clear();
                buttontext="day";
                Thread t = new Thread() {
                    public void run() {
                        try {
                            post();
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                    }


                };
                t.start();
               // JSON_WEB_CALL();
                break;
            case R.id.btn_week:
                buttontext="week";
                 t = new Thread() {
                    public void run() {
                        try {
                            post();
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
                t.start();
                break;

            case R.id.btn_month:
                buttontext="month";
                 t = new Thread() {
                    public void run() {
                        try {
                            post();
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
                t.start();
                break;

            case R.id.btn_year:
                 t = new Thread() {
                    public void run() {
                        try {
                            post();
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
                t.start();
                break;

        }
    }
}

