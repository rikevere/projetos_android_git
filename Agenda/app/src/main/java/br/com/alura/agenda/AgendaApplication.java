package br.com.alura.agenda;

import android.app.Application;

import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunosDeTeste();


    }

    private void criaAlunosDeTeste() {
        AlunoDAO dao = new AlunoDAO();
        dao.salva(new Aluno("Ricardo Ludwig", "46 99911 1465", "rikevere@gmail.com"));
        dao.salva(new Aluno("Adrieli Ludwig", "46 99911 2697", "drika_dd@gmail.com"));
    }
}
