package com.computo.service;

import com.computo.dto.ProductDTO;
import com.computo.model.Product;

import java.util.List;

public interface ProductService {

    public ProductDTO createProduct(ProductDTO productDTO);

    public List<ProductDTO> listAllProduct();

    public List<ProductDTO> listByPageable(int numeroDePagina, int medidaDePagina);

    public ProductDTO listProductById(Long id);

    public ProductDTO updateProduct(Long id, ProductDTO productDTO);

    public void deleteProductById(Long id);


}
