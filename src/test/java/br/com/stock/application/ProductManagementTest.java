package br.com.stock.application;

import br.com.stock.services.ControlCollectionService;
import br.com.stock.services.ControlProductService;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProductManagementTest {

    @InjectMocks
    private ProductManagement productManagement;

    @BeforeClass
    public static void setup(){

        System.out.println("ProductManagementTest class");

    }

    @Before
    public void beforeEachClass(){

        System.out.println("Starting the tests");

    }

    @Test(expected = Exception.class)
    public void testProductCollection() throws Exception {

        productManagement.management(new ControlProductService(), new ControlCollectionService());

    }

    @AfterClass
    public static void end(){

        System.out.println("Finishing the tests");

    }

}
