package com.example.bogdashka.repos;

import com.example.bogdashka.models.BuyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyRepo extends JpaRepository<BuyModel, Long> {
}
