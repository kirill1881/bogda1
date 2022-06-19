package com.example.bogdashka.repos;

import com.example.bogdashka.models.DataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepo extends JpaRepository<DataModel, Long> {
}
