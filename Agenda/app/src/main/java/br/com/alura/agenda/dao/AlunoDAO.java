package br.com.alura.agenda.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salva(Aluno aluno) {
        aluno.setId(contadorDeIds);
        alunos.add(aluno);
        contadorDeIds++;
    }

    public void edita(Aluno aluno) {
        Aluno alunoEncontrado = null;
        //for para percorrer a lista de alunos do DAO
        for (Aluno a :
                alunos) {
            //caso o ID em edição "a.getId()" seja igual a um ID existente na lista
            if (a.getId() == aluno.getId()) {
                //alunoEncontrado é definido com os dados vindos da edição
                alunoEncontrado = a;
            }
        }
        //faz nova validação para ver se encontrou algum aluno com o mesmo ID
        if (alunoEncontrado != null) {
            //quando encontra, seta a var "posicaoDoAluno" com a posição na lista onde
            //o ID foi encontrato. Esta identificação ocorre pelo "indexOf" da lista
            // O indexOf() método recebe um único parâmetro cuja posição deve ser retornada
            int posicaoDoAluno = alunos.indexOf(alunoEncontrado);
            //"alunos.set" seta dos dados presentes em "aluno" (nova lista), dentro da lista "alunos" do DAO
            // arraylist.set(int index, E element)
            alunos.set(posicaoDoAluno, aluno);
        }
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }
}
