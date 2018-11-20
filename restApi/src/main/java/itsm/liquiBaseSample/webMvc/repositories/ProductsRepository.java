package itsm.liquiBaseSample.webMvc.repositories;

import itsm.liquiBaseSample.domains.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductsRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByStateId(Integer stateId);
}
