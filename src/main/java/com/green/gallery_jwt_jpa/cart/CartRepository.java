package com.green.gallery_jwt_jpa.cart;

import com.green.gallery_jwt_jpa.entity.Carts;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Carts, Long> {
}