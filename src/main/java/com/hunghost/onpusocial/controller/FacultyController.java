package com.hunghost.onpusocial.controller;

import com.hunghost.onpusocial.dto.FacultyDTO;
import com.hunghost.onpusocial.entity.Faculty;
import com.hunghost.onpusocial.service.faculty.FacultyCommandService;
import com.hunghost.onpusocial.service.faculty.FacultyConverterService;
import com.hunghost.onpusocial.service.faculty.FacultyQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("faculties")
public class FacultyController {
    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final String DEFAULT_SORT_FIELD = "id";
    private FacultyQueryService facultyQueryService ;
    private FacultyCommandService facultyCommandService ;
    private FacultyConverterService facultyConverterService ;

    @Autowired
    public FacultyController(FacultyQueryService facultyQueryService, FacultyCommandService facultyCommandService, FacultyConverterService facultyConverterService) {
        this.facultyQueryService = facultyQueryService;
        this.facultyCommandService = facultyCommandService;
        this.facultyConverterService = facultyConverterService;
    }

    @GetMapping
    public Page<Faculty> getPages(
            @PageableDefault(size = DEFAULT_PAGE_SIZE)
            @SortDefault.SortDefaults({@SortDefault(sort = DEFAULT_SORT_FIELD)})
                    Pageable pageable
    ) {
        return facultyQueryService.getPage(pageable);
    }

    @GetMapping("/{id}")
    public Faculty getFaculty(@PathVariable Long id) {
        return facultyQueryService.getFacultyById(id);
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody FacultyDTO facultyDTO ) {
        Faculty faculty = facultyConverterService.convertToEntity(facultyDTO);
        facultyCommandService.saveFaculty(faculty);
        return faculty;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long id) {
        facultyCommandService.deleteFaculty(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faculty> putFaculty(@PathVariable Long id, @RequestBody FacultyDTO facultyDTO) {
        return ResponseEntity.ok(facultyCommandService.updateFaculty(id,
                facultyConverterService.convertToEntity(facultyDTO)));
    }
}
