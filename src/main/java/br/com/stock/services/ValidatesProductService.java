package br.com.stock.services;

import br.com.stock.entities.Product;

public class ValidatesProductService {

    // valida se não possui dados vazios ou nulls
    // validates if you don't have empty data or nulls
    public boolean validatesProduct(Product product, ControlProductService controlProductService) throws Exception {

        if (product.getName() != null && !product.getName().equals("") &&
                product.getDescription() != null && !product.getDescription().equals("") &&
                product.getCollection() != null && !product.getCollection().equals("") &&
         !product.getImagesList().isEmpty()){

            // envia produto para ser inserido ao estoque
            // sends product to be inserted into stock
            controlProductService.addProductToStock(product);

            System.out.println("Finished insertion \n");

            return true;

        } else {

            throw new Exception("Missing values to insert product in stock ");

        }

    }

}
