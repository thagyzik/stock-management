package br.com.stock.services;

import br.com.stock.entities.Product;
import br.com.stock.entities.Variant;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductVariantService {

    public boolean productVariant(Product product){

        try {

            // verifica se o tipo de produto recebe variante
            // checks if the product type receives variant
            int variantType = 0;

            if (product.getName().toLowerCase().contains("t-shirt")) {

                variantType = 1;

            } else if (product.getName().toLowerCase().contains("sneakers")) {

                variantType = 2;

            } else if (product.getName().toLowerCase().contains("pants")) {

                variantType = 3;

            }

            if (variantType != 0) {

                Scanner scanner = new Scanner(System.in);

                System.out.println("Do you want to enter a variant for this product? Inform Y for Yes or N for no: ");
                String variantOption = scanner.nextLine();

                if (variantOption.equalsIgnoreCase("Y")) {

                    String informsVariant;

                    do {

                        Variant variant = variant(variantType);

                        // verifica se a variante foi passada corretamente
                        // checks that the variant has been passed correctly
                        if (variant.getActivity() == null && variant.getColor() == null && variant.getGender() == null && variant.getMaterial() == null &&
                                variant.getSize() == null && variant.getSleeve() == null) {

                            System.err.println("Variant entered incorrectly, would you like to proceed without the variant? Inform Y for Yes or N for no: ");
                            informsVariant = scanner.nextLine();

                        } else {

                            product.setVariant(variant);
                            informsVariant = "Y";

                        }

                    } while (!informsVariant.equalsIgnoreCase("Y"));

                } else {

                    System.out.println("Proceeding without inserting a variant! ");

                }

                return true;

            }

            return false;

        } catch (InputMismatchException e){

            throw new InputMismatchException("Number type entered incorrectly! ");

        }

    }

    private Variant variant(int variantType) {

        // solicita os dados da variante
        // asks for variant data
        Variant variant = new Variant();
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n Enter the type of variant you would like to register: ");
        int variantMenu;
        boolean genericMenu = false;

        if (variantType == 1){

            System.out.println("1 - Color. 2 - Size. 3 - Gender. 4 - Sleeve");
            variantMenu = scanner.nextInt();
            scanner.nextLine();

            if (variantMenu == 4){

                System.out.println("Enter the type of sleeve: ");
                variant.setSleeve(scanner.nextLine());

            } else {

                genericMenu = true;

            }

        } else if (variantType == 2){

            System.out.println("1 - Color. 2 - Size. 3 - Gender. 4 - Activity");
            variantMenu = scanner.nextInt();
            scanner.nextLine();

            genericMenu = true;

        } else if (variantType == 3){

            System.out.println("1 - Color. 2 - Size. 3 - Gender. 4 - Activity. 5 - Material");
            variantMenu = scanner.nextInt();
            scanner.nextLine();

            if (variantMenu == 5){

                System.out.println("Inform the material: ");
                variant.setMaterial(scanner.nextLine());

            } else {

                genericMenu = true;

            }

        } else {

            throw new InputMismatchException("Select from the menu options! ");

        }

        if (genericMenu){

            genericMenu(variantMenu, scanner, variant, variantType);

        }

        return variant;

    }

    private void genericMenu(int variantMenu, Scanner scanner, Variant variant, int variantType){

        if (variantMenu == 1){

            System.out.println("Enter the color: ");
            variant.setColor(scanner.nextLine());

        } else if (variantMenu == 2){

            String size;
            System.out.println("Enter the size: ");

            if (variantType == 2){

                size = scanner.nextLine();

                if (size.matches("^[0-9]*$")) {

                    variant.setSize(size);

                }

            } else {

                boolean validValue = true;

                if (variantType == 1){

                    System.out.println("Options: XS - P - M - L - XL - XXL - XXXL");
                    size = scanner.nextLine();

                    if (!size.equalsIgnoreCase("XS") && !size.equalsIgnoreCase("P") && !size.equalsIgnoreCase("M") &&
                            !size.equalsIgnoreCase("L") && !size.equalsIgnoreCase("XL") && !size.equalsIgnoreCase("XXL") &&
                            !size.equalsIgnoreCase("XXXL")){

                        validValue = false;

                    }

                } else {

                    size = scanner.nextLine();

                }

                if (validValue) {

                    variant.setSize(size);

                }

            }

        } else if (variantMenu == 3){

            System.out.println("Enter gender: ");
            variant.setGender(scanner.nextLine());

        } else if (variantMenu == 4 && (variantType == 2 || variantType == 3)){

            System.out.println("Enter the activity: ");
            variant.setActivity(scanner.nextLine());

        } else {

            throw new InputMismatchException("Select from the menu options! ");

        }

    }

}
