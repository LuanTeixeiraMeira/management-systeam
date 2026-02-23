package systeam.management.repository;

import systeam.management.infra.ConexaoBanco;
import systeam.management.model.StatusTarefa;
import systeam.management.model.Tarefa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TarefaRepository {

    public void salvar(Tarefa tarefas) throws SQLException {
        String sql = "INSERT INTO tarefas (titulo, descricao, status) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoBanco.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tarefas.getTitulo());
            stmt.setString(2, tarefas.getDescricao());
            stmt.setString(3, tarefas.getStatus().name());
            stmt.executeUpdate();
        }

    }

    public List<Tarefa> listarTodas() throws SQLException {
        String sql = "SELECT * FROM tarefas";
        List<Tarefa> tarefas = new ArrayList<>();

        try (Connection conn = ConexaoBanco.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setId(rs.getInt("id"));
                tarefa.setTitulo(rs.getString("titulo"));
                tarefa.setDescricao(rs.getString("descricao"));
                tarefa.setStatus(StatusTarefa.valueOf(rs.getString("status")));
                tarefa.setDataCriacao(rs.getTimestamp("data_criacao").toLocalDateTime());
                tarefas.add(tarefa);
            }
        }
        return tarefas;
    }

    public Optional<Tarefa> buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM tarefas WHERE id = ?";

        try (Connection conn = ConexaoBanco.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setId(rs.getInt("id"));
                tarefa.setTitulo(rs.getString("titulo"));
                tarefa.setDescricao(rs.getString("descricao"));
                tarefa.setStatus(StatusTarefa.valueOf(rs.getString("status")));
                tarefa.setDataCriacao(rs.getTimestamp("data_criacao").toLocalDateTime());
                return Optional.of(tarefa);
            }
        }
        return Optional.empty();
    }
    public void atualizar(Tarefa tarefa) throws SQLException {
        String sql = "UPDATE tarefas SET titulo = ?, descricao = ?, status = ? WHERE id = ?";

        try (Connection conn = ConexaoBanco.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getDescricao());
            stmt.setString(3, tarefa.getStatus().name());
            stmt.setInt(4, tarefa.getId());
            stmt.executeUpdate();

        }
    }
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM tarefas WHERE id = ?";

        try (Connection conn = ConexaoBanco.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
