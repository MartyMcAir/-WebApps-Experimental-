package com.repositories;

import com.model.XBiodataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface XBiodataRepo extends JpaRepository<XBiodataModel, Long> {

//	@Query(value="select max(id) from XBiodataModel")
//	public String getMaxKode();

    public List<XBiodataModel> findByfullname(String fullname);

    @Query(value = "SELECT p FROM XBiodataModel p WHERE LOWER(p.fullname) LIKE CONCAT('%',LOWER(:nama),'%')")
    public List<XBiodataModel> search(@Param("nama") String nama);
}