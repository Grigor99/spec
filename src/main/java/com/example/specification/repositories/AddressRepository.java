package com.example.specification.repositories;

import com.example.specification.domains.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long>, JpaSpecificationExecutor<Address> {
}
