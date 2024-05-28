package com.example.atividade5.services;

import com.example.atividade5.classes.Aluno;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MockApiService {
    @GET("aluno")
    Call<List<Aluno>> getAlunos();

    @POST("aluno")
    Call<Aluno> criarAluno(@Body Aluno aluno);
}
