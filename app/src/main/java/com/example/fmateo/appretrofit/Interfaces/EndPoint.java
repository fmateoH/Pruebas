package com.example.fmateo.appretrofit.Interfaces;

import com.example.fmateo.appretrofit.Models.RespuestaJson;
import com.example.fmateo.appretrofit.Models.Tokenizar;
import com.example.fmateo.appretrofit.Models.Validate;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface EndPoint {
    @POST("api/v1/cranes/validate/")
    Call<RespuestaJson> validarGrua(@Header("Authorization") String token, @Header("x-api-key") String api_key,@Header("Content-Type") String content, @Body Validate grua);

    @POST("oauth/token?grant_type=client_credentials")
    Call<Tokenizar> tokenizar (@Header("Authorization") String token, @Header("x-api-key") String api_key,@Header("Content-Type")String content);

}
