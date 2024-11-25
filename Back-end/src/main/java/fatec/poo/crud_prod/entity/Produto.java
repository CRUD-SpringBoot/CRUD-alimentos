package fatec.poo.crud_prod.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="produtos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name="nome")
    private String nome;

    @Column(name="categoria")
    private String categoria;

    @Column(name="descricao")
    private String descricao;

    @Column(name="preço")
    private Double preco;

    @Column(name="qtd_estoque")
    private Integer qtd_estoque;

    @Column(name="data_validade")
    @Temporal(TemporalType.DATE)
    private Date data_validade;

    @Column(name="data_fabricacao")
    @Temporal(TemporalType.DATE)
    private Date data_fabricacao;

    @Column(name="peso_por_unidade")
    private  Double peso_por_unidade;

    @Column(name="marca")
    private  String marca;

    @Column(name="pais_de_origem")
    private  String pais_de_origem;

    @Column(name="codigo_de_barras",unique = true)
    private  Integer codigo_de_barras;

}
