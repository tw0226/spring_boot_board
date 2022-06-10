package com.mysite.demo.answer;

import java.time.LocalDateTime;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.mysite.demo.question.QuestionDto;
import com.mysite.demo.user.SiteUserDto;
import com.mysite.demo.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final ModelMapper modelMapper;

    private Answer of(AnswerDto answerDto) {
        return modelMapper.map(answerDto, Answer.class);
    }
    
    private AnswerDto of(Answer answer) {
        return modelMapper.map(answer, AnswerDto.class);
    }
    
    public AnswerDto create(QuestionDto questionDto, String content, SiteUserDto author) {
        AnswerDto answerDto = new AnswerDto();
        answerDto.setContent(content);
        answerDto.setCreateDate(LocalDateTime.now());
        answerDto.setQuestion(questionDto);
        answerDto.setAuthor(author);
        Answer answer = of(answerDto);
        answer = this.answerRepository.save(answer);
        answerDto.setId(answer.getId());
        return answerDto;
    }
    
    public AnswerDto getAnswer(Integer id) {
        Optional<Answer> answer = this.answerRepository.findById(id);
        if (answer.isPresent()) {
            return of(answer.get());
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    public AnswerDto modify(AnswerDto answerDto, String content) {
        answerDto.setContent(content);
        answerDto.setModifyDate(LocalDateTime.now());
        this.answerRepository.save(of(answerDto));
        return answerDto;
    }
    
    public void delete(AnswerDto answerDto) {
        this.answerRepository.delete(of(answerDto));
    }
    
    public AnswerDto vote(AnswerDto answerDto, SiteUserDto siteUserDto) {
        answerDto.getVoter().add(siteUserDto);
        this.answerRepository.save(of(answerDto));
        return answerDto;
    }
}