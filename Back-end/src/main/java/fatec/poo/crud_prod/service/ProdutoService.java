package fatec.poo.crud_prod.service;

import fatec.poo.crud_prod.entity.Produto;
import fatec.poo.crud_prod.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produto_repository;

    // Retorna a lista com todos os produtos
    public List<Produto> getAllProductsService() {
        return produto_repository.findAll();
    }

    // Procura um produto pelo ID
    public Optional<Produto> getProductService(Integer id) {
        Optional<Produto> produto = produto_repository.findById(id);

        if (produto.isEmpty()) {
            throw new EntityNotFoundException("Produto não encontrado com o ID: " + id);
        }

        return produto;
    }

    // Adiciona um novo produto
    public Produto addProductService(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("O produto fornecido é nulo.");
        }

        return produto_repository.save(produto);
    }

    // Deleta um produto pelo ID
    public void deleteProductByIdService(Integer id) {
        if (!produto_repository.existsById(id)) {
            throw new EntityNotFoundException("Não é possível deletar. Produto não encontrado com o ID: " + id);
        }

        produto_repository.deleteById(id);
    }

    // Atualiza um produto existente
    public Produto updateProductService(Produto produto) {

        return produto_repository.findById(produto.getId()).map(existingProduct -> {
            existingProduct.setNome(produto.getNome());
            existingProduct.setCategoria(produto.getCategoria());
            existingProduct.setDescricao(produto.getDescricao());
            existingProduct.setPreco(produto.getPreco());
            existingProduct.setQtd_estoque(produto.getQtd_estoque());
            existingProduct.setData_validade(produto.getData_validade());
            existingProduct.setData_fabricacao(produto.getData_fabricacao());
            existingProduct.setPeso_por_unidade(produto.getPeso_por_unidade());
            existingProduct.setMarca(produto.getMarca());
            existingProduct.setPais_origem(produto.getPais_origem());
            existingProduct.setCodigo_barras(produto.getCodigo_barras());
            return produto_repository.save(existingProduct);
        }).orElseThrow(() -> new EntityNotFoundException("Não é possível atualizar. Produto não encontrado com o ID: " + produto.getId()));
    }
}
