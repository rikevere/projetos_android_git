package br.com.alura.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.R;
import br.com.alura.agenda.model.Aluno;

public class ListaAlunosAdapter extends BaseAdapter {
    /*O extends acima esta determinando que essa classe
    * abstrata estará estendendo os recursos do "BaseAdapter"
    * padrão do Android. Ou seja, com base no padrão,
    * estamos definindo a seguir os recursos que queremos, lembrando que os métodos
    * são:
     * getCount() -> representa a quantidade de elementos do adapter;
     * getItem() -> Retorna o elemento pela posição;
     * getItemId() -> retornar o id do elemento pela posição;
     * getView() -> cria a view para cada elemento.
     */


    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;
    /*A seguir vamos criar um construtor para que em cada local do sistema
    * que desejarmos utilizar o adapter, nos seja enviado este contexto.
    * Neste caso o Contexto "ListaAlunosAdapter" que é nosso adapter
    * personalizado*/
    public ListaAlunosAdapter (Context context){
        this.context = context;
    }


    @Override
    /* getCount() -> representa a quantidade de elementos do adapter;*/
    public int getCount() {
        /* by Ludwig - Aqui precisamos adicionar o tamanho
         * da lista que estamos associando. Por isso criamos uma nova
         * lista temporária, que terá o mesmo tamannho da lista
         * manipulada no DAO*/
        return alunos.size();
    }

    @Override
    /*getItem() -> Retorna o elemento pela posição;*/
    public Aluno getItem(int position) {
        /*Neste método precisamos devolver de qual posição
         * queremos a informação, por isso implementamos
         * o código a seguir.*/
        return alunos.get(position);
    }

    @Override
    /*getItemId() -> retornar o id do elemento pela posição;*/
    public long getItemId(int position) {
        /* Este campo devolve o ID do elemento
         * que estamos selecionando */
        return alunos.get(position).getId();
    }

    @Override
    /*getView() -> cria a view para cada elemento.*/
    public View getView(int position, View convertView, ViewGroup parent) {
        /*Este é o método mais importante, onde estaremos de fato definindo
         * qual o novo layout que queremos para exibir em cada linha
         * e faremos isso usando o método de inflar, como ocorreu no menu
         * mas específico para ListView
         * ou seja, vamos "inflar" o novo layout personalizado na nova View Criada
         * Lembre que criamos uma referencia ao atributo "context" a Actitivity
         * "ListaAlunosActivity" pois é nesta que iremos "inflar/aplicar" o novo
         * layout personalizado*/
        View viewCriada = criaView(parent);
        /*Abaixo criamos um novo atributo "alunoDevolvid" para que nos
        * retorne a posição do ListView que foi selecionada pelo usuário*/
        Aluno alunoDevolvido = alunos.get(position);
        vinculaDadosAlunoDevolvidoCampos(viewCriada, alunoDevolvido);
        return viewCriada;
    }

    private void vinculaDadosAlunoDevolvidoCampos(View viewCriada, Aluno alunoDevolvido) {
        TextView nome = viewCriada.findViewById(R.id.item_aluno_nome);
        /*Tendo a posição em "alunoDevolvido", determinamos que o TextView
        * seja preenchido com o valor correspondente aquela referência
        * pois está extendendo o get da classe Aluno*/
        nome.setText(alunoDevolvido.getNome());
        /*Agora repetimos o mesmo para o campo Telefone*/
        TextView contato = viewCriada.findViewById(R.id.item_aluno_contato);
        contato.setText("Telefone: " + alunoDevolvido.getTelefone()
                        + "\n" + "E-mail: " + alunoDevolvido.getEmail());
    }

    private View criaView(ViewGroup parent) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_aluno, parent, false);
    }


    public void remove(Aluno aluno) {
        alunos.remove(aluno);
        /*o comando abaixo realiza uma atulização no
        * dataSet. Isso é necessário porque na View
        * personalizada estamos trabalhando com os dados
        * do dataSet*/
        notifyDataSetChanged();
    }

    public void atualiza(List<Aluno> alunos){
        this.alunos.clear();
        this.alunos.addAll(alunos);
        /*o comando abaixo realiza uma atulização no
         * dataSet*/
        notifyDataSetChanged();
    }
}
