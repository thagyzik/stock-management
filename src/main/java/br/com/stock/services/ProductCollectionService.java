package br.com.stock.services;

import br.com.stock.entities.Collection;
import br.com.stock.entities.Product;
import java.util.Scanner;

public class ProductCollectionService {

    public void productCollection(Product product, ControlCollectionService controlCollectionService) throws Exception {

        // imprime as coleções criadas para usuário verificar onde inserir o produto
        // prints the collections created for the user to check where to insert the product
        System.out.println("\n Listing collections and subcollections already created: \n");
        boolean listCollections = controlCollectionService.listCollections();

        // valida se já existe alguma coleção criada
        // validates if there is already a collection created
        if (listCollections){

            Scanner scanner = new Scanner(System.in);

            System.out.println("\n Among the existing sub-collections, inform which sub-collection this product will belong to: ");
            String subCollection = scanner.nextLine();

            Collection validatesSubCollection = null;

            if (!subCollection.equals("")) {

                // valida se a Sub Coleção informada existe
                // validates if the informed Sub-collection exists
                validatesSubCollection = controlCollectionService.validatesExistingCollection(subCollection, false);

            }

            if (validatesSubCollection != null) {

                // insere o produto na Sub Coleção
                // inserts the product in the Sub-collection
                product.setCollection(validatesSubCollection);
                controlCollectionService.insertProductInASubCollection(product.getCollection(), product);

            } else {

                throw new Exception("The subcollection reported was not identified");

            }

        } else {

            throw new Exception("No collection created to link to the product! Create a collection before proceeding!");

        }

    }

}
