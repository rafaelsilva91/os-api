package com.project.os.model.repositories;

import com.project.os.model.entities.Os;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOsRepository extends JpaRepository<Os, Integer> {

}
