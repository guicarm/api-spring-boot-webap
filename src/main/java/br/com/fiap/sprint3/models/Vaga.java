package br.com.fiap.sprint3.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;


@Data
@Entity
@Table(name = "TB_VAGAS")
@SequenceGenerator(name = "vagas", sequenceName = "SQ_TB_VAGAS", allocationSize = 1)
public class Vaga {

    @Id
    @GeneratedValue(generator = "vagas", strategy = GenerationType.SEQUENCE)
    @Column(name = "cd_vaga")
    private Long id;

    @Column(name = "cd_estacionamento")
    private Long idEstacionamento;

    @NotBlank(message = "A localização é obrigatória, Exemplo de localização: 'H13'")
    @Column(name = "ds_localizacao")
    private String localizacao;

    @NotBlank(message = "A andar é obrigatório")
    @Column(name = "ds_andar")
    private String andar;

    @Column(name = "ds_disponivel")
    private Boolean disponivel;

}
