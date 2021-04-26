package br.com.stock.services;

import br.com.stock.entities.Collection;
import br.com.stock.entities.Product;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class ProductVariantServiceTest {

    @InjectMocks
    private ProductVariantService productVariantService;

    @BeforeClass
    public static void setup(){

        System.out.println("ProductVariantServiceTest class");

    }

    @Before
    public void beforeEachClass(){

        System.out.println("Starting the tests");

    }

    @Test
    public void testProductVariant() {

        Product product = new Product("Product One", 123.4, "Test for Product One", 2, new Collection(), new ArrayList<>(), null);

        productVariantService = new ProductVariantService();

        Assert.assertFalse(productVariantService.productVariant(product));

    }

    @AfterClass
    public static void end(){

        System.out.println("Finishing the tests");

    }

}
