package com.tomatobookstore.tomatobookstore.controller;

import com.tomatobookstore.tomatobookstore.service.ProductService;
import com.tomatobookstore.tomatobookstore.vo.MessageVO;
import com.tomatobookstore.tomatobookstore.vo.ProductVO;
import com.tomatobookstore.tomatobookstore.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("")
    public ResultVO<List<ProductVO>> getProducts() throws Exception {
        return ResultVO.buildSuccess(productService.getProducts());
    }

    @GetMapping("/{productId}")
    public ResultVO<ProductVO> getProductDetail(@PathVariable String productId) throws Exception {
        return ResultVO.buildSuccess(productService.getProductById(productId));
    }

    @PutMapping("")
    public ResultVO<MessageVO> update(@RequestBody ProductVO productVO) throws Exception {
        return ResultVO.buildSuccess(productService.updateProduct(productVO));
    }

    @PostMapping("")
    public ResultVO<ProductVO> add(@RequestBody ProductVO productVO) throws Exception {
        return ResultVO.buildSuccess(productService.createProduct(productVO));
    }

    @DeleteMapping("/{productId}")
    public ResultVO<MessageVO> deleteProduct(@PathVariable String productId) throws Exception {
        return ResultVO.buildSuccess(productService.deleteProduct(productId));
    }
}
