package fatec.poo.crud_prod.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="alimentos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column
    private String nome;

    @Column
    private String categoria;

    @Column
    private Double preco;

    @Column
    private Integer qtd_estoque;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate data_validade;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate data_fabricacao;

    @Column
    private  Double peso_por_unidade;

    @Column
    private  String marca;

    @Column
    private  String pais_origem;

    @Column
    private  Integer codigo_barras;

}
