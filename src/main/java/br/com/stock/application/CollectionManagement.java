package br.com.stock.application;

import br.com.stock.entities.Collection;
import br.com.stock.services.ControlCollectionService;
import br.com.stock.services.ValidatesCollectionService;

import java.util.*;

public class CollectionManagement {

    public void management(ControlCollectionService controlCollectionService){

        int collectionMenu = 0;

        System.out.println(" Welcome to the Control of Collections. ");

        do{

            try {

                ValidatesCollectionService validatesCollectionService;

                Scanner scanner = new Scanner(System.in);
                System.out.println("\n Select the Desired Option! \n");
                System.out.println("1 - Create new Main Collection.");
                System.out.println("2 - Create new sub-collection.");
                System.out.println("3 - List Collections .");
                System.out.println("4 - Search for Product in a sub-collections.");
                System.out.println("5 - List for Products in a Collection (main and/or sub).");
                System.out.println("6 - Back to the main menu.");
                collectionMenu = scanner.nextInt();

                if (collectionMenu == 1){

                    // envia os dados da coleção principal para uma validação antes de inserir
                    // sends the Main Collection data for validation before inserting
                    validatesCollectionService = new ValidatesCollectionService();
                    validatesCollectionService.validatesCollection(createCollections(scanner), null, controlCollectionService);

                } else if (collectionMenu == 2){

                    validatesCollectionService = new ValidatesCollectionService();
                    Collection createSubCollection = createCollections(scanner);

                    System.out.println("Enter the name of the Main Collection, to which this sub-collection will be linked: ");
                    String nameMainCollection = scanner.nextLine();

                    // valida se a sub coleção informada existe
                    // validates whether the informed sub-collection exists
                    Collection validatesExistingCollection =  controlCollectionService.validatesExistingCollection(nameMainCollection, true);

                    if (validatesExistingCollection != null){

                        // envia os dados da sub coleção para uma validação antes de inserir
                        // sends the Sub-collection data for validation before inserting
                        validatesCollectionService.validatesCollection(createSubCollection, validatesExistingCollection, controlCollectionService);

                    } else {

                        System.err.println("Unidentified main collection, create the main collection before! ");

                    }

                } else if (collectionMenu == 3){

                    boolean listCollections = controlCollectionService.listCollections();

                    if (!listCollections){

                        System.err.println("No collections created! ");

                    }

                } else if (collectionMenu == 4){

                    // solicita o nome do produto e nome da sub coleção para procurar
                    // prompts for product name and Sub-collection name to search
                    System.out.println("Enter the name of the product you want to search: ");
                    scanner.nextLine();
                    String nameProduct = scanner.nextLine();

                    System.out.println("Enter the name of the sub-collection where this product is located: ");
                    String nameSubCollection = scanner.nextLine();

                    if(nameProduct.equals("") || nameSubCollection.equals("") || !controlCollectionService.searchProduct(nameProduct, nameSubCollection)){

                        System.err.println("Unidentified product or sub-collection! ");

                    }

                } else if (collectionMenu == 5){

                    // solicita qual tipo de coleção listar
                    // asks which type of collection to list
                    System.out.println("\n Enter the desired option: ");
                    System.out.println("1. List the products of a main collection ");
                    System.out.println("2. List products in a sub-collection ");
                    int collectionType = scanner.nextInt();

                    if (collectionType == 1 || collectionType == 2){

                        // solicita o nome da coleção para listar
                        // prompts for the name of the collection to list
                        System.out.println("Which collection would you like to list?: ");
                        scanner.nextLine();
                        String nameCollection = scanner.nextLine();

                        boolean listProducts =  controlCollectionService.listProducts(nameCollection, collectionType);

                        if (!listProducts){

                            System.err.println("Not identified the product in the informed collection");

                        }

                    } else {

                        System.err.println("Incorrect option! ");

                    }

                } else if (collectionMenu == 6){

                    System.out.println("Returning to the main menu! ");

                } else {

                    System.err.println("Incorrect option, try again! ");

                }

            } catch (InputMismatchException e){

                System.err.println("You must enter a number corresponding to the menu!");

            } catch (IllegalArgumentException e) {

                System.err.println("Collection already exists!");

            } catch (Exception e) {

                System.err.println(e.getMessage());

            }

        } while (collectionMenu != 6);

    }

    private Collection createCollections(Scanner scanner){

        // solicita os dados da coleção
        // requests the collection data
        Collection collection = new Collection();

        System.out.println("Enter the name of the collection: ");
        scanner.nextLine();
        collection.setName(scanner.nextLine());

        System.out.println("Enter the description of the collection: ");
        collection.setDescription(scanner.nextLine());

        String addKeyword = "N";
        List<String> listKeyword = new ArrayList<String>();

        do {

            System.out.println("Enter the keyword for the collection: ");
            listKeyword.add(scanner.nextLine());

            System.out.println("Select Y to enter one more keyword or N for no (Maximum 6 key words)");
            addKeyword = scanner.nextLine();

        } while (!addKeyword.equalsIgnoreCase("N") && listKeyword.size() < 6);

        collection.setKeyword(listKeyword);

        return collection;

    }

}
