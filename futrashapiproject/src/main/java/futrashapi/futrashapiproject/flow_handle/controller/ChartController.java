package futrashapi.futrashapiproject.flow_handle.controller;


import futrashapi.futrashapiproject.flow_handle.model.Chart;
import futrashapi.futrashapiproject.flow_handle.repository.ChartRepository;
import futrashapi.futrashapiproject.flow_handle.repository.ItemRepository;
import futrashapi.futrashapiproject.flow_handle.services.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class ChartController {

    private ChartService chartService;
    private ChartRepository chartRepository;

    public ChartController(ChartService chartService, ChartRepository chartRepository) {
        this.chartService = chartService;
        this.chartRepository = chartRepository;
    }

    @PostMapping("/charts/create")
    public ResponseEntity<Object> createChart(@RequestBody Chart chart) {
        return  chartService.addChart(chart);
    }
    @DeleteMapping("/charts/delete/{id}")
    public ResponseEntity<Object> deleteCharts(@PathVariable Long id) {
        return chartService.deleteChart(id);
    }
    @GetMapping("/charts/details/{id}")
    public Chart getChart(@PathVariable Long id) {
        if(chartRepository.findById(id).isPresent())
            return chartRepository.findById(id).get();
        else return null;
    }
    @GetMapping("/charts/all")
    public List<Chart> getCharts() {
        return chartRepository.findAll();
    }


}
