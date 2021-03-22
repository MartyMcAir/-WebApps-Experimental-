package com.repositories;

import com.model.Foo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFooRepository extends JpaRepository<Foo, Long> {
    
}
