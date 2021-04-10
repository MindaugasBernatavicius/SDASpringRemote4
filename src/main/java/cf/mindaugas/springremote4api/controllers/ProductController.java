package cf.mindaugas.springremote4api.controllers;

import cf.mindaugas.springremote4api.models.Product;
import cf.mindaugas.springremote4api.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.data.domain.Sort.Order;

import java.util.ArrayList;
import java.util.List;

// http://localhost/products -> html (MVC endpoint)
// http://localhost/api/products -> json (REST endpoint)
// http://localhost/api/v1/products -> REST endpoint with versioning
//@RestController
//@RequestMapping("/api/v1/products")
//@CrossOrigin(origins = "*", maxAge = 3600)
//class ProductController {
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @GetMapping("") // GET all products
//    public List<Product> getAllProducts() {
//        return productRepository.getProducts();
//    }
//
//    @GetMapping("/{id}") // GET product by id
//    public Product getProductById(@PathVariable Integer id) {
//        return productRepository.getProducts().stream()
//                .filter(product -> product.getId().equals(id))
//                .findFirst().get();
//    }
//
//    @PostMapping("") // POST create new product
//    public ResponseEntity<Void> createProduct(@RequestBody Product product) {
//        System.out.println(product);
//        Integer maxId = 0;
//        for (Product p : productRepository.getProducts())
//            if (p.getId() > maxId)
//                maxId = p.getId();
//
//        product.setId(maxId + 1);
//        return productRepository.getProducts().add(product)
//                ? new ResponseEntity<>(HttpStatus.CREATED)
//                : new ResponseEntity<>(HttpStatus.CONFLICT);
//    }
//
//    @PutMapping("/{id}") // PUT replace existing product
//    public ResponseEntity<Void> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
//        System.out.println(productRepository.getProducts());
//        Product productToUpdate = getProductById(id);
//        productToUpdate.setPrice(product.getPrice());
//        productToUpdate.setCount(product.getCount());
//        productToUpdate.setRating(product.getRating());
//        productToUpdate.setTitle(product.getTitle());
//        System.out.println(productRepository.getProducts());
//        return new ResponseEntity<>(HttpStatus.OK); // 200
//    }
//
//    @DeleteMapping("/{id}") // DELETE product
//    public ResponseEntity<Void> deleteProductById(@PathVariable Integer id) {
//        Product product = getProductById(id);
//        boolean res = productRepository.getProducts().remove(product);
//        return res
//                ? new ResponseEntity<>(HttpStatus.NO_CONTENT) // 204
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
//    }
//}


@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "*", maxAge = 3600)
class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("") // GET all products
    // public Iterable<Product> getAllProducts(@PathVariable(required = false) String sortingOrder) {
    public Iterable<Product> getAllProducts(@RequestParam(required = false) String sortField) {
        // return productRepository.findAll();
        // return productRepository.findByOrderByRatingDesc();
        // return productRepository.findByOrderByTitleAscAndOrderByRatingDesc();
        // return productRepository.findByRatingGreaterThan(3.95);
        // return productRepository.findAllProducts();
        // return productRepository.findAll(Sort.by(Sort.Direction.DESC, "rating"));

        // http://localhost:8080/api/v1/products?sort=title
        return sortField != null
                ? productRepository.findAll(Sort.by(List.of(new Order(Sort.Direction.DESC, sortField))))
                : productRepository.findAll();
    }

    @GetMapping("/{id}") // GET product by id
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("") // POST create new product
    public ResponseEntity<Void> createProduct(@RequestBody Product product) {
        productRepository.save(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("") // PUT replace existing product
    public ResponseEntity<Void> updateProduct(@RequestBody Product product) {
        // System.out.println(product);
        Product productToUpdate = productRepository
                    .findById(product.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setCount(product.getCount());
        productToUpdate.setRating(product.getRating());
        productToUpdate.setTitle(product.getTitle());
        productRepository.save(productToUpdate);
        return new ResponseEntity<>(HttpStatus.OK); // 200
    }

    @DeleteMapping("/{id}") // DELETE product
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        try {
            this.productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            // e.printStackTrace(); // normally, we should not silence the errors!
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
    }
}
