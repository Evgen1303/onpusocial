package com.hunghost.onpusocial.service.studygroup;

import com.hunghost.onpusocial.entity.Studygroup;
import com.hunghost.onpusocial.entity.User;
import com.hunghost.onpusocial.repositories.StudygroupRepository;
import com.hunghost.onpusocial.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyGroupCommandService {
    private StudygroupRepository studygroupRepository;
    private UserRepository userRepository;

    @Autowired
    public StudyGroupCommandService(StudygroupRepository studygroupRepository, UserRepository userRepository) {
        this.studygroupRepository = studygroupRepository;
        this.userRepository = userRepository;
    }

    public void saveStudygroup(Studygroup studygroup) {
        studygroupRepository.save(studygroup);
    }
    public void deleteStudygroup(Long id) {
        List<User> userList = userRepository.findByStudygroup_Id(id);

        for(User user: userList){
            user.setStudygroup(null);
            userRepository.save(user);
        }
        studygroupRepository.deleteById(id);
    }

    public Studygroup updateStudygroup(Long id, Studygroup studygroup) {
        studygroup.setId(id);
        studygroupRepository.save(studygroup);
        return studygroup;
    }
}
