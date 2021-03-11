
package futrashapi.futrashapiproject.flow_handle.controller;



import futrashapi.futrashapiproject.flow_handle.model.Chart;
import futrashapi.futrashapiproject.flow_handle.model.Item;
import futrashapi.futrashapiproject.flow_handle.repository.ChartRepository;
import futrashapi.futrashapiproject.flow_handle.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("chart")
public class ChartController {

    private final ChartRepository chartRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public ChartController(ChartRepository chartRepository,ItemRepository itemRepository) {
        this.chartRepository = chartRepository;
        this.itemRepository = itemRepository;
    }

    @PostMapping
    public ResponseEntity<Chart> create(@RequestBody @Valid Chart chart) {
        Optional<Item> optionalLibrary = itemRepository.findById(chart.getItem().getId());
        if (!optionalLibrary.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        chart.setItem(optionalLibrary.get());

        Chart savedChart = chartRepository.save(chart);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedChart.getId()).toUri();

        return ResponseEntity.created(location).body(savedChart);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Chart> update(@RequestBody @Valid Chart chart, @PathVariable Long id) {
        Optional<Item> optionalLibrary = itemRepository.findById(chart.getItem().getId());
        if (!optionalLibrary.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Chart> optionalChart = chartRepository.findById(id);
        if (!optionalChart.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        chart.setItem(optionalLibrary.get());
        chart.setId(optionalChart.get().getId());
        chartRepository.save(chart);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Chart> delete(@PathVariable Long id) {
        Optional<Chart> optionalChart = chartRepository.findById(id);
        if (!optionalChart.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        chartRepository.delete(optionalChart.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<Chart>> getAll(Pageable pageable) {
        return ResponseEntity.ok(chartRepository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chart> getById(@PathVariable Long id) {
        Optional<Chart> optionalBook = chartRepository.findById(id);
        if (!optionalBook.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalBook.get());
    }
}


