package com.example.bogdashka.repos;

import com.example.bogdashka.models.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepo extends JpaRepository<PaymentModel, Long> {
    List<PaymentModel> getPaymentModelsByIfDone(boolean ifDone);
    List<PaymentModel> getPaymentModelsByName(String name);
}
