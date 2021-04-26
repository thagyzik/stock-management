package br.com.stock.services;

import br.com.stock.entities.Images;
import br.com.stock.entities.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductImagesService {

    public void productImages(Product product) throws Exception {

        // valida se imagem será vinculada a um produto existente
        // validates if image will be linked to an existing product
        if (product != null) {

            // solicita os dados da imagem
            // request image data
            Images images = new Images();

            Scanner scanner = new Scanner(System.in);

            String insertImages = "N";
            List<String> listUrlImages = new ArrayList<String>();

            do {

                System.out.println("\n Enter the image data");
                System.out.println("Name: ");
                images.setName(scanner.nextLine());

                System.out.println("Size: ");
                images.setSize(scanner.nextLine());

                System.out.println("Extension: ");
                images.setExtension(scanner.nextLine());

                System.out.println("Path: ");
                listUrlImages.add(scanner.nextLine());

                System.out.println("Select Y to insert one more image or N to not (Maximum 5 images)");
                insertImages = scanner.nextLine();

            } while (!insertImages.equalsIgnoreCase("N") && listUrlImages.size() < 5);

            product.setImagesList(listUrlImages);

        } else {

            throw new Exception("Imagem precisa ser vinculada a um produto");

        }

    }

}
