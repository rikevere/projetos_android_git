package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;
import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de alunos";
    private ListView listViewDelistaDeAlunos;
    private FloatingActionButton botaoNovoAluno;
    private final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // codigo para chamar uma nova activity (layout de tela)
        setContentView(R.layout.activity_lista_alunos);
        // a função abaixo cria o título no formulário
        setTitle(TITULO_APPBAR);
        inicializacaoDosCampos();
        configuraFabNovoAluno();


    }

    private void configuraFabNovoAluno() {
        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreFormularioModoEditaAluno();
            }
        });
    }

    private void inicializacaoDosCampos() {
        // esta função é responsável por buscar os componentes desta activity
        // e referenciá-los nos atributos da classe, para que depois possam ser consumidos
        // o comando findViewById busca os componentes
        // dentro do layout inicializado no comando "setContentView"
        botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        listViewDelistaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);
    }

    private void abreFormularioModoEditaAluno() {
        //        Exemplo de opção para iniciar uma nova intenção "Intent" de abertura
        //        de um formulário (layout), (Activity)
        //        Intent i = new Intent(this, FormularioAlunoActivity.class);
        //        startActivity(i);
        //        ...código resumido
        startActivity(new Intent(this,
                FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista();
    }

    private void configuraLista() {
        //o comando abaixo adapta uma lista ao componente "listview" fixado no layout
        List<Aluno> alunos = dao.todos();
        configuraAdapter(alunos);
        coonfiguraListenerDeCliquePorItem(alunos);

    }

    private void coonfiguraListenerDeCliquePorItem(List<Aluno> alunos) {
        listViewDelistaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno alunoEscolhido = (Aluno) listViewDelistaDeAlunos.getItemAtPosition(position);
                abreFormularioModoEditaAluno(alunoEscolhido);
            }
        });
    }

    private void abreFormularioModoEditaAluno(Aluno alunoEscolhido) {
        //comando abaixo cria uma nova variável "Intent" que aponta para qual activity eu quero abrir
        Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        //Esta variável conta com um recurso chamado "putExtra", que permite enviar dados para outra activity
        //mas para que funcione o recurso precisa ser serializável (transformado para Bite)
        //de modo que a classe de origem precise ser declarada como "implements Serializable"
        //neste caso a classe "aluno"
        vaiParaFormularioActivity.putExtra("aluno", alunoEscolhido);
        startActivity(vaiParaFormularioActivity);
    }

    private void configuraAdapter(List<Aluno> alunos) {
        listViewDelistaDeAlunos.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                alunos));
    }

    ;
 }

