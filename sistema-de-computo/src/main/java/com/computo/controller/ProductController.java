package com.computo.controller;

import com.computo.dto.ProductDTO;
import com.computo.model.Product;
import com.computo.service.ProductService;
import com.computo.util.AppConstantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/producto")
public class ProductController {


    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService service;


    @PostMapping("/product")
    public ResponseEntity<ProductDTO> crearNuevoProducto(@Valid @RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(service.createProduct(productDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> listAll(){
        return new ResponseEntity<>(service.listAllProduct(), HttpStatus.OK);
    }


    @GetMapping("/buscar/{id}")
    public ResponseEntity<ProductDTO> buscarPorId(@PathVariable(name = "id") Long id){
        log.info("listando producto por controlador");
        return ResponseEntity.ok(service.listProductById(id));
    }

    @GetMapping("/paginado")
    public List<ProductDTO> listarPorPaginación(
            @RequestParam(defaultValue = AppConstantes.MEDIDA_DE_PAGINA_POR_DEFECTO) int numeroDePagina,
            @RequestParam(defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO) int medidaDePagina){
        return service.listByPageable(numeroDePagina, medidaDePagina);
    }

    @PutMapping("actualizar/{id}")
    public ResponseEntity<ProductDTO> actualizarProductoPorId(@PathVariable("id") Long id, @Valid @RequestBody ProductDTO productDTO){
        ProductDTO productResponse = service.updateProduct(id, productDTO);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @DeleteMapping("borrar/{id}")
    public ResponseEntity<String> eliminarProductPorId(@PathVariable("id") Long id){
        service.deleteProductById(id);
        return new ResponseEntity<>("Producto eliminado con exito", HttpStatus.OK);
    }


}
