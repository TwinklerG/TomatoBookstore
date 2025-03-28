package com.tomatobookstore.tomatobookstore.controller;

import com.tomatobookstore.tomatobookstore.service.ProductService;
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
        return ResultVO.buildSuccess(productService.getProducts(),null);
    }

    @GetMapping("/{productId}")
    public ResultVO<ProductVO> getProductDetail(@PathVariable String productId) throws Exception {
        return ResultVO.buildSuccess(productService.getProductById(productId),null);
    }

    @PutMapping("")
    public ResultVO<String> update(@RequestBody ProductVO productVO) throws Exception {
        productService.updateProduct(productVO);
        return ResultVO.buildSuccess("更新成功",null);
    }

    @PostMapping("")
    public ResultVO<ProductVO> add(@RequestBody ProductVO productVO) throws Exception {
        return ResultVO.buildSuccess(productService.createProduct(productVO),null);
    }

    @DeleteMapping("/{productId}")
    public ResultVO<String> deleteProduct(@PathVariable String productId) throws Exception {
        productService.deleteProduct(productId);
        return ResultVO.buildSuccess("删除成功",null);
    }
}
