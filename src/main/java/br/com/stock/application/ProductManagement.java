package br.com.stock.application;

import br.com.stock.entities.Product;
import br.com.stock.services.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ProductManagement {

    public void management(ControlProductService controlProductService, ControlCollectionService controlCollectionService) throws Exception {

        if (controlCollectionService.listCollections()) {

            int productMenu = 0;

            System.out.println("\n Welcome to the Control of Products.\n");

            do {

                try {

                    Scanner scanner = new Scanner(System.in);
                    System.out.println("\n Select the Desired Option! \n");
                    System.out.println("1 - Save a new product in stock.");
                    System.out.println("2 - List products already saved.");
                    System.out.println("3 - Search for a product.");
                    System.out.println("4 - Back to the main menu.");
                    productMenu = scanner.nextInt();

                    String followInsertion = "N";

                    if (productMenu == 1) {

                        do {

                            try {

                                Product product = new Product();
                                ValidatesProductService validatesProductService = new ValidatesProductService();
                                ProductCollectionService productCollectionService = new ProductCollectionService();
                                ProductImagesService productImagesService = new ProductImagesService();
                                ProductVariantService productVariantService = new ProductVariantService();

                                // solicita dados do produto
                                System.out.println("Enter the price of the product: ");
                                product.setPrice(scanner.nextDouble());

                                scanner.nextLine();

                                System.out.println("Enter the product name: ");
                                product.setName(scanner.nextLine());

                                System.out.println("Enter the quantity of this product: ");
                                product.setQuantity(scanner.nextInt());

                                scanner.nextLine();

                                System.out.println("Enter the product description: ");
                                product.setDescription(scanner.nextLine());

                                productCollectionService.productCollection(product, controlCollectionService);

                                productImagesService.productImages(product);

                                productVariantService.productVariant(product);

                                // passa os valores recebidos para uma validação antes de salvar
                                validatesProductService.validatesProduct(product, controlProductService);

                                // sempre verifica se cliente quer inserir novos produtos
                                System.out.println("Select Y to insert new products or N to finish");
                                followInsertion = scanner.nextLine();

                            } catch (InputMismatchException e) {

                                followInsertion = "N";
                                System.err.println("Number type entered incorrectly, try again!");

                            } catch (IllegalArgumentException e) {

                                System.err.println("Product already in stock!");

                            } catch (Exception e) {

                                System.err.println(e.getMessage());
                                followInsertion = "N";

                            }

                        } while (!followInsertion.equalsIgnoreCase("N"));

                    } else if (productMenu == 2) {

                        // chama o metodo para imprimir todos os produtos e imprime o numero de produtos listados
                        List<String> totalProductsListed = controlProductService.listProductsInStock();

                        System.out.println(" Total processed: " + totalProductsListed.size());

                    } else if (productMenu == 3) {

                        // solicita o nome do produto a ser listado somente preço e descrição
                        System.out.println("What product name do you want to search for? ");

                        scanner.nextLine();

                        boolean returnProduto = controlProductService.searchProductsByName(scanner.nextLine());

                        if (!returnProduto) {

                            System.err.println("Unidentified product in stock!");

                        }

                    } else if (productMenu == 4) {

                        System.out.println("Returning to the main menu! ");

                    } else {

                        System.err.println("Incorrect option, try again!");

                    }

                } catch (InputMismatchException e) {

                    System.err.println("You must enter a number corresponding to the menu");

                }

            } while (productMenu != 4);

        } else {

            throw new Exception("All products must be linked to a sub-collection. Create a sub-collection before accessing Product Management! ");

        }

    }

}
