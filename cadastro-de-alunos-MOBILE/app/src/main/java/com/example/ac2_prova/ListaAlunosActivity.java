package com.example.ac2_prova;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ListaAlunosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewAlunos;
    private AlunoAdapter alunoAdapter;
    private ArrayList<Aluno> alunosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        recyclerViewAlunos = findViewById(R.id.recyclerViewAlunos);
        recyclerViewAlunos.setLayoutManager(new LinearLayoutManager(this));

        alunosList = new ArrayList<>();
        alunoAdapter = new AlunoAdapter(alunosList);
        recyclerViewAlunos.setAdapter(alunoAdapter);

        carregarAlunos();
    }

    private void carregarAlunos() {
        MockAPIUtil.getAlunos(new MockAPIUtil.OnGetAlunosListener() {
            @Override
            public void onGetAlunos(ArrayList<Aluno> alunos) {
                alunosList.clear();
                alunosList.addAll(alunos);
                alunoAdapter.notifyDataSetChanged();
            }
        });
    }
}
