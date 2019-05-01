package com.example.asyncsample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView ivCityList = findViewById(R.id.ivCityList);

        List<Map<String, String>> cityList = new ArrayList<>();

        Map<String, String> city = new HashMap<>();

        city.put("name", "大阪");
        city.put("id", "270000");
        cityList.add(city);

        city = new HashMap<>();
        city.put("name", "神戸");
        city.put("id", "300000");
        cityList.add(city);

        String[] from = {"name"};
        int[] to = {android.R.id.text1};

        SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, cityList, android.R.layout.simple_list_item_1, from, to);

        ivCityList.setAdapter(adapter);

        ivCityList.setOnItemClickListener(new ListItemClickListener());
    }


    private class ListItemClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?>parent, View view,int position,long id){
            Map<String,String> item = (Map<String,String>)parent.getItemAtPosition(position);
            String cityName = item.get("name");
            String cityId = item.get("id");

            TextView tvCityName = findViewById(R.id.tvCityName);
            tvCityName.setText(cityName + "の天気；" );

            TextView tvWeatherTelop = findViewById(R.id.tvWeatherTelop);

            TextView tvWeatherDesc = findViewById(R.id.tvWeatherDesc);

            WeatherInfoReceiver receiver = new WeatherInfoReceiver(tvWeatherTelop,tvWeatherDesc);

            receiver.execute(cityId);
        }
    }

    private class WeatherInfoReceiver extends AsyncTask<String,String,String>{
        private TextView _tvWeatherTelop;

        private TextView _tvWeatherDesc;

        public WeatherInfoReceiver(TextView tvWeatherTelop,TextView tvWeatherDesc){
            _tvWeatherTelop = tvWeatherTelop;
            _tvWeatherDesc = tvWeatherDesc;
        }

        @Override
        public String doInBackground(String... params){
            String id = params[0];

            String urlStr = "https://weather.livedoor.com/forecast/webservice/json/v1?city="+id;

            String result = "";

            return result;

        }

        @Override
        public void onPostExecute(String result){
            String telop = "";
            String desc = "";

            _tvWeatherTelop.setText(telop);
            _tvWeatherDesc.setText(desc);

        }

    }
}
