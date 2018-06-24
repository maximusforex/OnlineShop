package com.maximmalikov.onlineshop.service;

import com.maximmalikov.onlineshop.domain.Delivery;
import com.maximmalikov.onlineshop.dto.DeliveryDTO;
import com.maximmalikov.onlineshop.repository.DeliveryRepository;
import com.maximmalikov.onlineshop.repository.OrdersRepository;
import com.maximmalikov.onlineshop.repository.PointsOfIssueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    private final PointsOfIssueRepository pointsOfIssueRepository;

    private final OrdersRepository ordersRepository;

    private DeliveryDTO fromDelivery(Delivery delivery) {
        if (delivery != null) {
            return DeliveryDTO.builder()
                    .orderNumber(delivery.getOrderNumber())
                    .totalWeight(delivery.getTotalWeight())
                    .totalVolume(delivery.getTotalVolume())
                    .deliveryDate(delivery.getDeliveryDate())
                    .deliveryTime(delivery.getDeliveryTime())
                    .price(delivery.getPrice())
                    .deliveryStatus(delivery.getDeliveryStatus())
                    .deliveryType(delivery.getDeliveryType())
                    .orders(delivery.getOrder().getOrderNumber())
                    .pointOfIssue(delivery.getPointOfIssue().getPointsId())
                    .build();
        }
        return null;
    }

    private Delivery fromDTO(DeliveryDTO deliveryDTO) {
        if (deliveryDTO != null) {
            return Delivery.builder()
                    .orderNumber(deliveryDTO.getOrderNumber())
                    .totalWeight(deliveryDTO.getTotalWeight())
                    .totalVolume(deliveryDTO.getTotalVolume())
                    .deliveryDate(deliveryDTO.getDeliveryDate())
                    .deliveryTime(deliveryDTO.getDeliveryTime())
                    .price(deliveryDTO.getPrice())
                    .deliveryStatus(deliveryDTO.getDeliveryStatus())
                    .deliveryType(deliveryDTO.getDeliveryType())
                    .order(deliveryDTO.getOrders() > 0L
                            ? ordersRepository.getOne(deliveryDTO.getOrderNumber())
                            : null)
                    .pointOfIssue(deliveryDTO.getPointOfIssue() > 0L
                            ? pointsOfIssueRepository.getOne(deliveryDTO.getPointOfIssue())
                            : null)
                    .build();
        }
        return null;
    }

    public List<DeliveryDTO> getAllDelivery() {
        return deliveryRepository.findAll().stream()
                .map(this::fromDelivery)
                .collect(Collectors.toList());
    }

    @Transactional
    public DeliveryDTO addDelivery(DeliveryDTO deliveryDTO) {
        if (!deliveryRepository.existsById(deliveryDTO.getOrderNumber())) {
            return fromDelivery(deliveryRepository.saveAndFlush(fromDTO(deliveryDTO)));
        }
        return null;
    }

    @Transactional
    public void deleteDelivery(long orderNumber) {
        if (deliveryRepository.existsById(orderNumber)) {
            deliveryRepository.deleteById(orderNumber);
        }
    }

    @Transactional
    public DeliveryDTO updateDelivery(DeliveryDTO deliveryDTO) {
        if (deliveryRepository.existsById(deliveryDTO.getOrderNumber())) {
            Delivery deliveryTerm = deliveryRepository.getOne(deliveryDTO.getOrderNumber());
            deliveryTerm.setTotalWeight(deliveryDTO.getTotalWeight());
            deliveryTerm.setTotalVolume(deliveryDTO.getTotalVolume());
            deliveryTerm.setDeliveryDate(deliveryDTO.getDeliveryDate());
            deliveryTerm.setDeliveryTime(deliveryDTO.getDeliveryTime());
            deliveryTerm.setPrice(deliveryDTO.getPrice());
            deliveryTerm.setDeliveryStatus(deliveryDTO.getDeliveryStatus());
            deliveryTerm.setDeliveryType(deliveryDTO.getDeliveryType());
            return fromDelivery(deliveryRepository.saveAndFlush(deliveryTerm));
        }
        return null;
    }

    public DeliveryDTO getByOrderNumber(long orderNumber) {
        if (deliveryRepository.existsById(orderNumber)) {
            return fromDelivery(deliveryRepository.getByOrderNumber(orderNumber));
        }
        return null;
    }

    public DeliveryDTO getByDeliveryDate(Date deliveryDate) {
        return fromDelivery(deliveryRepository.getByDeliveryDate(deliveryDate));
    }
}
