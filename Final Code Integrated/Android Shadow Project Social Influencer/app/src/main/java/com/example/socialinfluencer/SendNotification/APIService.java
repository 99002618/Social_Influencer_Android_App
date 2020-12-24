package com.example.socialinfluencer.SendNotification;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers({"Authorization: key=AAAA4Ubio1Q:APA91bGWkw84b1XX2nnnOKn8MO25U2giLRXXXTUkXidojFluZk_qKXXXlS27oMZZV5goTQdwRtpdmvI1iAPRZZDNKz6c-mpU6nvHZJ-Jg9f1fQ5NdttftqUpqwAkObLEEX26VFDDbXN8",
            "Content-Type:application/json"})

    @POST("fcm/send")
    Call<ResponseBody> sendChatNotification(@Body RequestNotificaton requestNotificaton);
}

