package com.xzy.service;

import com.xzy.domain.QuestionAnswer;

import java.util.List;

public interface QuestionAnswerService {
    /**
     * 查询课后习题信息
     * @param id
     * @return
     */
    List<QuestionAnswer> queryQuestions(int id);

    /**
     * 查询某一个习题答案
     * @param courseId
     * @param i
     * @return
     */
    QuestionAnswer queryOneQuestionAndAnswer(Integer courseId, int i);

}