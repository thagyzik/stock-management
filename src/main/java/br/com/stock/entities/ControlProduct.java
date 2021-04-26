package br.com.stock.entities;

import java.util.List;

public interface ControlProduct {

    void addProductToStock(Product product);
    List<String> listProductsInStock();
    boolean searchProductsByName(String nome);

}
