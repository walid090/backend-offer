package com.sesame.backend_dashbord.controller;

import com.sesame.backend_dashbord.entity.*;
import com.sesame.backend_dashbord.service.DashbordIMPL;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Dashbord")
@AllArgsConstructor
public class DashbordController {

    @Autowired
    DashbordIMPL dashboard;

    @PostMapping("/Auth")
    public boolean Autehtification(@RequestBody User user){
        return dashboard.Autehtification(user);
    }

    @PostMapping("/AddProduct")
    public Product AddProduct(@RequestBody Product product){
        return dashboard.AddProduct(product);
    }
    @DeleteMapping("/DeleteProduct/{id}")
    public void DeleteProduct(@PathVariable("id") int id){
        dashboard.DeleteProduct(id);
    }
    @PutMapping("/UpdateProduct")
    public Product UpdateProduct(@RequestBody Product product){
        return dashboard.UpdateProduct(product);
    }

    @GetMapping("/GetAllProduct")
    public List<Product> GetAllProduct(){
        return dashboard.GetAllProduct();
    }

    @GetMapping("/GetProductById/{id}")
    public Product GetProductById(@PathVariable("id") int id){
        return dashboard.GetProductById(id);
    }

    @GetMapping("/GetStockByCategory/{category}")
    public Integer GetStockByCategory(@PathVariable("category") String category){
        return dashboard.GetStockByCategory(category);
    }

    @GetMapping("/GetRevenueByMonth/{month}")
    public float GetRevenueByMonth(@PathVariable("month") String month){
        return dashboard.GetRevenueByMonth(month);
    }

    /* customer parte */
    @GetMapping("/GetCustomerById/{id}")
    public Customer GetCustomerById(@PathVariable("id") int id){
        return dashboard.GetCustomerById(id);
    }
    @PostMapping("/AddCustomer")
    public Customer AddCustomer(@RequestBody Customer customer){
        return dashboard.AddCustomer(customer);
    }
    @PutMapping("/UpdateCustomer")
    public Customer UpdateCustomer(@RequestBody Customer customer){
        return dashboard.UpdateCustomer(customer);
    }
    @DeleteMapping("/DeleteCustomer/{id}")
    public void DeleteCustomer(@PathVariable("id") int id){
        dashboard.DeleteCustomer(id);
    }
    @GetMapping("/GetAllCustomer")
    public Iterable<Customer> GetAllCustomer(){
        return dashboard.GetAllCustomer();
    }

    /* supplier part */
    @PostMapping("/AddSupplier")
    public Supplier AddSupplier(@RequestBody Supplier supplier){
        return dashboard.AddSupplier(supplier);
    }
    @PutMapping("/UpdateSupplier")
    public Supplier UpdateSupplier(@RequestBody Supplier supplier){
        return dashboard.UpdateSupplier(supplier);
    }
    @GetMapping("/GetAllSupplier")
    public Iterable<Supplier> GetAllSupplier(){
        return dashboard.GetAllSupplier();
    }
    @GetMapping("/GetSupplierById/{id}")
    public Supplier GetSupplierById(@PathVariable("id") int id){
        return dashboard.GetSupplierById(id);
    }
    @DeleteMapping("/DeleteSupplier/{id}")
    public void DeleteSupplier(@PathVariable("id") int id){
        dashboard.DeleteSupplier(id);
    }

    /* stock Management */
    @PostMapping("/AddStock")
    public Stock AddStock(@RequestBody  Object stock){
        return dashboard.AddStock(stock);
    }

    @GetMapping("/GetStockByProductIdAndType/{id}/{type}")
    public List<Stock> GetStockByProductIdAndType(@PathVariable("id") int id, @PathVariable("type") String type){
        return dashboard.GetStockByProductIdAndType(id,type);
    }




}

