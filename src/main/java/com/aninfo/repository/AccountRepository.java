package com.aninfo.repository;

import com.aninfo.model.Account;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findAccountByCbu(Long cbu);

    @Override
    List<Account> findAll();

}
