package com.pezesha.myproject.repository;

import com.pezesha.myproject.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
