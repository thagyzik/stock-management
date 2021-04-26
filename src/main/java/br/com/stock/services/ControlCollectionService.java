package br.com.stock.services;

import br.com.stock.entities.Collection;
import br.com.stock.entities.ControlCollection;
import br.com.stock.entities.Product;
import java.util.*;

public class ControlCollectionService implements ControlCollection {

    private Map<Collection, Map<Collection, List<Product>>> allCollections;

    public ControlCollectionService(){

        this.allCollections = new HashMap<Collection, Map<Collection, List<Product>>>();

    }

    public ControlCollectionService(Map<Collection, Map<Collection, List<Product>>> allCollections) {

        this.allCollections = allCollections;

    }

    @Override
    public void createCollectionMain(Collection mainCollection) {

        // valida se Coleção Principal não existe antes de inseri-la novamente
        // validates if Main Collection does not exist before inserting it again
        validatesCollection(mainCollection.getName(), this.allCollections.keySet(), false);

        this.allCollections.put(mainCollection, new HashMap<Collection, List<Product>>());
        System.out.println("New main collection saved: " + mainCollection.toString());

    }

    @Override
    public void createCollectionTypeSubCollection(Collection subCollection, Collection mainCollection) {

        // valida se Sub Coleção não existe antes de inseri-la novamente
        // validates if Sub-collection does not exist before inserting it again
        validatesCollection(subCollection.getName(), this.allCollections.get(mainCollection).keySet(), false);

        this.allCollections.get(mainCollection).put(subCollection, new ArrayList<Product>());

        System.out.println("New sub-collection saved: " + subCollection.toString());

    }

    @Override
    public boolean listCollections() {

        if (!this.allCollections.isEmpty()) {

            for (Collection entryAllCollections : this.allCollections.keySet()) {

                System.out.println(" Main Collection : " + entryAllCollections.getName());

                for (Collection entrySubCollection : this.allCollections.get(entryAllCollections).keySet()) {

                    System.out.println(" - sub-collection: " + entrySubCollection.getName());

                }

            }

        } else {

            return false;

        }

        return true;

    }

    @Override
    public boolean searchProduct(String nameProduct, String nameSubCollection) {

        // para localizar o produto é buscado pela Sub Coleção
        // to locate the product is searched for by the Sub Collection
        for (Map<Collection, List<Product>> collection : this.allCollections.values()) {

            for (Collection keySubCollection : collection.keySet()){

                if (keySubCollection.getName().contains(nameSubCollection)){

                    for (Product product : collection.get(keySubCollection)){

                        if (product.getName().contains(nameProduct)){

                            System.out.println("Identified product: " + product.toString());
                            return true;

                        }

                    }

                }

            }

        }

        return false;

    }

    @Override
    public boolean listProducts(String nameCollection, int collectionType) {

        // para listar é identificado se é passada uma coleção do tipo 1 (Coleção Principal) ou tipo 2 (Sub Coleção)
        // to list it is identified whether a collection of type 1 (Main Collection) or type 2 (Sub-collection) is passed
        if (collectionType == 1){

            for (Collection mainCollection : this.allCollections.keySet()){

                if (mainCollection.getName().equals(nameCollection)) {

                    for (List<Product> productList : this.allCollections.get(mainCollection).values()){

                        for (Product product : productList) {

                            System.out.println(" - " + product.toString());

                        }

                    }

                    return true;

                }

            }

        } else {

            for (Map<Collection, List<Product>> mapSubCollection : this.allCollections.values()){

                for (Collection subCollection : mapSubCollection.keySet()){

                    if (subCollection.getName().equals(nameCollection)){

                        for (Product productList : mapSubCollection.get(subCollection)) {

                            System.out.println(" - " + productList.toString());

                        }

                        return true;

                    }

                }

            }

        }

        return false;

    }

    @Override
    public Collection validatesExistingCollection(String nameCollection, boolean mainCollection) {

        Collection returnCollection = null;

        if (mainCollection){

            returnCollection =  validatesCollection(nameCollection, this.allCollections.keySet(), true);

        } else {

            for (Map<Collection, List<Product>> subCollection : this.allCollections.values()) {

                for (Collection collection : subCollection.keySet()){

                    if (collection.getName().contains(nameCollection)){

                        returnCollection =  validatesCollection(nameCollection, subCollection.keySet(), true);
                        break;

                    }

                }

            }

        }

        return returnCollection;

    }

    @Override
    public void insertProductInASubCollection(Collection subCollection, Product product) {

        for (Map<Collection, List<Product>> mapMainCollection : this.allCollections.values()) {

            for (Collection keySubCollection : mapMainCollection.keySet()){

                if (keySubCollection.getName().contains(subCollection.getName())){

                    // recebe a lista de produtos já inseridos na Sub Coleção e insere mais um
                    // receives the list of products already inserted in the Sub-collection and inserts one more
                    List<Product> productList = mapMainCollection.get(keySubCollection);
                    productList.add(product);

                    mapMainCollection.put(subCollection, productList);

                }

            }

        }

    }

    public Collection validatesCollection(String nameCollection, Set<Collection> mapCollection, boolean returnCollection){

        for (Collection collection : mapCollection) {

            if (collection.getName().contains(nameCollection)) {

                if (returnCollection){

                    System.out.println("Found collection: " + collection.toString());

                    return collection;

                } else {

                    throw new IllegalArgumentException("Collection is already created!");

                }

            }

        }

        return null;

    }

}
