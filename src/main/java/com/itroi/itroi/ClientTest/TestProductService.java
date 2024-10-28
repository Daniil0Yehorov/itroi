package com.itroi.itroi.ClientTest;
import com.itroi.itroi.ServiceInterfaces.ProductService;
import jakarta.xml.ws.Service;
import javax.xml.namespace.QName;
import java.net.URL;
import java.util.List;
import com.itroi.itroi.Model.Product;

public class TestProductService {
    public static void main(String[] args) {
        try {
            URL wsdlURL = new URL("http://localhost:8081/ws/products?wsdl");

            QName qname = new QName("http://ServiceImplementation.itroi.itroi.com/", "ProductServiceImplService");
            Service service = Service.create(wsdlURL, qname);

            ProductService productService = service.getPort(ProductService.class);

            Product newProduct = new Product(1, "Корм для собак", "......", 25.99, "Собача їжа", 9999);
            productService.addProduct(newProduct);
            System.out.println("Продукт додано: " + newProduct);

            List<Product> products = productService.getAllProducts();
            System.out.println("Всі продукти: " + products);

            Product retrievedProduct = productService.getProductById(1);
            System.out.println("Отриманий продукт: " + retrievedProduct);

            newProduct.setPrice(22.99);
            productService.updateProduct(newProduct);
            System.out.println("Продукт оновлено: " + newProduct);

            productService.deleteProduct(1);
            System.out.println("Продукт видалено: " + newProduct.getID());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}