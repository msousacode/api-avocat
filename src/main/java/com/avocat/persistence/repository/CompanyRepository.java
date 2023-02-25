package com.avocat.persistence.repository;

import com.avocat.persistence.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {

    Optional<Company> findByIdAndActiveTrue(UUID companyId);

    Page<Company> findAllByCustomer_Id(UUID branchOfficeId, Pageable pageable);
}
