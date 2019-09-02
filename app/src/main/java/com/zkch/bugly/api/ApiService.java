package com.zkch.bugly.api;

import com.zkch.bugly.bean.Joker;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

   String BASE_URL="https://www.apiopen.top/";

   @GET("satinApi")
   Observable<Joker> getJokerList(@Query("type")String type,@Query("page")String page);

}
