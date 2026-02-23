package systeam.management.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarefa {

    private int id;
    private String titulo;
    private String descricao;
    private StatusTarefa status;
    private LocalDateTime dataCriacao;
}
