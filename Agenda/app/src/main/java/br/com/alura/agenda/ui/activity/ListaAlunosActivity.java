package br.com.alura.agenda.ui.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;

public class ListaAlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // codigo para chamar uma nova activity (layout de tela)
        setContentView(R.layout.activity_lista_alunos);

        AlunoDAO dao = new AlunoDAO();



        // a função abaixo cria o título no formulário
        setTitle("Lista de alunos");
        List<String> alunos = new ArrayList<>(
                Arrays.asList("Alex", "Fran", "Jose", "Maria", "Ana"));
        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);
        listaDeAlunos.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dao.todos()));
    }
}
