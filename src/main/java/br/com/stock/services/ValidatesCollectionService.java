package br.com.stock.services;

import br.com.stock.entities.Collection;

public class ValidatesCollectionService {

    // valida se não possui dados vazios ou nulls
    // validates if you don't have empty data or nulls
    public boolean validatesCollection(Collection collection, Collection validatesExistingCollection, ControlCollectionService controlCollectionService) throws Exception {

        if (collection.getName() != null && !collection.getName().equals("") &&
                collection.getDescription() != null && !collection.getDescription().equals("") &&
                collection.getKeyword() != null && !collection.getKeyword().isEmpty()) {

            if (validatesExistingCollection != null){

                // envia Sub-coleção para ser inserida junto com nome da coleção principal a qual irá pertencer
                // sends Sub-collection to be inserted together with the name of the Main Collection to which it will belong
                controlCollectionService.createCollectionTypeSubCollection(collection, validatesExistingCollection);

            } else {

                // envia coleção principal para ser inserida
                // sends Main Collection to be inserted
                controlCollectionService.createCollectionMain(collection);

            }

            System.out.println("Finished insertion \n");

            return true;

        } else {

            throw new Exception("Missing values to insert product in stock ");

        }

    }

}
