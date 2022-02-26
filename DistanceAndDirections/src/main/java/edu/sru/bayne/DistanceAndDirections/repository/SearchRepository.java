package edu.sru.bayne.DistanceAndDirections.repository;

/**
 * Repository implementation extending from CRUD repository.
 * It is used to store Search objects (Search.java) and their data.
 * @author Gregory Bayne
 * 
 */
import org.springframework.data.repository.CrudRepository;
import edu.sru.bayne.DistanceAndDirections.domain.Search;


/*
 Crud Repo for use with database.
 may replace or change to work with hash table or sql
 */
public interface SearchRepository extends CrudRepository<Search, Long> {}