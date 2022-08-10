package io.dbops.service;

import io.dbops.model.Product;
import io.dbops.model.User;
import io.dbops.model.mapped.MappedProduct;
import io.dbops.repository.ProductRepository;
import io.dbops.repository.UserRepository;
import io.dbops.util.model.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductImpl extends DBOpsService implements ProductService {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;


    private Product getProduct(String productId){
        Product product = productRepository.getProductByProductId(productId);
        if (product == null) {
            throw new EntityNotFoundException("No product matches productId provided");
        }

        return product;
    }

    private User getUser(String userId){
        // find the user that's logged in
        User user = userRepository.findByUserId(userId);

        if (user == null) {
            throw new EntityNotFoundException("No user matches id provided");
        }

        return user;
    }

    // user clicked product
    @Transactional
    public void click(String productId, String userId){

        // make sure it doesn't exist
        if(!productRepository.hasBeenClicked(userId,productId)){
            Product product = getProduct(productId);
            User user   = getUser(userId);
            user.clicked(product);
            // save the user
            userRepository.save(user);
        }

    }

    // user liked product
    @Transactional
    public void like(String productId, String userId){
        // make sure it doesn't exist
        if(!productRepository.Isliked(userId,productId)){
            Product product = getProduct(productId);
            User user   = getUser(userId);

            logger.info(user.getUserId());
            logger.info(product.getProductId());

            user.likeProduct(product);
            // save the user
            userRepository.save(user);
        }

    }
    // user unliked product
    @Transactional
    public void unlike(String productId, String userId){

        // make sure it exists
        if(productRepository.Isliked(userId,productId)){
            Product product = getProduct(productId);
            User user   = getUser(userId);
            user.unlikeProduct(product);
            // save the user
            userRepository.save(user);
        }
    }

    public MappedProduct getProductForView(String productId) {

        MappedProduct product = productRepository.getProductByProductIdForView(productId);
        return product;
    }
}
