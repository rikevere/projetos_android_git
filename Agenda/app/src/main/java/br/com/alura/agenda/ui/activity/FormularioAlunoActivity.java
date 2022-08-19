package br.com.alura.agenda.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle("Novo Aluno");

        AlunoDAO dao = new AlunoDAO();

        // "final" utilizado para definir como uma variável local

        final EditText campoNome = findViewById(R.id.activiti_formulario_aluno_nome);
        final EditText campoTelefone = findViewById(R.id.activiti_formulario_aluno_telefone);
        final EditText campoEmail = findViewById(R.id.activiti_formulario_aluno_email);



        // aqui eu estou instansiando uma classe que busca um componente em uma view re referenciando no "botaoSalvar"
        Button botaoSalvar = findViewById(R.id.activiti_formulario_aluno_botao_salvar);
        //aqui estou criando uma função para adiconar os eventos do botão
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = campoNome.getText().toString();
                String telefone = campoTelefone.getText().toString();
                String email = campoEmail.getText().toString();

                Aluno alunoCriado = new Aluno(nome, telefone, email);
                dao.salva(alunoCriado);
                // o código a seguir inicializa uma activity (layout)
//                startActivity(new Intent(FormularioAlunoActivity.this,
//                        ListaAlunosActivity.class));

                finish();

            }
        });

    }
}