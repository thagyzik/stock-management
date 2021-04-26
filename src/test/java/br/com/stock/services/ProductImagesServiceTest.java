package br.com.stock.services;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProductImagesServiceTest {

    @InjectMocks
    private ProductImagesService productImagesService;

    @BeforeClass
    public static void setup(){

        System.out.println("ProductImagesServiceTest class");

    }

    @Before
    public void beforeEachClass(){

        System.out.println("Starting the tests");

    }

    @Test(expected = Exception.class)
    public void testProductCollection() throws Exception {

        productImagesService.productImages(null);

    }

    @AfterClass
    public static void end(){

        System.out.println("Finishing the tests");

    }

}
