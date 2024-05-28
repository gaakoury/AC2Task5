package com.example.atividade5.services;

import com.example.atividade5.model.Endereco;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ViaCepService {
    @GET("{cep}/json")
    Call<Endereco> getEndereco(@Path("cep") String cep);
}

