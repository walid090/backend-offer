package com.sesame.backend_dashbord.service;


import com.sesame.backend_dashbord.entity.*;

import java.util.List;

public interface IDashbord {
    public boolean Autehtification(User user);
    public Product AddProduct(Product product);
    public void DeleteProduct(int id);
    public Product UpdateProduct(Product product);
    public Iterable<Product> GetAllProduct();
    public Product GetProductById(int id);
    public Integer GetStockByCategory (String category);
    public float GetRevenueByMonth(String month);
    public Customer GetCustomerById(int id);
    public Customer AddCustomer(Customer customer);
    public Customer UpdateCustomer(Customer customer);
    public void DeleteCustomer(int id);
    public Iterable<Customer> GetAllCustomer();
    public Supplier AddSupplier(Supplier supplier);
    public Supplier UpdateSupplier(Supplier supplier);
    public Iterable<Supplier> GetAllSupplier();
    public Supplier GetSupplierById(int id);
    public void DeleteSupplier(int id);
    public Stock AddStock(Object stock);
    public List<Stock> GetStockByProductIdAndType(int id,String type);
}
