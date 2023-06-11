package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.ProductDTO;
import org.acme.entity.ProductEntity;
import org.acme.repository.ProductRepository;
import org.hibernate.dialect.function.PostgreSQLTruncRoundFunction;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    public List<ProductDTO> getAllProducts(){
        List<ProductDTO> productsDTO = new ArrayList<>();
        productRepository.findAll().stream().forEach(item ->{
            productsDTO.add(mapProductEntityToDTO(item));
        });
        return productsDTO;
    }

    public ProductDTO getProductById(long id){
        return mapProductEntityToDTO(productRepository.findById(id));
    }

    public void createProduct(ProductDTO productDTO){
        productRepository.persist(mapProductDTOToEntity(productDTO));
    }

    public void changeProduct(long id, ProductDTO productDTO){
        ProductEntity productEntity = productRepository.findById(id);
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setCategory(productDTO.getCategory());
        productEntity.setName(productDTO.getName());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setModel(productDTO.getModel());
        productRepository.persist(productEntity);
    }

    public void deleteProduct(long id){
        productRepository.deleteById(id);
    }

    private ProductEntity mapProductDTOToEntity(ProductDTO productDTO){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategory(productDTO.getCategory());
        productEntity.setName(productDTO.getName());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setModel(productDTO.getModel());
        productEntity.setDescription(productDTO.getDescription());
        return productEntity;
    }
    private ProductDTO mapProductEntityToDTO(ProductEntity productEntity){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(productEntity.getName());
        productDTO.setCategory(productEntity.getCategory());
        productDTO.setPrice(productEntity.getPrice());
        productDTO.setModel(productEntity.getModel());
        productDTO.setDescription(productEntity.getDescription());
        return productDTO;
    }
}
