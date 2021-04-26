package br.com.stock.services;

import br.com.stock.entities.Collection;
import br.com.stock.entities.Product;
import org.junit.*;
import org.junit.runner.RunWith;

import java.util.*;

import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ControlProductServiceTest {

    @InjectMocks
    private ControlProductService controlProductService;

    @BeforeClass
    public static void setup(){

        System.out.println("ControleProdutoServiceTest class");

    }

    @Before
    public void beforeEachClass(){

        System.out.println("Starting the tests");

    }

    @Test
    public void testAddProductToStock(){

        // valida se produto é inserido corretamente, considerando apenas o nome como chave unica
        // validates if product is inserted correctly, considering only the name as a unique key
        Map<String, Product> mapProduct = createProducts();
        Product product = new Product("New Product", mapProduct.get("Product 1").getPrice(),mapProduct.get("Product 1").getDescription(), mapProduct.get("Product 1").getQuantity(), mapProduct.get("Product 1").getCollection(),mapProduct.get("Product 1").getImagesList(),null);

        controlProductService = new ControlProductService(mapProduct);

        controlProductService.addProductToStock(product);

        // validado se apos a inserção, se ao buscar pelo nome, todos os produtos são identificados
        // validated after insertion, if by searching for the name, all products are identified
        Assert.assertTrue("Product 1 unidentified", controlProductService.searchProductsByName("Product 1"));
        Assert.assertTrue("Product 2 unidentified", controlProductService.searchProductsByName("Product 2"));
        Assert.assertTrue("Product 3 unidentified", controlProductService.searchProductsByName("Product 3"));
        Assert.assertTrue("Unidentified New Product", controlProductService.searchProductsByName("New Product"));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddProductToStockError(){

        // valida se gera erro ao tentar inserir o mesmo produto, considerando apenas o nome como chave unica
        // validates if an error is generated when trying to insert the same product, considering only the name as a unique key
        Map<String, Product> mapProduct = createProducts();
        Product product = mapProduct.get("Product 1");

        controlProductService = new ControlProductService(mapProduct);
        controlProductService.addProductToStock(product);

    }

    @Test
    public void testListProductsInStock(){

        Map<String, Product> mapProduct = createProducts();

        controlProductService = new ControlProductService(mapProduct);
        List<String> totalProductsListed = controlProductService.listProductsInStock();
        List<String> nameProducts = new ArrayList<>();
        nameProducts.add(mapProduct.get("Product 1").getName());
        nameProducts.add(mapProduct.get("Product 2").getName());
        nameProducts.add(mapProduct.get("Product 3").getName());

        Collections.sort(totalProductsListed);

        Assert.assertEquals(mapProduct.size(), totalProductsListed.size());
        Assert.assertEquals(nameProducts, totalProductsListed);

    }

    @Test
    public void testSearchProductsByName(){

        Map<String, Product> mapProduct = createProducts();

        controlProductService = new ControlProductService(mapProduct);

        Assert.assertTrue("Product 1 unidentified", controlProductService.searchProductsByName("Product 1"));
        Assert.assertTrue("Product 2 unidentified", controlProductService.searchProductsByName("Product 2"));
        Assert.assertTrue("Product 3 unidentified", controlProductService.searchProductsByName("Product 3"));

    }

    @Test(expected = AssertionError.class)
    public void testSearchProductsByNameError(){

        Map<String, Product> mapProduct = createProducts();

        controlProductService = new ControlProductService(mapProduct);

        Assert.assertTrue("Unidentified New Product", controlProductService.searchProductsByName("New Product"));

    }

    @Test
    public void testSearchProductsByEmptyNameError(){

        controlProductService = new ControlProductService();

        Assert.assertFalse(controlProductService.searchProductsByName("Product 1"));

    }

    public Map<String, Product> createProducts(){

        Map<String, Product> mapProducts = new HashMap<String, Product>();

        List<String> keywordListTest = new ArrayList<>();
        keywordListTest.add("Keyword one");
        keywordListTest.add("Keyword two");
        keywordListTest.add("Keyword three");

        List<String> imagesListTest = new ArrayList<>();
        imagesListTest.add("Image one");

        Collection subCollection = new Collection("Sub-collection One", "My sub-collection test", keywordListTest);

        Product productToTestOne = new Product("Product 1", 123.4, "Test for product One", 2, subCollection, imagesListTest, null);
        Product productToTestTwo = new Product("Product 2", 144.0, "Test for product Two", 4, subCollection, imagesListTest, null);
        Product productToTestThree = new Product("Product 3", 52.6, "Test for product Three", 2, subCollection, imagesListTest, null);

        mapProducts.put(productToTestOne.getName(), productToTestOne);
        mapProducts.put(productToTestTwo.getName(), productToTestTwo);
        mapProducts.put(productToTestThree.getName(), productToTestThree);

        return mapProducts;

    }

    @AfterClass
    public static void end(){

        System.out.println("Finishing the tests");

    }

}
