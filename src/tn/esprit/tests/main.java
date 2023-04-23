package tn.esprit.tests;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import tn.esprit.entities.Shop;
import tn.esprit.services.ShopService;
import tn.esprit.tools.MaConnexion;

import java.util.List;
import tn.esprit.entities.Product;
import tn.esprit.services.ProductService;

public class main {
    public static void main(String[] args) {
        
        ShopService shopService = new ShopService();
        ProductService productService = new ProductService();
        
        ProductService product = new ProductService();
  /*  
    // call the method to retrieve the list of products
    List<Product> productList = product.afficherr();
    
    // iterate over the list of products and print their details
    for (Product p : productList) {
        System.out.println("Product ID: " + p.getId());
        System.out.println("Name: " + p.getName());
        System.out.println("Price: " + p.getPrice());
        System.out.println("Type: " + p.getType());
        System.out.println("Stock: " + p.getStock());
        System.out.println("Shop ID: " + p.getShop().getId());
        System.out.println("Shop Name: " + p.getShop().getName());
        System.out.println("------------------------");
    }*/

       
        /*
        //Affichage product :
        List<Product> products = productService.afficherr();
        System.out.println("List of products:");
        for (Product p : products) {
            System.out.println(p);
        }
        */
        /*
       // Affichage shop :
        List<Shop> shops = shopService.afficher();
        System.out.println("List of shops:");
        for (Shop s : shops) {
            System.out.println(s);} 
       */
        
        
        
        
        /*
        // supprimer product:
         int productId = 43; // replace with the ID of the product you want to delete
         Product productToDelete = productService.findById(productId);
         if (productToDelete != null) {
             productService.supprimer(productToDelete);
         } else {
             System.out.println("Product not found."); }
        */
        
       //supprimer shop:
        int shopId = 35; 
        Shop shopToDelete = shopService.findById(shopId);
        if (shopToDelete != null) {
        shopService.supprimer(shopToDelete);
            } else {
            System.out.println("Shop not found.");
            }
        
        
        
        
        
        /*
        // ajouter shop :
        Date date = new Date(System.currentTimeMillis());
        Shop shop = new Shop("MyShop", "This is my shop", "shop@myshop.com","test",date);
        shopService.ajouter(shop);
        */
        /*
         //ajouter produit
        Product product = new Product("MyProduct", 10.0f, "TypeA", 100);
        productService.ajouter(product);
        */
        
        
        
       
       /*
        //modifier Shop:
        Shop shopToUpdate = shopService.findById(34); 
        shopToUpdate.setName("New Shop Name");
        shopToUpdate.setDescription("New Shop Description");
        shopToUpdate.setEmail("newemail@example.com");
        shopToUpdate.setUser_s("esemjdid");
        shopService.update(shopToUpdate);
       */
       /*
       //modifer product :
                 Product productToUpdate = productService.findById(42);
                 productToUpdate.setName("New Product Name");
                 productToUpdate.setPrice((float) 15.0);
                 productToUpdate.setType("woman");
                 productToUpdate.setStock(20);
                 productService.update(productToUpdate);
       */

      
       
       

        }
}
    

