package br.com.stock.services;

import br.com.stock.entities.Collection;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ValidatesCollectionServiceTest {

    @InjectMocks
    private ValidatesCollectionService validatesCollectionService;

    @BeforeClass
    public static void setup(){

        System.out.println("ValidatesCollectionServiceTest class");

    }

    @Before
    public void beforeEachClass(){

        System.out.println("Starting the tests");

    }

    @Test
    public void testValidatesCollection() throws Exception {

        List<String> keywordListTest = new ArrayList<>();
        keywordListTest.add("Keyword one");
        keywordListTest.add("Keyword two");
        keywordListTest.add("Keyword three");

        Collection collection = new Collection("Collection", "My collection test", keywordListTest);

        validatesCollectionService = new ValidatesCollectionService();

        Assert.assertTrue("Values are missing to insert the collection", validatesCollectionService.validatesCollection(collection, null, new ControlCollectionService()));

    }

    @Test(expected = Exception.class)
    public void testValidatesCollectionError() throws Exception {

        Collection collection = new Collection("", "", new ArrayList<>());

        validatesCollectionService = new ValidatesCollectionService();

        validatesCollectionService.validatesCollection(collection, new Collection(), new ControlCollectionService());

    }

    @AfterClass
    public static void end(){

        System.out.println("Finishing the tests");

    }

}
