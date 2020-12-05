package com.statistics.statistics.service.impl;

import com.statistics.statistics.models.Statistic;
import com.statistics.statistics.repository.CountriesRepository;
import com.statistics.statistics.repository.StatisticRepository;
import com.statistics.statistics.service.StatisticService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StatisticServiceImpl implements StatisticService {
    private StatisticRepository statisticRepository;
    private CountriesRepository countriesRepository;
    private Map<String, List<String>> countries;

    public StatisticServiceImpl(StatisticRepository statisticRepository,
                                CountriesRepository countriesRepository) {
        this.statisticRepository = statisticRepository;
        this.countriesRepository = countriesRepository;
    }

    @Override
    public Statistic add(Statistic statistic) {
        Statistic statistic1 = statisticRepository.save(statistic);
        statisticRepository.findAll().forEach(System.out::println);
//        for (String country : countries.keySet()){
//            countriesRepository.save(Country.builder()
//                    .country(country)
//                    .cities(countries.get(country))
//                    .build());
//        }
//        System.out.println(countriesRepository.findCountryByCities("Bariloche"));
        return statistic1;
    }

    //****************************************************************************
//    @PostConstruct
//    public void init() throws IOException {
//        this.countries = new TreeMap<>();
//        this.countries = new ObjectMapper().readValue(Files.newBufferedReader(Paths.get("countries.json"))
//                , new TypeReference<Map<String
//                        , List<String>>>() {
//                });
//    }
    //****************************************************************************
}
