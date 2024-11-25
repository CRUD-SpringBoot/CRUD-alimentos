package repository;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import fatec.poo.crud_prod.dto.ProdDTO;
import fatec.poo.crud_prod.entity.Produto;
import fatec.poo.crud_prod.repository.ProdutoRepository;

@DataJpaTest
@ActiveProfiles("test")
class AlunoRepositoryTest {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    EntityManager entityManager; //precisa remover @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Test
    @DisplayName("Should fetch all Morbid Obesity Student Successfully")
    void fetchAllMorbidObesityStudentsSuccessfully() {
       
    }

    @Test
    @DisplayName("Should fetch all Morbid Obesity Student UnSuccessfully")
    void fetchAllMorbidObesityStudentsUnSuccessfully() {
       
    }

    private void createPrducts(){

        //Este Arrange contém 3 aluno com imc de obesidade mórbida

        ProdDTO a1 = new ProdDTO(1, "Monitos 165htz", 800, 10, 200);
        Produto newProd = new Produto(a1);
        this.entityManager.persist(newProd);
        //this.produtoRepository.save(newProd);


        a1 = new ProdDTO(2,"RX6750XT", 2100, 10, 110);
        newProd = new Produto(a1);
        this.entityManager.persist(newProd);
        //this.produtoRepository.save(newProd);

    }

}
