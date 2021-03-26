package futrashapi.futrashapiproject.flow_handle.controller;

import futrashapi.futrashapiproject.flow_handle.exception.ResourceNotFoundException;
import futrashapi.futrashapiproject.flow_handle.model.Chart;
import futrashapi.futrashapiproject.flow_handle.repository.ChartRepository;
import futrashapi.futrashapiproject.flow_handle.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ChartController {

    @Autowired
    private ChartRepository chartRepository;

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/items/{itemId}/charts")
    public Page<Chart> getAllChartByItemId(@PathVariable (value = "itemId") Long itemId,
                                              Pageable pageable) {
        return chartRepository.findByItemId(itemId, pageable);
    }

    @PostMapping("/items/{itemId}/charts")
    public Chart createChart(@PathVariable (value = "itemId") Long itemId,
                                 @Valid @RequestBody Chart chart) {
        return itemRepository.findById(itemId).map(item -> {
            chart.setItem(item);
            return chartRepository.save(chart);
        }).orElseThrow(() -> new ResourceNotFoundException("ItemId " + itemId + " not found"));
    }

   //cart gabisa di edit
    @DeleteMapping("/items/{itemId}/charts/{chartId}")
    public ResponseEntity<?> deleteChart(@PathVariable (value = "itemId") Long itemId,
                                           @PathVariable (value = "chartId") Long chartId) {
        return chartRepository.findByIdAndItemId(chartId, itemId).map(chart -> {
            chartRepository.delete(chart);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + chartId + " and postId " +itemId));
    }


}
