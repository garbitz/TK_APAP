package com.apapedia.order.repository;

import jakarta.transaction.Transactional;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apapedia.order.model.CartItem;

@Repository
@Transactional
public interface CartItemDb extends JpaRepository<CartItem, UUID> {

}
