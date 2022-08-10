package io.dbops.model.mapped;

import io.dbops.model.Category;
import io.dbops.model.Location;
import io.dbops.model.Product;
import io.dbops.model.Review;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.Set;

@QueryResult
public class MappedProduct {
    @Getter @Setter Product product;
    @Getter @Setter Set<Category> categories;
    @Getter @Setter Set<Review> reviews;
    @Getter @Setter Set<Location> locations;
    @Getter @Setter Set<Product> similar;

}
