package com.example.coolweather.util;

import android.text.TextUtils;
import android.util.Log;

import com.example.coolweather.db.City;
import com.example.coolweather.db.County;
import com.example.coolweather.db.Province;
import com.example.coolweather.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Response;

/**
 * Created by jack on 2017/8/15.
 */

public class Utility {
    public static Weather handleWeatherResponse(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String WeatherContent = jsonArray.getJSONObject(0).toString();
            Log.d("GSOn1",WeatherContent);
            Log.d("GSOn2",jsonObject.toString());
            Log.d("GSOn3", response);
            return new Gson().fromJson(WeatherContent, Weather.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
     try {
                JSONArray allprovinces=new JSONArray(response);
            for (int i=0;i<allprovinces.length();i++){

                JSONObject provinceObject=allprovinces.getJSONObject(i);
                Province province=new Province();
                province.setProvinceName(provinceObject.getString("name"));
                province.setProvinveCode(provinceObject.getInt("id"));
                province.save();

            }
            return true;
        }catch (JSONException e){
         e.printStackTrace();

     }
        }

        return false;
    }
    public static boolean handleCityResponse(String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCities=new JSONArray(response);
                for (int i=0; i<allCities.length();i++){
                    JSONObject cityObject=allCities.getJSONObject(i);
                    City city=new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCountyResponse(String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCounties=new JSONArray(response);
                for(int i=0; i<allCounties.length();i++){
                    JSONObject countyObject=allCounties.getJSONObject(i);
                    County county=new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return  false;
    }
}
