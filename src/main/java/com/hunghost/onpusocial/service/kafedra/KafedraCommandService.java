package com.hunghost.onpusocial.service.kafedra;

import com.hunghost.onpusocial.entity.Kafedra;
import com.hunghost.onpusocial.repositories.KafedraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafedraCommandService {

    private KafedraRepository kafedraRepository;

    @Autowired
    public KafedraCommandService(KafedraRepository kafedraRepository) {
        this.kafedraRepository = kafedraRepository;
    }

    public void saveKafedra(Kafedra kafedra) {
        kafedraRepository.save(kafedra);
    }
    public void deleteKafedra(Long id) {
        kafedraRepository.deleteById(id);
    }

    public Kafedra updateKafedra(Long id, Kafedra kafedra) {
        kafedra.setId(id);
        kafedraRepository.save(kafedra);
        return kafedra;
    }
}
