package com.example.atividade5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.atividade5.classes.Aluno;
import com.example.atividade5.model.Endereco;
import com.example.atividade5.services.MockApiService;
import com.example.atividade5.services.ViaCepService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Cadastro extends AppCompatActivity {

    private EditText etRa, etNome, etCep, etLogradouro, etComplemento, etBairro, etCidade, etUf;
    private Button btnSalvar, btnBuscarCep;

    private ViaCepService viaCepService;
    private MockApiService mockApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        etRa = findViewById(R.id.etRa);
        etNome = findViewById(R.id.etNome);
        etCep = findViewById(R.id.etCep);
        etLogradouro = findViewById(R.id.etLogradouro);
        etComplemento = findViewById(R.id.etComplemento);
        etBairro = findViewById(R.id.etBairro);
        etCidade = findViewById(R.id.etCidade);
        etUf = findViewById(R.id.etUf);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnBuscarCep = findViewById(R.id.btnBuscarCep);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://viacep.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        viaCepService = retrofit.create(ViaCepService.class);

        Retrofit mockApiRetrofit = new Retrofit.Builder()
                .baseUrl("https://66551efe3c1d3b6029384cb7.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mockApiService = mockApiRetrofit.create(MockApiService.class);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aluno aluno = new Aluno(Integer.parseInt(etRa.getText().toString()),etNome.getText().toString(),etCep.getText().toString(),etLogradouro.getText().toString(),etComplemento.getText().toString(),etBairro.getText().toString(),etCidade.getText().toString(),etUf.getText().toString());

                mockApiService.criarAluno(aluno).enqueue(new Callback<Aluno>() {
                    @Override
                    public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(Cadastro.this, "Aluno salvo com sucesso", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Aluno> call, Throwable t) {
                        Toast.makeText(Cadastro.this, "Erro ao salvar aluno", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnBuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cep = etCep.getText().toString();
                if (!cep.isEmpty()) {
                    viaCepService.getEndereco(cep).enqueue(new Callback<Endereco>() {
                        @Override
                        public void onResponse(Call<Endereco> call, Response<Endereco> response) {
                            if (response.isSuccessful()) {
                                Endereco endereco = response.body();
                                etLogradouro.setText(endereco.getLogradouro());
                                etComplemento.setText(endereco.getComplemento());
                                etBairro.setText(endereco.getBairro());
                                etCidade.setText(endereco.getLocalidade());
                                etUf.setText(endereco.getUf());
                            }
                        }

                        @Override
                        public void onFailure(Call<Endereco> call, Throwable t) {
                            Toast.makeText(Cadastro.this, "Erro ao buscar endereço", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}