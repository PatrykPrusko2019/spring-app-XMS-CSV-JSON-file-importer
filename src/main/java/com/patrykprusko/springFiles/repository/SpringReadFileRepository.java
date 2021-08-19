package com.patrykprusko.springFiles.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.patrykprusko.springFiles.model.Policy;

@Repository
public interface SpringReadFileRepository extends CrudRepository<Policy, Long>{

}
