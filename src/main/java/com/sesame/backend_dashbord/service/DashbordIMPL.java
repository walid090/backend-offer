package com.sesame.backend_dashbord.service;

import com.sesame.backend_dashbord.entity.*;
import com.sesame.backend_dashbord.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class DashbordIMPL implements IDashbord{
    @Autowired
    IuserRepository userRepository;
    @Autowired
    IproductRepository productRepository;
    @Autowired
    IcustomerRepository customerRepository;
    @Autowired
    IsupplierRepository supplierRepository;
    @Autowired
    IStockRepository stockRepository;



    @Override
    public boolean Autehtification(User user) {
        List<User> list= (List<User>) userRepository.findAll();
        for (User u : list) {
            System.out.println(u.getUsername());
            if (u.getUsername().equals( user.getUsername()) && u.getPassword().equals(user.getPassword()) ) {
                System.out.println("heloo");
                return true;
            }
        }
        return false;
    }

    @Override
    public Product AddProduct(Product product) {
        System.out.println(product.getCategory());
        return productRepository.save(product);
    }

    @Override
    public void DeleteProduct(int id) {
         productRepository.deleteById(id);
    }

    @Override
    public Product UpdateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> GetAllProduct() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product GetProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Integer GetStockByCategory(String category) {
        Iterable<Product> all=productRepository.findAll();
        int res=0;
        for (Product p : all) {
            if (p.getCategory().equals(category)) {
                res=res+p.getQuantity();
            }
        }
        return res;
    }

    @Override
    public float GetRevenueByMonth(String month) {
        float revenue = 0;
        Iterable<Product> all=productRepository.findAll();
        for (Product p : all) {
            if (p.getDateAdded().getMonth().name().toLowerCase().equals(month.toLowerCase())) {
                        revenue=revenue+p.getPrice()*p.getQuantity();
            }
        }
       return revenue;
    }

    @Override
    public Customer GetCustomerById(int id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer AddCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer UpdateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void DeleteCustomer(int id) {
         customerRepository.deleteById(id);
    }

    @Override
    public Iterable<Customer> GetAllCustomer() {
        return (Iterable<Customer>) customerRepository.findAll();
    }

    @Override
    public Supplier AddSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier UpdateSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public Iterable<Supplier> GetAllSupplier() {
        return (Iterable<Supplier>) supplierRepository.findAll();
    }

    @Override
    public Supplier GetSupplierById(int id) {
        return supplierRepository.findById(id).orElse(null);
    }

    @Override
    public void DeleteSupplier(int id) {
            supplierRepository.deleteById(id);
    }

    @Override
    public Stock AddStock(Object stock) {
        if (stock instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) stock;

            String movement_type = (String) map.get("movement_type");
            Integer quantity = (Integer) map.get("quantity");
            Integer id_prodcut= (Integer) map.get("id_prodcut");
            Integer id_customer=map.get("id_customer")!= null ? Integer.valueOf(map.get("id_customer").toString()) : null;
            Integer id_supplier = map.get("id_supplier")!= null ? Integer.valueOf(map.get("id_supplier").toString()) : null;;
            System.out.println(movement_type+" "+ quantity+" " + id_prodcut+" "+" "+id_supplier+" "+id_customer);

            Stock stock1=new Stock();

            stock1.setQuantity(quantity);
            Product product = productRepository.findById(id_prodcut).orElse(null);
            List<Product> products = product != null ? Collections.singletonList(product) : Collections.emptyList();
            stock1.setProducts(products);

            if(id_supplier!=null){
                Supplier supplier = supplierRepository.findById(id_supplier).orElse(null);
                List<Supplier> suppliers = supplier != null ? Collections.singletonList(supplier) : Collections.emptyList();
                stock1.setSuppliers(suppliers);
            }
            if(id_customer!=null){
                Customer customer = customerRepository.findById(id_customer).orElse(null);
                List<Customer> customers = customer != null ? Collections.singletonList(customer) : Collections.emptyList();
                stock1.setCustomers(customers);
            }
            if(movement_type.equals("sell")){
                stock1.setMovement_type(Type_Mov.sell);
            }
            if(movement_type.equals("buy")){
                stock1.setMovement_type(Type_Mov.buy);
            }
            updateProductQuantity(id_prodcut);

            return stockRepository.save(stock1);
        }

        return null;
    }

    @Override
    public List<Stock> GetStockByProductIdAndType(int id,String type) {
          Product p =productRepository.findById(id).orElse(null);
        List<Stock> res=new ArrayList<Stock>();
          if(type.equals("All")){
              res.addAll(p.getStocks());
          }
          if(type.equals("Sell")){
              for (Stock s:p.getStocks()) {
                  if(s.getMovement_type().equals(Type_Mov.sell)){
                      res.add(s);
                  }

              }
          }
          if(type.equals("Buy")){
              for (Stock s:p.getStocks()) {
                  if(s.getMovement_type().equals(Type_Mov.buy)){
                      res.add(s);
                  }

              }
          }
          return res;
    }
    public void updateProductQuantity(int productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        int totalQuantity = 0;

        for (Stock stock : product.getStocks()) {
            if ("buy".equals(stock.getMovement_type().toString())) {
                totalQuantity += stock.getQuantity(); // Add quantity if buy
            } else if ("sell".equals(stock.getMovement_type().toString())) {
                totalQuantity -= stock.getQuantity(); // Subtract quantity if sell
            }
        }

        product.setQuantity(totalQuantity);
        productRepository.save(product);
    }


}
