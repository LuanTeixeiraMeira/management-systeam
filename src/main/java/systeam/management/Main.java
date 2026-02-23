package systeam.management;


import systeam.management.exception.TarefaNaoEncontradaException;
import systeam.management.model.Tarefa;
import systeam.management.service.TarefaService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TarefaService service = new TarefaService();
        Scanner sc = new Scanner(System.in);
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n===== GERENCIADOR DE TAREFAS =====");
            System.out.println("1 - Criar tarefa");
            System.out.println("2 - Listar tarefas");
            System.out.println("3 - Concluir tarefa");
            System.out.println("4 - Deletar tarefa");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            try {
                switch (opcao) {
                    case 1 -> {
                        System.out.println("Titulo: ");
                        String titulo = sc.nextLine();
                        System.out.println("Descricao: ");
                        String descricao = sc.nextLine();
                        service.criarTarefa(titulo, descricao);
                        System.out.println("Tarefa criada com sucesso!");
                }
                    case 2 -> {
                        List<Tarefa> tarefas = service.listarTarefas();
                        if (tarefas.isEmpty()) {
                            System.out.println("Nenhuma tarefa encontrada.");
                        } else {
                            tarefas.forEach(t -> System.out.println(
                                    "\n[" + t.getId() + "] " + t.getTitulo() +
                                            "\n    Descrição: " + t.getDescricao() +
                                            "\n    Status: " + t.getStatus() +
                                            "\n    Criada em: " + t.getDataCriacao()
                            ));
                        }
                    }
                    case 3 -> {
                        System.out.print("ID da tarefa para concluir: ");
                        int id = sc.nextInt();
                        service.concluirTarefa(id);
                        System.out.println(" Tarefa concluída!");
                    }
                    case 4 -> {
                        System.out.print("ID da tarefa para deletar: ");
                        int id = sc.nextInt();
                        service.deletarTarefa(id);
                        System.out.println(" Tarefa deletada!");
                    }
                    case 0 -> System.out.println("Até logo!");
                    default -> System.out.println("Opção inválida!");
                }
            } catch (TarefaNaoEncontradaException e) {
                System.out.println(" Erro: " + e.getMessage());
            } catch (SQLException e) {
                System.out.println(" Erro no banco de dados: " + e.getMessage());
            }
        }

        sc.close();
    }
}