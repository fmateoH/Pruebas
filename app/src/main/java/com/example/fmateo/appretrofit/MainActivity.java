package com.example.fmateo.appretrofit;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fmateo.appretrofit.Interfaces.EndPoint;
import com.example.fmateo.appretrofit.Models.RespuestaJson;
import com.example.fmateo.appretrofit.Models.Tokenizar;
import com.example.fmateo.appretrofit.Models.Validate;
import com.example.fmateo.appretrofit.Utilerias.Globales;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tylerjroach.eventsource.EventSource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button btnValidar;
    EditText code, device;
    TextView textResultado;
    Validate validacion;
    RespuestaJson respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);


        setContentView(R.layout.activity_main);


        btnValidar = findViewById(R.id.validar);
        code = findViewById(R.id.validacion_code);
        device = findViewById(R.id.validacion_device);
        textResultado = findViewById(R.id.resultado);

        obtenerToken();



        btnValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar();
            }
        });

    }



    public void validar(){
        respuesta = new RespuestaJson();

        String res = code.getText().toString();
        validacion.setCode(res);
        validacion.setDeviceId(device.getText().toString());


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Globales.URL_PRINCIPAL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        EndPoint restClient = retrofit.create(EndPoint.class);

        Call<RespuestaJson> llamada = restClient.validarGrua(Globales.TOKEN, Globales.API_KEY, "application/json", validacion);

        llamada.enqueue(new Callback<RespuestaJson>() {
            @Override
            public void onResponse(Call<RespuestaJson> call, Response<RespuestaJson> response) {
                switch (response.code()){
                    case 200:
                        System.out.println("entre");
                        RespuestaJson respuesta = response.body();
                        if(respuesta.isSuccess()){
                            textResultado.setText(respuesta.getData().getId());
                        }
                }
            }

            @Override
            public void onFailure(Call<RespuestaJson> call, Throwable t) {
                System.out.println("no entre :(");
            }
        });

    }
    private void obtenerToken(){


        respuesta = new RespuestaJson();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Globales.URL_PRINCIPAL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        EndPoint token = retrofit.create(EndPoint.class);

        Call<Tokenizar> llamada = token.tokenizar(Globales.AUTHORIZATION, Globales.API_KEY, "application/json");

        llamada.enqueue(new Callback<Tokenizar>() {
            @Override
            public void onResponse(Call<Tokenizar> call, Response<Tokenizar> response) {
                switch (response.code()){
                    case 200:
                        System.out.println("entre");
                        Tokenizar respuesta = response.body();

                            textResultado.setText("Token: " +respuesta.getAccess_token().toString());
                        Globales.TOKEN = respuesta.getAccess_token().toString();
//Javier
                }
            }

            @Override
            public void onFailure(Call<Tokenizar> call, Throwable t) {
            	//Comentario commit numero ocho

            }

        });
    }


}
