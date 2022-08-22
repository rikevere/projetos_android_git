package br.com.alura.agenda.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Novo aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private Button botaoSalvar;
    final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //o comando a seguir inicializa um novo layout no evento "onCreate"
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APPBAR);
        inicializacaoDosCampos();
        configuraBotaoSalvar();
    }

    private void configuraBotaoSalvar() {

        //aqui estou criando um listener para os eventos do botão
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aluno alunoCriado = criaAluno();
                salva(alunoCriado);

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
    }

    private void salva(Aluno aluno) {
        dao.salva(aluno);
        finish();
    }

    @NonNull
    private Aluno criaAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();
        Aluno alunoCriado = new Aluno(nome, telefone, email);
        return alunoCriado;
    }
}