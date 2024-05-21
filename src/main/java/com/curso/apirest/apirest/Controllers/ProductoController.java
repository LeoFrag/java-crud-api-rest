package com.curso.apirest.apirest.Controllers;

import com.curso.apirest.apirest.Entities.Producto;
import com.curso.apirest.apirest.Repositories.ProductoRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired //Sabe la inyeccion del repository
    private ProductoRepository productoRepository;

    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos() {

        List<Producto> productos = productoRepository.findAll();
        return ResponseEntity.ok(productos);

    }
    @GetMapping("/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id) {
        return productoRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID: " + id));
    }
    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        Producto productoAlmacenado = productoRepository.save(producto);
        return new ResponseEntity<>(productoAlmacenado, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto detalleProducto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID: " + id));

        producto.setNombre(detalleProducto.getNombre());
        producto.setPrecio(detalleProducto.getPrecio());

        return productoRepository.save(producto);
    }

    @DeleteMapping("/{id}")
    public String borrarProducto(@PathVariable Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el producto con ID" + id));

        productoRepository.delete(producto);
        return "El producto con ID " + id + "Fue eliminado";

    }

}
