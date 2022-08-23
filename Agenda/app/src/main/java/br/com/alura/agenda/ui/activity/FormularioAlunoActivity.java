package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Novo aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private Button botaoSalvar;
    private Aluno aluno;
    private List<Aluno> tamanhoListaAlunos;
    final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //o comando a seguir inicializa um novo layout no evento "onCreate"
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APPBAR);
        inicializacaoDosCampos();
        configuraBotaoSalvar();
        Intent dados = getIntent();
        if (dados.hasExtra("aluno")){
            aluno = (Aluno) dados.getSerializableExtra("aluno");
            campoNome.setText(aluno.getNome());
            campoTelefone.setText(aluno.getTelefone());
            campoEmail.setText(aluno.getEmail());
        } else {
            aluno = new Aluno();
        }

    }

    private void configuraBotaoSalvar() {

        //aqui estou criando um listener para os eventos do botão
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preencheAluno();
                if (aluno.temIdValido()){
                    dao.edita(aluno);
                } else {
                    dao.salva(aluno);
                }
                finish();
                Log.i("Nome Salvo", "" + aluno.getNome());
                Log.i("Email Salvo", "" + aluno.getEmail());
                Log.i("Telefone Salvo", "" + aluno.getTelefone());
                Log.i("ID Salvo", "" + aluno.getId());
                Log.i("ID valido", "" + aluno.temIdValido());

            }
        });
    }

    private void inicializacaoDosCampos() {
        // esta função é responsável por buscar os componentes desta activity
        // e referenciá-los nos atributos da classe, para que depois possam ser consumidos
        // o comando findViewById busca os componentes
        // dentro do layout inicializado no comando "setContentView"
        botaoSalvar = findViewById(R.id.activiti_formulario_aluno_botao_salvar);
        campoNome = findViewById(R.id.activiti_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activiti_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activiti_formulario_aluno_email);
        tamanhoListaAlunos = dao.todos();
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);



    }
}