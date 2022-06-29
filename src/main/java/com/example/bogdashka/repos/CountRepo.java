package com.example.bogdashka.repos;

import com.example.bogdashka.helper.Cont;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface CountRepo extends JpaRepository<Cont, Long> {
}
