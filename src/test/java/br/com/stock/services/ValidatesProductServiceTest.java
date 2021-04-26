package br.com.stock.services;

import br.com.stock.entities.Collection;
import br.com.stock.entities.Product;
import br.com.stock.entities.Variant;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ValidatesProductServiceTest {

    @InjectMocks
    private ValidatesProductService validatesProductService;

    @BeforeClass
    public static void setup(){

        System.out.println("ValidatesProductServiceTest class");

    }

    @Before
    public void beforeEachClass(){

        System.out.println("Starting the tests");

    }

    @Test
    public void testValidatesProduct() throws Exception {

        List<String> keywordListTest = new ArrayList<>();
        keywordListTest.add("Keyword one");
        keywordListTest.add("Keyword two");
        keywordListTest.add("Keyword three");

        List<String> imagesListTest = new ArrayList<>();
        imagesListTest.add("Image one");

        Product product = new Product("Product One", 123.4, "Test for product One", 2, new Collection("Collection One", "My collection test", keywordListTest), imagesListTest, new Variant("Variant test", "23", "Gender test", "Long", "Sport", "Material"));

        validatesProductService = new ValidatesProductService();

        Assert.assertTrue("Missing values to insert the Product", validatesProductService.validatesProduct(product, new ControlProductService()));

    }

    @Test(expected = Exception.class)
    public void testeValidatesProductError() throws Exception {

        Product productWithoutInformation = new Product("", 123.4, "", 2, new Collection(), new ArrayList<String>(), null);

        validatesProductService = new ValidatesProductService();
        validatesProductService.validatesProduct(productWithoutInformation, new ControlProductService());

    }

    @AfterClass
    public static void end(){

        System.out.println("Finishing the tests");

    }

}
