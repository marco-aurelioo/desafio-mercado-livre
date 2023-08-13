package com.dev.mercadolivre.service;

import com.dev.mercadolivre.model.ProdutoModel;
import com.dev.mercadolivre.model.UserModel;
import com.dev.mercadolivre.repository.CategoryRepository;
import com.dev.mercadolivre.repository.ProdutoRepository;
import com.dev.mercadolivre.repository.UserRepository;
import com.dev.mercadolivre.repository.entity.ProdutoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ProdutoService {

    Logger logger = Logger.getLogger(ProdutoService.class.getName());

    private  ProdutoRepository produtoRepository;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;
    private FileStorageService fileStorageService;

    ProdutoService(@Autowired ProdutoRepository produtoRepository,
                   @Autowired CategoryRepository categoryRepository,
                   @Autowired UserRepository userRepository,
                   @Autowired FileStorageService fileStorageService) {
        this.produtoRepository = produtoRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.fileStorageService = fileStorageService;
    }


    public ProdutoModel createProduto( ProdutoModel produto) {
        var produtoEntity = produtoRepository.save(
                produto.toEntity(
                        this.userRepository,
                        this.categoryRepository));
        return produtoEntity.toModel(categoryRepository);
    }

    public void upload(MultipartFile[] file, UserDetails user, Integer produtoId) {
        ProdutoEntity produtoEntity = produtoRepository.findById(produtoId).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        if(produtoEntity.getUsuario().getUsername().equals(user.getUsername())){
            List<String> imgs = fileStorageService.uploadFile(produtoEntity.getId(), file);
            produtoEntity.setImages(imgs);
            produtoRepository.save(produtoEntity);
        }else {
            throw new RuntimeException("Você não é o dono do produto.");
        }
    }

}
