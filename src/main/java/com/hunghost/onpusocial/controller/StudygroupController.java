package com.hunghost.onpusocial.controller;

import com.hunghost.onpusocial.dto.StudygroupDTO;
import com.hunghost.onpusocial.entity.Studygroup;
import com.hunghost.onpusocial.service.studygroup.StudyGroupCommandService;
import com.hunghost.onpusocial.service.studygroup.StudyGroupConverterService;
import com.hunghost.onpusocial.service.studygroup.StudyGroupQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("studygroups")
public class
StudygroupController {
    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final String DEFAULT_SORT_FIELD = "id";
    private StudyGroupQueryService studyGroupQueryService;
    private StudyGroupCommandService studyGroupCommandService;
    private StudyGroupConverterService studyGroupConverterService;

    @Autowired
    public StudygroupController(StudyGroupQueryService studyGroupQueryService, StudyGroupCommandService studyGroupCommandService
            , StudyGroupConverterService studyGroupConverterService
    ) {
        this.studyGroupQueryService = studyGroupQueryService;
        this.studyGroupCommandService = studyGroupCommandService;
        this.studyGroupConverterService = studyGroupConverterService;
    }

    @GetMapping
    public Page<Studygroup> getPages(
            @PageableDefault(size = DEFAULT_PAGE_SIZE)
            @SortDefault.SortDefaults({@SortDefault(sort = DEFAULT_SORT_FIELD)})
                    Pageable pageable
    ) {
        return studyGroupQueryService.getPage(pageable);
    }
    @GetMapping("/kafedra")
    public Page<Studygroup> getPagesByFacultyId(
            @PageableDefault(size = DEFAULT_PAGE_SIZE)
            @SortDefault.SortDefaults({@SortDefault(sort = DEFAULT_SORT_FIELD)})
            @RequestParam Long kafedraid, Pageable pageable
    ) {
        return studyGroupQueryService.getPageByKafedraId(pageable, kafedraid);
    }

    @GetMapping("/{id}")
    public Studygroup getStudygroup(@PathVariable Long id) {
        return studyGroupQueryService.getStudyGroupById(id);
    }

    @PostMapping
    public Studygroup createStudygroup(@RequestBody StudygroupDTO studygroupDTO) {
        Studygroup studygroup = studyGroupConverterService.convertToEntity(studygroupDTO);
        studyGroupCommandService.saveStudygroup(studygroup);
        return studygroup;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Studygroup> deleteStudygroup(@PathVariable Long id) {
        studyGroupCommandService.deleteStudygroup(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Studygroup> putStudygroup(@PathVariable Long id, @RequestBody StudygroupDTO studygroupDTO) {
        return ResponseEntity.ok(studyGroupCommandService.updateStudygroup(id, studyGroupConverterService.convertToEntity(studygroupDTO)));
    }
}
