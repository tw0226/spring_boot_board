package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.mysite.demo.question.QuestionService;
import com.mysite.demo.user.SiteUserDto;

@SpringBootTest
class TestPageApplicationTests {

	private QuestionService questionService;
	private SiteUserDto user;
	@Test
	void testJpa() {
        for (int i = 1; i <= 300; i++) {
            String subject = String.format("테스트 데이터입니다:[%03d]", i);
            String content = "내용무";
            this.questionService.create(subject, content, user);
        }
    }


}
