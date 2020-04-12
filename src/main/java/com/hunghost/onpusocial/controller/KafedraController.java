package com.hunghost.onpusocial.controller;

import com.hunghost.onpusocial.dto.KafedraDTO;
import com.hunghost.onpusocial.entity.Kafedra;
import com.hunghost.onpusocial.service.kafedra.KafedraCommandService;
import com.hunghost.onpusocial.service.kafedra.KafedraConverterService;
import com.hunghost.onpusocial.service.kafedra.KafedraQueryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("departments")
public class KafedraController {
    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final String DEFAULT_SORT_FIELD = "id";
    private KafedraQueryService kafedraQueryService;
    private KafedraCommandService kafedraCommandService;
    private KafedraConverterService kafedraConverterService;

    @Autowired
    public KafedraController(KafedraQueryService kafedraQueryService, KafedraCommandService kafedraCommandService, KafedraConverterService kafedraConverterService) {
        this.kafedraQueryService = kafedraQueryService;
        this.kafedraCommandService = kafedraCommandService;
        this.kafedraConverterService = kafedraConverterService;
    }

    @GetMapping
    public Page<Kafedra> getPages(
            @PageableDefault(size = DEFAULT_PAGE_SIZE)
            @SortDefault.SortDefaults({@SortDefault(sort = DEFAULT_SORT_FIELD)})
                    Pageable pageable
    ) {
        return kafedraQueryService.getPage(pageable);
    }
    @GetMapping("/faculty")
    public Page<Kafedra> getPagesByFacultyId(
            @PageableDefault(size = DEFAULT_PAGE_SIZE)
            @SortDefault.SortDefaults({@SortDefault(sort = DEFAULT_SORT_FIELD)})
            @RequestParam Long facultyid, Pageable pageable
    ) {
        return kafedraQueryService.getPageByFacultyId(facultyid, pageable);
    }

    @GetMapping("/{id}")
    public Kafedra getKafedra(@PathVariable Long id) {
        return kafedraQueryService.getKafedraById(id);
    }

    @PostMapping
    public Kafedra createKafedra(@RequestBody KafedraDTO kafedraDTO) {
        Kafedra kafedra = kafedraConverterService.convertToEntity(kafedraDTO);
        kafedraCommandService.saveKafedra(kafedra);
        return kafedra;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Kafedra> deleteKafedra(@PathVariable Long id) {
        kafedraCommandService.deleteKafedra(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kafedra> putKafedra(@PathVariable Long id, @RequestBody KafedraDTO kafedraDTO) {
        return ResponseEntity.ok(kafedraCommandService.updateKafedra(id,
                kafedraConverterService.convertToEntity(kafedraDTO)));
    }
}
