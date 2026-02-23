package systeam.management.service;

import systeam.management.model.StatusTarefa;
import systeam.management.model.Tarefa;
import systeam.management.repository.TarefaRepository;
import systeam.management.exception.TarefaNaoEncontradaException;


import java.sql.SQLException;
import java.util.List;

public class TarefaService {

    private TarefaRepository repository =  new TarefaRepository();

    public void criarTarefa(String titulo, String descricao) throws SQLException {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(titulo);
        tarefa.setDescricao(descricao);
        tarefa.setStatus(StatusTarefa.PENDENTE);
        repository.salvar(tarefa);
    }

    public List<Tarefa> listarTarefas() throws SQLException {
        return repository.listarTodas();
    }

    public void concluirTarefa(int id) throws SQLException {
        Tarefa tarefa = repository.buscarPorId(id)
                .orElseThrow(() -> new TarefaNaoEncontradaException(id));

        tarefa.setStatus(StatusTarefa.CONCLUIDA);
        repository.atualizar(tarefa);
    }

    public void deletarTarefa(int id) throws SQLException {
        repository.buscarPorId(id)
                .orElseThrow(() -> new TarefaNaoEncontradaException(id));
    }
}
