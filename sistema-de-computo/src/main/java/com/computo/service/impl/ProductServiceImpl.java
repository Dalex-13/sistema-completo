package com.computo.service.impl;

import com.computo.dao.ProductDao;
import com.computo.dto.ProductDTO;
import com.computo.model.Product;
import com.computo.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductDao dao;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = maperEntity(productDTO);

        Product newProduct = dao.save(product);

        ProductDTO productResponse = maperDTO(newProduct);
        return productResponse;
    }

    @Override
    public List<ProductDTO> listAllProduct() {
        List<Product> products = dao.findAll();
        //Pageable pageable = PageRequest.of(2,1);
        return products.stream().map(p -> maperDTO(p)).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> listByPageable(int numeroDePagina, int medidaDePagina) {
        Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina);
        Page<Product> productos = dao.findAll(pageable);

        List<Product> listarAPublicar = productos.getContent();
        return listarAPublicar.stream().map(p ->
                maperDTO(p)).collect(Collectors.toList());
    }


    @Override
    public ProductDTO listProductById(Long id) {
        Product product = dao.findById(id).orElseThrow(() -> new ResolutionException("no se encuentra el producto con el id:"+id));
        return maperDTO(product);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = dao.findById(id).orElseThrow(() -> new ResolutionException("no se encuentra el producto con el id:"+id));
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setAmount(productDTO.getAmount());
        product.setPrice(productDTO.getPrice());

        Product productUpdate = dao.save(product);
        return maperDTO(productUpdate);
    }

    @Override
    public void deleteProductById(Long id) {
        Product product = dao.findById(id).orElseThrow(() -> new ResolutionException("no se encuentra el producto con el id:"+id));
        dao.delete(product);
    }


    private ProductDTO maperDTO(Product product){

        //utilizamos la libreria del modelMapper
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);

/*
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setAmount(product.getAmount());
        productDTO.setPrice(product.getPrice());
*/
        return productDTO;
    }

    private Product maperEntity(ProductDTO productDTO){
        Product product = modelMapper.map(productDTO, Product.class);
/*
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setAmount(productDTO.getAmount());
        product.setPrice(productDTO.getPrice());
*/
        return product;
    }






}
