package br.com.alura.agenda.ui.activity;

import static br.com.alura.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO_LEVA_DADO;
import static br.com.alura.agenda.ui.activity.ConstantesActivities.TITULO_APPBAR_LISTA_ALUNO;


import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    private ListView listViewDelistaDeAlunos;
    private FloatingActionButton botaoNovoAluno;
    private final AlunoDAO dao = new AlunoDAO();
    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // codigo para chamar uma nova activity (layout de tela)
        setContentView(R.layout.activity_lista_alunos);
        // a função abaixo cria o título no formulário
        setTitle(TITULO_APPBAR_LISTA_ALUNO);
        inicializacaoDosCampos();
        configuraFabNovoAluno();
        configuraLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        /*menu.add("Remover"); ----  Este comando adiciona um context denominado no Listview
        menu.add("Item 1 - Menu");
        menu.add("Item 2 - Menu");
        menu.add("Item 3 - Menu");*/

        /* o comando abaixo serve para incorporar um arquivo de layout de menu
        para aumentar "inflar" as opções na lista de menu conforme arquivo
        neste caso, do arquivo "activity_lista_alunos_menu.xml"*/
        getMenuInflater()
                .inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activiti_lista_aluno_remover) {
            AdapterView.AdapterContextMenuInfo menuInfo =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
            removeAlunoDaLista(alunoEscolhido);
        }
        return super.onContextItemSelected(item);
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
        atualizaAlunos();
    }

    private void atualizaAlunos() {
        adapter.clear();
        adapter.addAll(dao.todos());
    }

    private void configuraLista() {
        configuraAdapter();
        configuraListenerDeCliqueEditaItemLista();
//        configuraListenerdeCliqueExcluiItemLista();
        /*o comando abaixo espera que se envie uma view
        como argumento dentro do nosso layout
        para que se possa utilizar a funcionalidade de contexto
        como o botão esquerdo do mouse*/
        registerForContextMenu(listViewDelistaDeAlunos);
    }

// ------xxxx  CÓDIGO PARA O CONTROLE DE CLIQUE LONGO NA LISTVIEW xxxxxx------
//    private void configuraListenerdeCliqueExcluiItemLista() {
//        listViewDelistaDeAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Aluno alunoEscolhidoParaExcluir = (Aluno) listViewDelistaDeAlunos.getItemAtPosition(position);
//                removeAlunoDaLista(alunoEscolhidoParaExcluir);
    /*se o return for true, o envento do clique longo
    executa o que está no Listner e segue*/
//                return false;
//            }
//        });
//    }

    private void removeAlunoDaLista(Aluno alunoEscolhidoParaExcluir) {
        dao.remove(alunoEscolhidoParaExcluir);
        //função para remover itens do adapter
        adapter.remove(alunoEscolhidoParaExcluir);
    }

    private void configuraListenerDeCliqueEditaItemLista() {
        listViewDelistaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno alunoEscolhidoParaEditar = (Aluno) listViewDelistaDeAlunos.getItemAtPosition(position);
                abreFormularioModoEditaAluno(alunoEscolhidoParaEditar);
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
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO_LEVA_DADO, alunoEscolhido);
        startActivity(vaiParaFormularioActivity);
    }

    private void configuraAdapter() {
        //o comando abaixo adapta uma lista ao componente "listview" fixado no layout
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1);
        listViewDelistaDeAlunos.setAdapter(adapter);
    }

}

