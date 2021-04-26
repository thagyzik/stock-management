package br.com.stock.services;

import br.com.stock.entities.Product;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProductCollectionServiceTest {

    @InjectMocks
    private ProductCollectionService productCollectionService;

    @BeforeClass
    public static void setup(){

        System.out.println("ProductCollectionServiceTest class");

    }

    @Before
    public void beforeEachClass(){

        System.out.println("Starting the tests");

    }

    @Test(expected = Exception.class)
    public void testProductCollection() throws Exception {

        productCollectionService.productCollection(new Product(), new ControlCollectionService());

    }

    @AfterClass
    public static void end(){

        System.out.println("Finishing the tests");

    }

}
