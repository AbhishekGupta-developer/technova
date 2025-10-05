package com.myorganisation.technova.repository;

import com.myorganisation.technova.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
