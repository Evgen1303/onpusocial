package com.hunghost.onpusocial.service.kafedra;

import com.hunghost.onpusocial.dto.KafedraDTO;
import com.hunghost.onpusocial.entity.Kafedra;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class KafedraConverterService {
    private ModelMapper modelMapper = new ModelMapper();

    public KafedraDTO convertToDto(Kafedra kafedra) {
        return modelMapper.map(kafedra, KafedraDTO.class);
    }

    public  Kafedra convertToEntity(KafedraDTO kafedraDTO) {
        Kafedra kafedra = modelMapper.map(kafedraDTO, Kafedra.class);
        return kafedra;

    }
}
