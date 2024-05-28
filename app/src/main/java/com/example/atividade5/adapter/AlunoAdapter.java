package com.example.atividade5.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.atividade5.R;
import com.example.atividade5.classes.Aluno;

import java.util.List;

public class AlunoAdapter extends RecyclerView.Adapter<AlunoAdapter.AlunoViewHolder> {
    private List<Aluno> alunos;

    public AlunoAdapter(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    @Override
    public AlunoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_aluno, parent, false);
        return new AlunoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlunoViewHolder holder, int position) {
        Aluno aluno = alunos.get(position);
        holder.bind(aluno);
    }

    @Override
    public int getItemCount() {
        return alunos.size();
    }

    class AlunoViewHolder extends RecyclerView.ViewHolder {
        private TextView tvRa, tvNome, tvEndereco;

        public AlunoViewHolder(View itemView) {
            super(itemView);
            tvRa = itemView.findViewById(R.id.tvRa);
            tvNome = itemView.findViewById(R.id.tvNome);
            tvEndereco = itemView.findViewById(R.id.tvEndereco);
        }

        public void bind(Aluno aluno) {
            tvRa.setText(String.valueOf(aluno.getRa()));
            tvNome.setText(aluno.getNome());
            tvEndereco.setText(aluno.getLogradouro() + ", " + aluno.getBairro() + ", " + aluno.getCidade() + " - " + aluno.getUf());
        }
    }
}

