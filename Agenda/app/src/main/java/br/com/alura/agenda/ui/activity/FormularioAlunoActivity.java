package br.com.alura.agenda.ui.activity;

import static br.com.alura.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO_LEVA_DADO;
import static br.com.alura.agenda.ui.activity.ConstantesActivities.TITULO_APPBAR_EDITA_ALUNO;
import static br.com.alura.agenda.ui.activity.ConstantesActivities.TITULO_APPBAR_NOVO_ALUNO;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    /*Atributos de Classe do tipo EditText para serem associados
    * aos EditText do Layout*/
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;


    private Aluno aluno;
    private List<Aluno> tamanhoListaAlunos;
    final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*by Ludwig - O comando a seguir inicializa/determina um novo
         layout no evento "onCreate" para esta classe
        /Ou seja, define qual formulário esta classe vai utilizar*/
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APPBAR_NOVO_ALUNO);
        inicializacaoDosCampos();
        Intent dados = getIntent();
        carregaAluno(dados);
    }

    @Override
    /* by Ludwig - onCreateOptionsMenu é a criação de um menu de
    opções, aquele que fica no cabeçalho do formulário
    ele deve estar associado a um arquivo de layout específico para menus
    neste projeto, seria o "activity_formulalio_aluno_menu"
    esta associação é feita através da
    função "getMenuInflater()" como pode ser visto a seguir.*/
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    /*onOptionsItemSelected é uma função que
    permite identificar a opção selecionada no menu de opções
    ela retorna um "int" relativo a posição selecionada*/
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activiti_formulario_aluno_menu_salvar){
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaAluno(Intent dados) {
        if (dados.hasExtra(CHAVE_ALUNO_LEVA_DADO)) {
            setTitle(TITULO_APPBAR_EDITA_ALUNO);
            aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO_LEVA_DADO);
            /* by Ludwig - Como o EditText da classe foi associado ao
            EditText do XML de layou na função "inicializacaoDosCampos()"
            pelo comando "findViewById()", o componente no layout irá
            receber toda informação que for enviada para o EditText da Classe*/
            campoNome.setText(aluno.getNome());
            campoTelefone.setText(aluno.getTelefone());
            campoEmail.setText(aluno.getEmail());
        } else {
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            aluno = new Aluno();
        }
    }

    private void finalizaFormulario() {
        preencheAluno();
        if (aluno.temIdValido()) {
            dao.edita(aluno);
        } else {
            dao.salva(aluno);
        }
        finish();
    }

    private void inicializacaoDosCampos() {
        /* by Ludwig -  esta função é responsável por buscar
        os componentes desta activity e referenciá-los nos
        atributos da classe, para que depois possam ser consumidos
        o comando findViewById busca os componentes
        dentro do layout inicializado no comando "setContentView"*/
        campoNome = findViewById(R.id.activiti_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activiti_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activiti_formulario_aluno_email);
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();
        /*by Ludwig - Nesta função, primeiro os dados do EditText da classe foram
        * passados para a string e agora são enviados para a classe aluno, sendo na
        * sequência, enviados para o DAO pela função "finalizaFormulario()" */
        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
    }
}