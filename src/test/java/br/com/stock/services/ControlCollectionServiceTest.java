package br.com.stock.services;

import br.com.stock.entities.Collection;
import br.com.stock.entities.Images;
import br.com.stock.entities.Product;
import br.com.stock.entities.Variant;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class ControlCollectionServiceTest {

    @InjectMocks
    private ControlCollectionService controlCollectionService;

    @BeforeClass
    public static void setup(){

        System.out.println("ControlCollectionServiceTest class");

    }

    @Before
    public void beforeEachClass(){

        System.out.println("Starting the tests");

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCollectionMainError(){

        Map<Collection, Map<Collection, List<Product>>> mapAllCollection = createCollections();
        Collection mainCollection = new Collection("Main Collection One", "My main collection test", new ArrayList<>());

        controlCollectionService = new ControlCollectionService(mapAllCollection);
        controlCollectionService.createCollectionMain(mainCollection);

    }

    @Test
    public void testCreateCollectionMain(){

        Map<Collection, Map<Collection, List<Product>>> mapAllCollection = createCollections();

        controlCollectionService = new ControlCollectionService(mapAllCollection);

        Collection mainCollection = collectionSearch(mapAllCollection.keySet());

        Assert.assertEquals(mainCollection, controlCollectionService.validatesExistingCollection(mainCollection.getName(), true));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCollectionTypeSubCollectionError(){

        Map<Collection, Map<Collection, List<Product>>> mapAllCollection = createCollections();

        controlCollectionService = new ControlCollectionService(mapAllCollection);

        Collection mainCollection = collectionSearch(mapAllCollection.keySet());

        Collection subCollection = collectionSearch(mapAllCollection.get(mainCollection).keySet());

        controlCollectionService.createCollectionTypeSubCollection(subCollection, mainCollection);

    }

    @Test
    public void testCreateCollectionTypeSubCollection(){

        Map<Collection, Map<Collection, List<Product>>> mapAllCollection = createCollections();

        controlCollectionService = new ControlCollectionService(mapAllCollection);

        Collection mainCollection = collectionSearch(mapAllCollection.keySet());

        Collection subCollection = new Collection("Sub-collection Two", "My sub-collection test", new ArrayList<>());

        controlCollectionService.createCollectionTypeSubCollection(subCollection, mainCollection);

        Assert.assertEquals(subCollection, controlCollectionService.validatesExistingCollection("Sub-collection Two", false));

    }

    @Test
    public void testListCollectionsError(){

        controlCollectionService = new ControlCollectionService();

        Assert.assertFalse(controlCollectionService.listCollections());

    }

    @Test
    public void testListCollections(){

        Map<Collection, Map<Collection, List<Product>>> mapAllCollection = createCollections();

        controlCollectionService = new ControlCollectionService(mapAllCollection);

        Assert.assertTrue(controlCollectionService.listCollections());

    }

    @Test
    public void testSearchProductWithoutSubCollectionError(){

        Map<Collection, Map<Collection, List<Product>>> mapAllCollection = createCollections();

        controlCollectionService = new ControlCollectionService(mapAllCollection);

        Product newProduct = new Product();

        for (Map<Collection, List<Product>> allCollections : mapAllCollection.values()){

            Collection subCollection = collectionSearch(allCollections.keySet());

            for (Product product : allCollections.get(subCollection)){

                newProduct = product;

            }

        }

        Collection subCollection = new Collection("Sub-collection Two", "My sub-collection test", new ArrayList<>());

        Assert.assertFalse("Unregistered sub-collection found", controlCollectionService.searchProduct(newProduct.getName(), subCollection.getName()));

    }

    @Test
    public void testSearchProductWithoutProductError(){

        Map<Collection, Map<Collection, List<Product>>> mapAllCollection = createCollections();

        controlCollectionService = new ControlCollectionService(mapAllCollection);

        Collection subCollection = new Collection();

        for (Map<Collection, List<Product>> allCollections : mapAllCollection.values()){

            subCollection = collectionSearch(allCollections.keySet());

        }

        Product newProduct = new Product("New Product", 123.4, "Test for product One", 2, subCollection, new ArrayList<>(), new Variant("Variant test", "23", "Gender test", "Long", "Sport", "Material"));

        Assert.assertFalse("Product not registered found", controlCollectionService.searchProduct(newProduct.getName(), subCollection.getName()));

    }

    @Test
    public void testSearchProduct(){

        Map<Collection, Map<Collection, List<Product>>> mapAllCollection = createCollections();

        controlCollectionService = new ControlCollectionService(mapAllCollection);

        Collection subCollection = new Collection();
        Product newProduct = new Product();

        for (Map<Collection, List<Product>> allCollections : mapAllCollection.values()){

            subCollection = collectionSearch(allCollections.keySet());

            for (Product product : allCollections.get(subCollection)){

                newProduct = product;

            }

        }

        Assert.assertTrue("Unidentified product", controlCollectionService.searchProduct(newProduct.getName(), subCollection.getName()));

    }

    @Test
    public void testListProductsWithoutMainCollectionError(){

        Map<Collection, Map<Collection, List<Product>>> mapAllCollection = createCollections();

        controlCollectionService = new ControlCollectionService(mapAllCollection);

        Collection mainCollection = new Collection("Main Collection Two", "My main collection test", new ArrayList<>());

        Assert.assertFalse("Main Collection not registered to contain product", controlCollectionService.listProducts(mainCollection.getName(), 1));

    }

    @Test
    public void testListProductsWithoutSubCollectionError(){

        Map<Collection, Map<Collection, List<Product>>> mapAllCollection = createCollections();

        controlCollectionService = new ControlCollectionService(mapAllCollection);

        Collection subCollection = new Collection("Sub-collection Two", "My sub-collection test", new ArrayList<>());

        Assert.assertFalse("Sub-collection not registered to contain product", controlCollectionService.listProducts(subCollection.getName(), 2));

    }

    @Test
    public void testListProductsMainCollection(){

        Map<Collection, Map<Collection, List<Product>>> mapAllCollection = createCollections();

        controlCollectionService = new ControlCollectionService(mapAllCollection);

        Collection mainCollection = collectionSearch(mapAllCollection.keySet());

        Assert.assertTrue("Main Collection not found", controlCollectionService.listProducts(mainCollection.getName(), 1));

    }

    @Test
    public void testListProductsSubCollection(){

        Map<Collection, Map<Collection, List<Product>>> mapAllCollection = createCollections();

        controlCollectionService = new ControlCollectionService(mapAllCollection);

        Collection mainCollection = collectionSearch(mapAllCollection.keySet());
        Collection subCollection = collectionSearch(mapAllCollection.get(mainCollection).keySet());

        Assert.assertTrue("Sub-collection not found", controlCollectionService.listProducts(subCollection.getName(), 2));

    }

    @Test
    public void testValidatesExistingMainCollectionError(){

        Map<Collection, Map<Collection, List<Product>>> mapAllCollection = createCollections();

        controlCollectionService = new ControlCollectionService(mapAllCollection);

        Collection mainCollection = new Collection("Main Collection Two", "My main collection test", new ArrayList<>());

        Assert.assertNull(controlCollectionService.validatesExistingCollection(mainCollection.getName(), true));

    }

    @Test
    public void testValidatesExistingSubCollectionError(){

        Map<Collection, Map<Collection, List<Product>>> mapAllCollection = createCollections();

        controlCollectionService = new ControlCollectionService(mapAllCollection);

        Collection subCollection = new Collection("Sub-collection Two", "My sub-collection test", new ArrayList<>());

        Assert.assertNull(controlCollectionService.validatesExistingCollection(subCollection.getName(), false));

    }

    @Test
    public void testValidatesExistingMainCollection(){

        Map<Collection, Map<Collection, List<Product>>> mapAllCollection = createCollections();

        controlCollectionService = new ControlCollectionService(mapAllCollection);

        Collection mainCollection = collectionSearch(mapAllCollection.keySet());

        Assert.assertEquals(mainCollection, controlCollectionService.validatesExistingCollection(mainCollection.getName(), true));

    }

    @Test
    public void testValidatesExistingSubCollection(){

        Map<Collection, Map<Collection, List<Product>>> mapAllCollection = createCollections();

        controlCollectionService = new ControlCollectionService(mapAllCollection);

        Collection mainCollection = collectionSearch(mapAllCollection.keySet());
        Collection subCollection = collectionSearch(mapAllCollection.get(mainCollection).keySet());

        Assert.assertEquals(subCollection, controlCollectionService.validatesExistingCollection(subCollection.getName(), false));

    }

    @Test
    public void testInsertProductInASubCollection(){

        Map<Collection, Map<Collection, List<Product>>> mapAllCollection = createCollections();

        controlCollectionService = new ControlCollectionService(mapAllCollection);

        Collection mainCollection = collectionSearch(mapAllCollection.keySet());
        Collection subCollection = collectionSearch(mapAllCollection.get(mainCollection).keySet());

        Product product = new Product("New Product", 123.4, "Test for product One", 2, subCollection, new ArrayList<>(), new Variant("Variant test", "23", "Gender test", "Long", "Sport", "Material"));

        controlCollectionService.insertProductInASubCollection(subCollection, product);

        Assert.assertTrue("Unidentified product", controlCollectionService.searchProduct(product.getName(), subCollection.getName()));

    }

    @Test
    public void testValidatesCollectionNotCreatedError(){

        Map<Collection, Map<Collection, List<Product>>> mapAllCollection = createCollections();

        controlCollectionService = new ControlCollectionService(mapAllCollection);

        Assert.assertNull(controlCollectionService.validatesCollection("New Main Collection", mapAllCollection.keySet(), true));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidatesCollectionAlreadyCreatedError(){

        Map<Collection, Map<Collection, List<Product>>> mapAllCollection = createCollections();

        controlCollectionService = new ControlCollectionService(mapAllCollection);

        controlCollectionService.validatesCollection("Main Collection One", mapAllCollection.keySet(), false);

    }

    @Test
    public void testValidatesCollection(){

        Map<Collection, Map<Collection, List<Product>>> mapAllCollection = createCollections();

        controlCollectionService = new ControlCollectionService(mapAllCollection);

        Collection mainCollection = collectionSearch(mapAllCollection.keySet());

        Assert.assertEquals(mainCollection, controlCollectionService.validatesCollection(mainCollection.getName(),mapAllCollection.keySet(),true));

    }

    private Map<Collection, Map<Collection, List<Product>>> createCollections(){

        Map<Collection, Map<Collection, List<Product>>> allCollections = new HashMap<>();
        Map<Collection, List<Product>> mapSubCollection = new HashMap<>();

        List<Product> productList = new ArrayList<>();

        List<String> keywordListTest = new ArrayList<>();
        keywordListTest.add("Keyword one");
        keywordListTest.add("Keyword two");
        keywordListTest.add("Keyword three");

        Images images = new Images();
        images.setSize("Kb");
        images.setPath("https://link.com");
        images.setExtension("png");
        images.setName("Test images");

        List<String> imagesListTest = new ArrayList<>();
        imagesListTest.add(images.getPath());

        Variant variant = new Variant();
        variant.setColor("Brown");
        variant.setSize("XL");
        variant.setMaterial("Test material");
        variant.setSleeve("Long");
        variant.setActivity("Sport");
        variant.setGender("Gender");

        Collection subCollection = new Collection();
        subCollection.setName("Sub-collection One");
        subCollection.setDescription("My sub-collection test");
        subCollection.setKeyword(keywordListTest);

        Product product = new Product();
        product.setName("Product One");
        product.setPrice(123.5);
        product.setDescription("Product testing");
        product.setQuantity(2);
        product.setCollection(subCollection);
        product.setImagesList(imagesListTest);
        product.setVariant(new Variant(variant.getColor(), variant.getSize(), variant.getGender(), variant.getSleeve(), variant.getActivity(), variant.getMaterial()));

        productList.add(product);

        Collection mainCollection = new Collection("Main Collection One", "My main collection test", keywordListTest);

        mapSubCollection.put(subCollection, productList);
        allCollections.put(mainCollection, mapSubCollection);

        return allCollections;

    }

    private Collection collectionSearch(Set<Collection> setCollection){

        Collection mainCollection = new Collection();

        for (Collection collection : setCollection){

            mainCollection = collection;

        }

        return mainCollection;

    }

    @AfterClass
    public static void end(){

        System.out.println("Finishing the tests");

    }

}
