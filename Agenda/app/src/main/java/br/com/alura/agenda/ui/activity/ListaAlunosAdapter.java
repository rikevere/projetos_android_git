package br.com.alura.agenda.ui.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.R;
import br.com.alura.agenda.model.Aluno;

public class ListaAlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos = new ArrayList<>();
    private Context context;

    public ListaAlunosAdapter (Context context){
        this.context = context;
    }


        @Override
    public int getCount() {
        /* by Ludwig - Aqui precisamos adicionar o tamanho
         * da lista que estamos associando. Por isso criamos uma nova
         * lista temporária, que terá o mesmo tamannho da lista
         * manipulada no DAO*/
        return alunos.size();
    }

    @Override
    public Aluno getItem(int position) {
        /*Neste método precisamos devolver de qual posição
         * queremos a informação, por isso implementamos
         * o código a seguir.*/
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        /* Este campo devolve o ID do elemento
         * que estamos selecionando */
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*Este é o método mais importante, onde estaremos de fato definindo
         * qual o novo layout que queremos para exibir em cada linha
         * e faremos isso usando o método de inflar, como ocorreu no menu
         * mas específico para ListView */
        View viewCriada = LayoutInflater
                .from(context)
                .inflate(R.layout.item_aluno, parent, false);
        return viewCriada;
    }

    public void clear() {
        alunos.clear();
    }

    public void addAll(List<Aluno> alunos) {
        this.alunos.addAll(alunos);
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
    }
}
