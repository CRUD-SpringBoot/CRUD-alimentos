package service;

import fatec.poo.crud_prod.entity.Produto;
import fatec.poo.crud_prod.repository.ProdutoRepository;
import fatec.poo.crud_prod.service.ProdutoService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

class ProdutoServiceTest {
    @Mock
    ProdutoRepository produtoRepository;

    @Autowired
    @InjectMocks
    ProdutoService produtoService;

    @BeforeEach
    void initializeMocks(){
        MockitoAnnotations.openMocks(this);
    }

    //Testando o métodos que traz todos os dados do banco de dados

    @Test
    @DisplayName("Should bring all the data successfully")
    void showAllDatabaseData() throws Exception{

        // Arrange
        Produto p1 = new Produto(1,"Teclado","Periféricos","Teclado mecânico",12.0, 100, new Date(), new Date(), 1.2, "DELL", "China", 123456789);
        Produto p2 = new Produto(2,"Teclado","Periféricos","Teclado RGB Gamer",15.0, 80, new Date(), new Date(), 1.4, "DELL", "Vietna", 123456700);

        // Configurando o mock para retornar os produtos simulados
        List<Produto> prods = new ArrayList<>();
        prods.add(p1);
        prods.add(p2);

        when(produtoRepository.findAll()).thenReturn(prods);

        // Act
        List<Produto> produtos = produtoService.getAllProductsService();

        // Assert
        assertNotNull(produtos); 
        assertEquals(2, produtos.size()); 
        assertEquals("Teclado", produtos.get(0).getDescricao()); 
        assertEquals("Mouse", produtos.get(1).getDescricao()); 
       
    }

    @Test
    @DisplayName("Should bring all the data unsuccessfully")
    void showNoDatabaseData() throws Exception{

        // Arrange
        Produto p1 = new Produto(1,"Teclado","Periféricos","Teclado mecânico",12.0, 100, new Date(), new Date(), 1.2, "DELL", "China", 123456789);
        Produto p2 = new Produto(2,"Teclado","Periféricos","Teclado RGB Gamer",15.0, 80, new Date(), new Date(), 1.4, "DELL", "Vietna", 123456700);

        // Configurando o mock para retornar os produtos simulados
        List<Produto> prods = new ArrayList<>();
        prods.add(p1);
        prods.add(p2);

        when(produtoRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<Produto> produtos = produtoService.getAllProductsService();

        // Assert
        assertEquals(0, produtos.size()); 
        
    }

    @Test
    @DisplayName("Should find a specific data successfully")
    void shouldFindDataById() throws Exception{
        
        int id = 1;

        // Arrange
        Produto p1 = new Produto(1,"Teclado","Periféricos","Teclado mecânico",12.0, 100, new Date(), new Date(), 1.2, "DELL", "China", 123456789);
        
        when(produtoRepository.findById(id)).thenReturn(Optional.of(p1));
        when(produtoRepository.save(p1)).thenReturn(p1);

        // Act
        Optional<Produto> produto = produtoService.getProductService(id);

        // Assert
        assertTrue(produto.isPresent()); 
        assertEquals("Teclado", produto.get().getDescricao()); 
 
    }

    @Test
    @DisplayName("Should find a specific data unsuccessfully")
    void shouldNotFindDataById() throws Exception{
        
        int id = 2;

        // Arrange
        Produto p1 = new Produto(1,"Teclado","Periféricos","Teclado mecânico",12.0, 100, new Date(), new Date(), 1.2, "DELL", "China", 123456789);
        
        when(produtoRepository.findById(id)).thenReturn(null);
        when(produtoRepository.save(p1)).thenReturn(p1);

        // Act
        Optional<Produto> produto = produtoService.getProductService(id);

        // Assert
        assertEquals(null, produto); 
    }

    @Test
    @DisplayName("Should add a new product into the database successfully")
    void shouldAddNewData() throws Exception{
        
        // Arrange
        Produto p1 = new Produto(1,"Teclado","Periféricos","Teclado mecânico",12.0, 100, new Date(), new Date(), 1.2, "DELL", "China", 123456789);
        
        when(produtoRepository.save(p1)).thenReturn(p1);

        // Act
        Produto produto = produtoService.addProductService(p1);

        // Assert
        assertEquals(p1, produto); 
    }

    @Test
    @DisplayName("Should add a new product into the database unsuccessfully")
    void shouldNotAddNewData() throws Exception{
        
        // Arrange
        Produto p1 = new Produto(1,"Teclado","Periféricos","Teclado mecânico",12.0, 100, new Date(), new Date(), 1.2, "DELL", "China", 123456789);
        
        when(produtoRepository.save(p1)).thenReturn(null);

        // Act
        Produto produto = produtoService.addProductService(p1);

        // Assert
        assertEquals(null, produto); 
    }

    @Test
    @DisplayName("Should delete data by id successfully")
    void shouldDeleteDataById() throws Exception{

        // Arrange
        int id = 1;

        Produto p1 = new Produto(1,"Teclado","Periféricos","Teclado mecânico",12.0, 100, new Date(), new Date(), 1.2, "DELL", "China", 123456789);
        

        when(produtoRepository.findById(id)).thenReturn(null);

        // Act
        produtoService.addProductService(p1);
        produtoService.deleteProductByIdService(id);
        Optional<Produto> produto = produtoService.getProductService(id);

        // Assert
        assertEquals(null, produto); 
    }

    @Test
    @DisplayName("Should delete data by id unsuccessfully")
    void shouldNotDeleteDataById() throws Exception{

        // Arrange
        int id = 2;

        Produto p1 = new Produto(1,"Teclado","Periféricos","Teclado mecânico",12.0, 100, new Date(), new Date(), 1.2, "DELL", "China", 123456789);
        

        when(produtoRepository.findById(id)).thenReturn(null);

        // Act
        produtoService.addProductService(p1);
        produtoService.deleteProductByIdService(id);
        Optional<Produto> produto = produtoService.getProductService(id);

        // Assert
        assertEquals(null, produto); 
    }

    @Test
    @DisplayName("Should update the data successfully")
    void shouldUpdateData() throws Exception{

        // Arrange
        int id = 1;

        Produto p1 = new Produto(1,"Teclado","Periféricos","Teclado mecânico",12.0, 100, new Date(), new Date(), 1.2, "DELL", "China", 123456789);
        Produto p2 = new Produto(2,"Teclado","Periféricos","Teclado RGB Gamer",15.0, 80, new Date(), new Date(), 1.4, "DELL", "Vietna", 123456700);
        

        when(produtoRepository.findById(id)).thenReturn(Optional.of(p1));
        when(produtoRepository.save(p2)).thenReturn(p2);

        // Act
        Produto produto = produtoService.updateProductService(p2);

        // Assert
        assertEquals(p2, produto); 
    }

    @Test
    @DisplayName("Should update the data unsuccessfully")
    void shouldNotUpdateData() throws Exception{

        // Arrange
        int id = 2;

        Produto p1 = new Produto(1,"Teclado","Periféricos","Teclado mecânico",12.0, 100, new Date(), new Date(), 1.2, "DELL", "China", 123456789);
        Produto p2 = new Produto(2,"Teclado","Periféricos","Teclado RGB Gamer",15.0, 80, new Date(), new Date(), 1.4, "DELL", "Vietna", 123456700);
        

        when(produtoRepository.findById(id)).thenReturn(Optional.of(p1));
        when(produtoRepository.save(p2)).thenReturn(p2);

        // Act
        Exception exception = null;

        try {
            Produto produto = produtoService.updateProductService(p2);
        } catch (Exception ex) {
            exception = ex;
        }

        // Assert
        assertNotNull(exception); 
    }
}