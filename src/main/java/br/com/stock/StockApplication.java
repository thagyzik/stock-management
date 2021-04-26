package br.com.stock;

import br.com.stock.application.CollectionManagement;
import br.com.stock.application.ProductManagement;
import br.com.stock.services.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StockApplication {

    public static void main(String[] args) {

        ProductManagement productManagement = new ProductManagement();
        CollectionManagement collectionManagement = new CollectionManagement();
        ControlProductService controlProductService = new ControlProductService();
        ControlCollectionService controlCollectionService = new ControlCollectionService();

        int menuOption = 0;

        System.out.println(" Welcome to the Control of Products and Collections of the Stock. ");

        do{

            try {

                Scanner scanner = new Scanner(System.in);
                System.out.println("\n Select the Desired Option! \n");
                System.out.println("1 - Product Management.");
                System.out.println("2 - Collection Management.");
                System.out.println("3 - Exit.");
                menuOption = scanner.nextInt();

                if (menuOption == 1){

                    productManagement.management(controlProductService, controlCollectionService);

                } else if (menuOption == 2){

                    collectionManagement.management(controlCollectionService);

                } else if (menuOption == 3){

                    System.out.println("End!");

                } else {

                    System.err.println("Incorrect option, try again!");

                }

            } catch (InputMismatchException e){

                System.err.println("You must enter a number corresponding to the menu!");

            } catch (Exception e){

                System.err.println(e.getMessage());

            }

        } while (menuOption != 3);

    }

}
