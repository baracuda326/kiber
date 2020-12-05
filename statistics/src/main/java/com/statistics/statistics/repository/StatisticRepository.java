package com.statistics.statistics.repository;

import com.statistics.statistics.models.Statistic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface StatisticRepository extends ElasticsearchRepository<Statistic,String> {
}
