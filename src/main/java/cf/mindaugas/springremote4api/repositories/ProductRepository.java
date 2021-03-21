package cf.mindaugas.springremote4api.repositories;

import cf.mindaugas.springremote4api.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//@Repository
//public class ProductRepository {
//    // db
//    private List<Product> products = new ArrayList<>() {{
//        add(new Product(1, "Snowboard", 100, 9.99, 3.75));
//        add(new Product(2, "Kittens", 999, 19.99, 4.85));
//        add(new Product(3, "Small dogs", 999, 19.99, 4.85));
//        add(new Product(4, "Tesla P100", 999, 19.99, 4.85));
//    }};
//
//    public List<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<Product> products) {
//        this.products = products;
//    }
//}

// @Repository // ... application will not even work if CRUD action used when Repository<T, ID> is used
// public interface ProductRepository extends org.springframework.data.repository.Repository<Product, Long> {}
//
// @Repository // ... adds crud actions (findById, findAll, save)
// public interface ProductRepository extends CrudRepository<Product, Long> {}
//
// @Repository // ... adds paging and sorting capabilities
// public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {}

@Repository // ... adds flush and deleteInBatch capabilities
public interface ProductRepository extends JpaRepository<Product, Long> {
    // List<Product> findByOrderByRatingDesc();
    // List<Product> findByOrderByTitleAsc();
    // List<Product> findByRatingGreaterThan(Double rating);
    //
    // @Query(value = "SELECT p FROM Product p ORDER BY p.title ASC")
    // List<Product> findAllProducts();
}

