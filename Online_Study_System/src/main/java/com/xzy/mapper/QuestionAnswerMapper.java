package com.xzy.mapper;

import com.xzy.domain.QuestionAnswer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionAnswerMapper {

    List<QuestionAnswer> selectQuestions(@Param("id") int id);


    QuestionAnswer selectQuestionAndAnswer(@Param("courseId") Integer courseId, @Param("i") int i);

}
