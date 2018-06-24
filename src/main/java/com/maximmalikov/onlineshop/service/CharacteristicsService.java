package com.maximmalikov.onlineshop.service;

import com.maximmalikov.onlineshop.domain.Characteristics;
import com.maximmalikov.onlineshop.dto.CharacteristicsDTO;
import com.maximmalikov.onlineshop.repository.CharacteristicsRepository;
import com.maximmalikov.onlineshop.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CharacteristicsService {

    private final GoodsRepository goodsRepository;

    private final CharacteristicsRepository characteristicsRepository;

    private CharacteristicsDTO fromCharacteristics(Characteristics characteristic) {
        if (characteristic != null) {
            return CharacteristicsDTO.builder()
                    .characteristicId(characteristic.getCharacteristicId())
                    .characteristicName(characteristic.getCharacteristicName())
                    .description(characteristic.getDescription())
                    .product(characteristic.getProduct().getProductId())
                    .build();
        }
        return null;
    }

    private Characteristics fromDTO(CharacteristicsDTO characteristicsDTO) {
        if (characteristicsDTO != null) {
            return Characteristics.builder()
                    .characteristicId(characteristicsDTO.getCharacteristicId())
                    .characteristicName(characteristicsDTO.getCharacteristicName())
                    .description(characteristicsDTO.getDescription())
                    .product(characteristicsDTO.getProduct() > 0L
                            ? goodsRepository.getOne(characteristicsDTO.getProduct())
                            : null)
                    .build();
        }
        return null;
    }

    public List<CharacteristicsDTO> getAllCharacteristics() {
        return characteristicsRepository.findAll().stream()
                .map(this::fromCharacteristics)
                .collect(Collectors.toList());
    }

    @Transactional
    public CharacteristicsDTO addCharacteristic(CharacteristicsDTO characteristicsDTO) {
        if (!characteristicsRepository.existsById(characteristicsDTO.getCharacteristicId())) {
            return fromCharacteristics(characteristicsRepository.saveAndFlush(fromDTO(characteristicsDTO)));
        }
        return null;
    }

    @Transactional
    public void deleteByCharacteristicId(long characteristicId) {
        if (characteristicsRepository.existsById(characteristicId)) {
            characteristicsRepository.deleteById(characteristicId);
        }
    }

    @Transactional
    public CharacteristicsDTO updateCharacteristics(CharacteristicsDTO characteristicsDTO) {
        if (characteristicsRepository.existsById(characteristicsDTO.getCharacteristicId())) {
            Characteristics characteristicsTemp = characteristicsRepository.getOne(characteristicsDTO.getCharacteristicId());
            characteristicsTemp.setCharacteristicName(characteristicsDTO.getCharacteristicName());
            characteristicsTemp.setDescription(characteristicsDTO.getDescription());
            return fromCharacteristics(characteristicsRepository.saveAndFlush(characteristicsTemp));
        }
        return null;
    }

    public CharacteristicsDTO getByCharacteristicId(long characteristicId) {
        if (characteristicsRepository.existsById(characteristicId)) {
            return fromCharacteristics(characteristicsRepository.getByCharacteristicId(characteristicId));
        }
        return null;
    }

    public CharacteristicsDTO getByProductId(long productId) {
        if (characteristicsRepository.existsById(productId)) {
            return fromCharacteristics(characteristicsRepository.getByProductProductId(productId));
        }
        return null;
    }

}
