package fatec.poo.crud_prod.dto;

import java.util.Date;

public record ProdDTO(Integer Id,
                      String descricao,
                      Double preco,
                      Integer qtd_estoque,
                      Integer codigo_de_barras,
                      String pais_de_origem,
                      String marca,
                      Double peso_por_unidade,
                      Date data_fabricacao,
                      Date data_validade,
                      String nome,
                      String categoria) {
}