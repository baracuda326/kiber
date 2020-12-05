package com.statistics.statistics.repository;

import com.statistics.statistics.models.Country;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CountriesRepository extends ElasticsearchRepository<Country, String> {
    Country findCountryByCities(String city);
}
