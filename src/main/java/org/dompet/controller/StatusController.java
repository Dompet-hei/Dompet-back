package org.dompet.controller;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.dompet.model.Status;
import org.dompet.service.StatusService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/status")
public class StatusController {
  private final StatusService statusService;

  @GetMapping
  public List<Status> findAllStatus() {
    return statusService.findAll();
  }

  @GetMapping("/{id}")
  public Optional<Status> findStatusById(@PathVariable String id) {
    return Optional.ofNullable(statusService.findById(id));
  }

  @PutMapping
  public Status saveStatus(@RequestBody Status status) {
    return statusService.save(status);
  }

  @DeleteMapping("/{id}")
  public void deleteStatusById(@PathVariable String id) {
    statusService.deleteById(id);
  }
}
