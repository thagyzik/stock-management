package br.com.stock.entities;

public interface ControlCollection {

    void createCollectionMain(Collection mainCollection);
    void createCollectionTypeSubCollection(Collection subCollection, Collection mainCollection);
    boolean listCollections();
    boolean searchProduct(String nameProduct, String nameSubCollection);
    boolean listProducts(String nameCollection, int collectionType);
    Collection validatesExistingCollection(String nameCollection, boolean mainCollection);
    void insertProductInASubCollection(Collection subCollection, Product product);

}
