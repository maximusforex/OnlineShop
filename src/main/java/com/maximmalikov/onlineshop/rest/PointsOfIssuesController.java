package com.maximmalikov.onlineshop.rest;

import com.maximmalikov.onlineshop.dto.PointsOfIssueDTO;
import com.maximmalikov.onlineshop.service.PointsOfIssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/points_of_issue")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PointsOfIssuesController {

    private final PointsOfIssueService pointsOfIssueService;

    @GetMapping
    public List<PointsOfIssueDTO> getAll() {
        return pointsOfIssueService.getAllPointsOfIssue();
    }

    @PostMapping
    public ResponseEntity<PointsOfIssueDTO> addPointOfIssue(@RequestBody PointsOfIssueDTO pointsOfIssueDTO) {
        PointsOfIssueDTO pointsOfIssueDTO1 = pointsOfIssueService.addPointOfIssue(pointsOfIssueDTO);
        return ResponseEntity.ok(pointsOfIssueDTO1);
    }

    @DeleteMapping("/{points_id}")
    public ResponseEntity<Void> deletePointOfIssue(@PathVariable(value = "points_id") long pointsId) {
        try {
            pointsOfIssueService.deletePointOfIssue(pointsId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<PointsOfIssueDTO> updatePointOfIssue(@RequestBody PointsOfIssueDTO pointsOfIssueDTO) {
        PointsOfIssueDTO pointsOfIssueDTO1 = pointsOfIssueService.updatePointOfIssue(pointsOfIssueDTO);
        return ResponseEntity.ok(pointsOfIssueDTO1);
    }

    @GetMapping("/{points_id}")
    public ResponseEntity<PointsOfIssueDTO> getPointOfIssueByPointsId(@PathVariable(value = "points_id") long pointsId) {
        return ResponseEntity.ok(pointsOfIssueService.getByPointsId(pointsId));
    }

    @GetMapping("/{address}")
    public ResponseEntity<PointsOfIssueDTO> getPointOfIssueByAddress(@PathVariable(value = "address") String address) {
        return ResponseEntity.ok(pointsOfIssueService.getByAddress(address));
    }
}
