package org.apache.struts.crud.model;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonRepository extends PagingAndSortingRepository<PersonEntity,Integer>
{
}
