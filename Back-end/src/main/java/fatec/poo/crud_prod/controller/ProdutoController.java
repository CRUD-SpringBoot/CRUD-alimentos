package fatec.poo.crud_prod.controller;

import fatec.poo.crud_prod.entity.Produto;
import fatec.poo.crud_prod.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alimentos")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping
    public String showIndex() {
        return "index";
    }


    @GetMapping("/listall")
    public ResponseEntity<List<Produto>> getAllProducts(){
        return ResponseEntity.ok(produtoService.getAllProductsService());
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Optional<Produto>> getProduct(@PathVariable Integer id){

        if ((produtoService.getProductService(id)).isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(produtoService.getProductService(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Produto> addProduct(@RequestBody Produto produto){
        return new ResponseEntity<>(produtoService.addProductService(produto), HttpStatus.CREATED);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id){
        produtoService.deleteProductByIdService(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Produto> updateProduct(@RequestBody Produto produto){
        return ResponseEntity.ok(produtoService.updateProductService(produto));
    }
}
