package com.repositories;

import com.model.XBiodataModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface XPelamarPagingRepo extends PagingAndSortingRepository<XBiodataModel, Long> {

    public Page<XBiodataModel> findByfullname(String fullname, Pageable pageable);

    @Query(value = "SELECT p FROM XBiodataModel p WHERE LOWER(p.fullname) LIKE CONCAT('%',LOWER(:nama),'%')")
    public Page<XBiodataModel> search(@Param("nama") String nama, Pageable pageable);
}