package com.maximmalikov.onlineshop.service;

import com.maximmalikov.onlineshop.domain.Delivery;
import com.maximmalikov.onlineshop.domain.PointsOfIssue;
import com.maximmalikov.onlineshop.dto.PointsOfIssueDTO;
import com.maximmalikov.onlineshop.repository.DeliveryRepository;
import com.maximmalikov.onlineshop.repository.PointsOfIssueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PointsOfIssueService {

    private final PointsOfIssueRepository pointsOfIssueRepository;

    private final DeliveryRepository deliveryRepository;

    private PointsOfIssueDTO fromPointsOfIssue(PointsOfIssue pointsOfIssue) {
        if (pointsOfIssue != null) {
            return PointsOfIssueDTO.builder()
                    .pointsId(pointsOfIssue.getPointsId())
                    .address(pointsOfIssue.getAddress())
                    .workTime(pointsOfIssue.getWorkTime())
                    .phoneNumber(pointsOfIssue.getPhoneNumber())
                    .deliveries(pointsOfIssue.getDeliveries() != null
                            ? pointsOfIssue.getDeliveries().stream()
                            .map(Delivery::getOrderNumber)
                            .collect(Collectors.toList())
                            : null)
                    .build();
        }
        return null;
    }

    private PointsOfIssue fromDTO(PointsOfIssueDTO pointsOfIssueDTO) {
        if (pointsOfIssueDTO != null) {
            return PointsOfIssue.builder()
                    .pointsId(pointsOfIssueDTO.getPointsId())
                    .address(pointsOfIssueDTO.getAddress())
                    .workTime(pointsOfIssueDTO.getWorkTime())
                    .phoneNumber(pointsOfIssueDTO.getPhoneNumber())
                    .deliveries(pointsOfIssueDTO.getDeliveries() != null
                            ? deliveryRepository.findAllById(pointsOfIssueDTO.getDeliveries())
                            : null)
                    .build();
        }
        return null;
    }

    public List<PointsOfIssueDTO> getAllPointsOfIssue() {
        return pointsOfIssueRepository.findAll().stream()
                .map(this::fromPointsOfIssue)
                .collect(Collectors.toList());
    }

    @Transactional
    public PointsOfIssueDTO addPointOfIssue(PointsOfIssueDTO pointsOfIssueDTO) {
        if (!pointsOfIssueRepository.existsById(pointsOfIssueDTO.getPointsId())) {
            return fromPointsOfIssue(pointsOfIssueRepository.saveAndFlush(fromDTO(pointsOfIssueDTO)));
        }
        return null;
    }

    @Transactional
    public void deletePointOfIssue(long pointsId) {
        if (pointsOfIssueRepository.existsById(pointsId)) {
            pointsOfIssueRepository.deleteById(pointsId);
        }
    }

    @Transactional
    public PointsOfIssueDTO updatePointOfIssue(PointsOfIssueDTO pointsOfIssueDTO) {
        if (pointsOfIssueRepository.existsById(pointsOfIssueDTO.getPointsId())) {
            PointsOfIssue pointsOfIssueTerm = pointsOfIssueRepository.getOne(pointsOfIssueDTO.getPointsId());
            pointsOfIssueTerm.setAddress(pointsOfIssueDTO.getAddress());
            pointsOfIssueTerm.setWorkTime(pointsOfIssueDTO.getWorkTime());
            pointsOfIssueTerm.setPhoneNumber(pointsOfIssueDTO.getPhoneNumber());
            return fromPointsOfIssue(pointsOfIssueRepository.saveAndFlush(pointsOfIssueTerm));
        }
        return null;
    }

    public PointsOfIssueDTO getByPointsId(long pointsId) {
        if (pointsOfIssueRepository.existsById(pointsId)) {
            return fromPointsOfIssue(pointsOfIssueRepository.getByPointsId(pointsId));
        }
        return null;
    }

    public PointsOfIssueDTO getByAddress(String address) {
        return fromPointsOfIssue(pointsOfIssueRepository.getByAddress(address));
    }
}
