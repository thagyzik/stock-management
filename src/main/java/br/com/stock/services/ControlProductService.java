package br.com.stock.services;

import br.com.stock.entities.ControlProduct;
import br.com.stock.entities.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControlProductService implements ControlProduct {

    private Map<String, Product> mapProducts;

    public ControlProductService(){

        this.mapProducts = new HashMap<String, Product>();

    }

    public ControlProductService(Map<String, Product> mapProducts){

        this.mapProducts = mapProducts;

    }

    @Override
    public void addProductToStock(Product product){

        // valida se produto não existe antes de inseri-lo novamente
        // validates if product does not exist before inserting it again
        if (this.mapProducts.get(product.getName()) != null){

            throw new IllegalArgumentException("Product is already in stock!");

        }

        this.mapProducts.put(product.getName(), product);
        System.out.println("New product saved: " + product.toString());

    }

    @Override
    public List<String> listProductsInStock(){

        int i = 0;

        for (Map.Entry entryProduto : this.mapProducts.entrySet()){

            System.out.println(i + " " + entryProduto.getValue());

            i += 1;

        }

        return new ArrayList<>(this.mapProducts.keySet());

    }

    @Override
    public boolean searchProductsByName(String name) {

        String identifyProduct = null;

        // caso produto não seja identificado ou lista esteja vazia, retorna false
        // if product is not identified or list is empty, returns false
        if (!this.mapProducts.isEmpty()) {

            for (Map.Entry entryProduct : this.mapProducts.entrySet()) {

                // valida se existe produto exatamente com mesmo nome informado
                if (entryProduct.getKey().equals(name)) {

                    identifyProduct = "Product: " + name + " - Price: " + this.mapProducts.get(name).getPrice() + " - Description: " + this.mapProducts.get(name).getDescription();

                    System.out.println(identifyProduct);

                    return true;

                }

            }

        }
        return false;

    }

}
