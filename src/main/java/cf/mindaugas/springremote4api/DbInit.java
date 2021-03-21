package cf.mindaugas.springremote4api;

import cf.mindaugas.springremote4api.models.Product;
import cf.mindaugas.springremote4api.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DbInit implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) {
        // // Delete all in the beginning
        // this.productRepository.deleteAll();
        //
        // // Create initial dummy products
        // Product p1 = new Product(1L, "Snowboard", 100, 9.99, 3.75);
        // Product p2 = new Product(2L, "Kittens", 999, 19.99, 4.85);
        // Product p3 = new Product(3L, "Small dogs", 999, 19.99, 4.85);
        // Product p4 = new Product(4L, "Tesla P100", 999, 19.99, 4.85);
        //
        // // Save to db
        // this.productRepository.saveAll(Arrays.asList(p1,p2,p3,p4));
    }
}
