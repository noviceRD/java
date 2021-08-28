package com.xzy.service.impl;

import com.xzy.domain.QuestionAnswer;
import com.xzy.mapper.QuestionAnswerMapper;
import com.xzy.service.QuestionAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionAnswerServiceImpl implements QuestionAnswerService {
    @Autowired
    private QuestionAnswerMapper qsm;


    @Override
    public List<QuestionAnswer> queryQuestions(int id) {
        return qsm.selectQuestions(id);
    }

    @Override
    public QuestionAnswer queryOneQuestionAndAnswer(Integer courseId, int i) {
        return qsm.selectQuestionAndAnswer(courseId,i);
    }




}
