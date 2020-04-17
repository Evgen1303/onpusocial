package com.hunghost.onpusocial.service.post;

import com.hunghost.onpusocial.entity.*;
import com.hunghost.onpusocial.exception.ResourceNotFoundException;
import com.hunghost.onpusocial.repositories.PostRepository;
import com.hunghost.onpusocial.service.faculty.FacultyQueryService;
import com.hunghost.onpusocial.service.kafedra.KafedraQueryService;
import com.hunghost.onpusocial.service.studygroup.StudyGroupQueryService;
import com.hunghost.onpusocial.service.user.UserQueryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class PostQueryService {
    private PostRepository postRepository;
    private StudyGroupQueryService studyGroupQueryService;
    private KafedraQueryService kafedraQueryService;
    private FacultyQueryService facultyQueryService;
    private UserQueryService userQueryService;

    private static final Logger log = LogManager.getLogger(PostQueryService.class);
    @Autowired
    public PostQueryService(PostRepository postRepository, StudyGroupQueryService studyGroupQueryService, KafedraQueryService kafedraQueryService, FacultyQueryService facultyQueryService, UserQueryService userQueryService) {
        this.postRepository = postRepository;
        this.studyGroupQueryService = studyGroupQueryService;
        this.kafedraQueryService = kafedraQueryService;
        this.facultyQueryService = facultyQueryService;
        this.userQueryService = userQueryService;
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Page<Post> getPage(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Page<Post> getPostByUserLogin(String username, Pageable pageable){
        return postRepository.findByUserUsername(username, pageable);
    }

    public Page<Post> getPostsForUser(Pageable pageable){
        User user = userQueryService.getAuthUser();
        Boolean isAllusers = true;
        Long studygroup_id = 0l;
        Long kafedra_id = 0l;
        Long faculty_id = 0l;

            if(user.getStudygroup()!=null){
                studygroup_id =user.getStudygroup().getId();
                kafedra_id = user.getStudygroup().getKafedra().getId();
                faculty_id = user.getStudygroup().getKafedra().getFaculty().getId();
            }


        List<Post> allPostList = postRepository.findBySubscribers(true);
        List<User> toDisplayUserList = new ArrayList<>();
        for(Post post: allPostList){
            Set<User> userSet = post.getUserObject().getSubscribers();
            if(userSet.contains(user)){
                toDisplayUserList.add(post.getUserObject());
            }
        }
        toDisplayUserList.add(user);

       if(studygroup_id !=0L && kafedra_id != 0L && faculty_id!=0L && user != null){

           return postRepository.findByAlluserOrStudygroup_IdOrKafedra_IdOrFaculty_IdOrUserIn(isAllusers,studygroup_id,kafedra_id,faculty_id,toDisplayUserList,pageable);
       }
       else {

         return postRepository.findByAlluserOrUserIn(isAllusers,toDisplayUserList,pageable);
       }

    }






}
