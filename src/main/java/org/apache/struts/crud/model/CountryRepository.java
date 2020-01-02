package org.apache.struts.crud.model;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CountryRepository extends PagingAndSortingRepository<CountryEntity,Integer> {

    CountryEntity findByCountryId(String countryId);
}
