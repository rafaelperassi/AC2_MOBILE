package com.example.ac2_prova;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ViaCEPService {

    public interface ViaCEPResponseListener {
        void onCEPSuccess(ViaCEPResponse response);
        void onCEPError(String error);
    }

    public void buscarCEP(String cep, Context context, ViaCEPResponseListener listener) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String logradouro = "Rua Exemplo";
                        String complemento = "Apto 101";
                        String bairro = "Centro";
                        String cidade = "São Paulo";
                        String uf = "SP";

                        ViaCEPResponse viaCEPResponse = new ViaCEPResponse(logradouro, complemento, bairro, cidade, uf);
                        viaCEPResponse.parseJSON(response);

                        listener.onCEPSuccess(viaCEPResponse);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onCEPError(error.getMessage());
                    }
                });

        Volley.newRequestQueue(context).add(request);
    }

}

