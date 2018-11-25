package itsm.liquiBaseSample.webMvc.services.product;

import itsm.liquiBaseSample.domains.Product;
import itsm.liquiBaseSample.domains.State;
import itsm.liquiBaseSample.webMvc.repositories.ProductsRepository;
import itsm.liquiBaseSample.webMvc.runner.WebMain;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebMain.class)
@DataJpaTest
public class ProductCrudServiceImplTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductsRepository productsRepository;

    private Product productForTest;

    private State stateForTest;


    @Before
    public void initProductServiceTest() {
        stateForTest = new State("12", "Washington");
        entityManager.persist(stateForTest);
        productForTest = new Product("mesim", stateForTest);
        entityManager.persist(productForTest);
        entityManager.flush();
    }

    @Test
    public void findByStateIdTest() {
        assertThat(productsRepository.findAllByStateId(stateForTest.getId())).isNotEmpty();
    }

    @Test
    public void saveProductTest() {
        State state = entityManager.find(State.class, stateForTest.getId());
        Product product = new Product("Prodnizolon", state);
        productsRepository.save(product);
    }


}