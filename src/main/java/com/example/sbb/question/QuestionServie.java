package com.example.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import com.example.sbb.DataNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
@Service
public class QuestionServie {
    private final QuestionRepository questionRepository;


    public Question getQuestion(Integer id){
        Optional<Question> question = this.questionRepository.findById(id);
        if(question.isPresent()){
            return question.get();
        }else{
            throw new DataNotFoundException("Qquestion not found");
        }
    }

    public void create(String subject, String content){
        Question q = new Question();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q);
    }
    public Page<Question> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.questionRepository.findAll(pageable);
    }
}
