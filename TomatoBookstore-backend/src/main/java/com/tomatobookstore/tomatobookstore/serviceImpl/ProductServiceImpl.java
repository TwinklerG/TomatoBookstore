package com.tomatobookstore.tomatobookstore.serviceImpl;

import com.tomatobookstore.tomatobookstore.exception.TomatoBookstoreException;
import com.tomatobookstore.tomatobookstore.po.Product;
import com.tomatobookstore.tomatobookstore.po.Specification;
import com.tomatobookstore.tomatobookstore.po.Stockpile;
import com.tomatobookstore.tomatobookstore.repository.ProductRepository;
import com.tomatobookstore.tomatobookstore.repository.SpecificationRepository;
import com.tomatobookstore.tomatobookstore.repository.StockpileRepository;
import com.tomatobookstore.tomatobookstore.service.ProductService;
import com.tomatobookstore.tomatobookstore.vo.ProductVO;
import com.tomatobookstore.tomatobookstore.vo.SpecificationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SpecificationRepository specificationRepository;

    @Autowired
    private StockpileRepository stockpileRepository;

    @Override
    public List<ProductVO> getProducts() {
        List<Product>productsPO=productRepository.findAll();
        List<ProductVO>ProductVOList=new ArrayList<>();

        for(Product i:productsPO){
            Set<Specification>tmp=specificationRepository.findByProductId(i.getProductId());
            Set<SpecificationVO>tmp2=new HashSet<>();
            for(Specification j:tmp){
                tmp2.add(j.toVO());
            }
            ProductVOList.add(i.toVO(tmp2));
        }
        return ProductVOList;
    }

    @Override
    public ProductVO getProductById(String productId) throws Exception {
        try {
            Set<Specification>tmp=specificationRepository.findByProductId(Integer.valueOf(productId));
            Set<SpecificationVO>tmp2=new HashSet<>();
            for(Specification i:tmp)
                tmp2.add(i.toVO());
            return productRepository.findByProductId(Integer.valueOf(productId)).toVO(tmp2);
        } catch (Exception e) {
            throw TomatoBookstoreException.productNotExisted();
        }

    }

    @Override
    public void updateProduct(ProductVO productVO) throws Exception {
        try {
            if(productVO.getId()==null||productRepository.findByProductId(Integer.valueOf(productVO.getId()))==null)
                throw TomatoBookstoreException.productNotExisted();
            Product product=productRepository.findByProductId(Integer.valueOf(productVO.getId()));
            product.setTitle(productVO.getTitle());
            product.setPrice(productVO.getPrice());
            product.setRate(productVO.getRate());
            product.setDescription(productVO.getDescription());
            product.setCover(productVO.getCover());
            product.setDetail(productVO.getDetail());
            specificationRepository.deleteByProductId(product.getProductId());
            if(productVO.getSpecifications()!=null)
                for(SpecificationVO i:productVO.getSpecifications())
                    specificationRepository.save(i.toPO(product.getProductId()));
            productRepository.save(product);
        } catch (Exception e) {
            throw TomatoBookstoreException.productNotExisted();
        }
    }

    @Override
    public ProductVO createProduct(ProductVO productVO) throws Exception {
        if(productVO.getId()!=null)
            throw TomatoBookstoreException.noIdAllowed();
        Product product=productVO.toPO();
        productRepository.saveAndFlush(product);
        if(productVO.getSpecifications()!=null)
            for(SpecificationVO i:productVO.getSpecifications())
                specificationRepository.save(i.toPO(product.getProductId()));
        productVO.setId(String.valueOf(product.getProductId()));
        Stockpile stockpile=new Stockpile();
        stockpile.setProductId(product.getProductId());
        stockpile.setAmount(0);
        stockpile.setFrozen(0);
        stockpileRepository.save(stockpile);
        return productVO;
    }

    @Override
    public void deleteProduct(String productId) throws Exception {
        try {
            if(productRepository.findByProductId(Integer.valueOf(productId))==null)
                throw TomatoBookstoreException.productNotExisted();
            productRepository.deleteById(Integer.valueOf(productId));
            specificationRepository.deleteByProductId(Integer.valueOf(productId));
            stockpileRepository.deleteByProductId(Integer.valueOf(productId));
        } catch (Exception e) {
            throw TomatoBookstoreException.productNotExisted();
        }
    }
}
