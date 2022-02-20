package edu.sru.bayne.DistanceAndDirections.repository;

import org.springframework.data.repository.CrudRepository;
import edu.sru.bayne.DistanceAndDirections.domain.Search;


/*
 Crud Repo for use with database.
 may replace or change to work with hash table or sql
 */
public interface SearchRepository extends CrudRepository<Search, Long> {}