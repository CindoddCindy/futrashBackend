package futrashapi.futrashapiproject.flow_handle.controller;

import futrashapi.futrashapiproject.auth.repository.UserRepository;
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
@RequestMapping("/api/foodTrash/charts")
public class ChartController {

    @Autowired
    private ChartRepository chartRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/{userId}/charts")
    public Page<Chart> getAllChartByUserId(@RequestHeader("Authorization") String token,@PathVariable (value = "userId") Long userId,
                                              Pageable pageable) {
        return chartRepository.findByUserId(userId, pageable);
    }

    @PostMapping("/users/{userId}/charts")
    public Chart createChart(@RequestHeader("Authorization") String token,@PathVariable (value = "userId") Long userId,
                                 @Valid @RequestBody Chart chart) {
        return userRepository.findById(userId).map(user -> {
            chart.setUser(user);
            return chartRepository.save(chart);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }

   //cart gabisa di edit
    @DeleteMapping("/users/{userId}/charts/{chartId}")
    public ResponseEntity<?> deleteChart(@RequestHeader("Authorization") String token,@PathVariable (value = "userId") Long userId,
                                           @PathVariable (value = "chartId") Long chartId) {
        return chartRepository.findByIdAndUserId(chartId, userId).map(chart -> {
            chartRepository.delete(chart);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + chartId + " and userId " +userId));
    }


}
