package systeam.management.exception;

public class TarefaNaoEncontradaException extends RuntimeException {

    public TarefaNaoEncontradaException(int id) {
        super("Tarefa com id " + id + " nao encontrada!");
    }
}
