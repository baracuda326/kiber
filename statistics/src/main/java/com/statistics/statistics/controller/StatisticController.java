package com.statistics.statistics.controller;

import com.statistics.statistics.models.Statistic;
import com.statistics.statistics.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class StatisticController {
    private StatisticService statisticService;

    @Autowired
    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("/add")
    Statistic add(){
        return statisticService.add(Statistic.builder().count(1).build());
    }
}
