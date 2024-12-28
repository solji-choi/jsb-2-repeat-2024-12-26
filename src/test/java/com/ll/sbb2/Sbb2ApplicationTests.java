package com.ll.sbb2;

import com.ll.sbb2.domain.question.answer.entity.Answer;
import com.ll.sbb2.domain.question.answer.repository.AnswerRepository;
import com.ll.sbb2.domain.question.question.entity.Question;
import com.ll.sbb2.domain.question.question.repository.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class Sbb2ApplicationTests {
	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Test
	@DisplayName("question insert")
	void testJpa1() {
		Question q1 = Question
				.builder()
				.subject("sbb가 무엇인가요?")
				.content("sbb에 대해서 알고 싶습니다.")
				.createDate(LocalDateTime.now())
				.build();

		this.questionRepository.save(q1);

		Question q2 = Question
				.builder()
				.subject("스프링부트 모델 질문입니다.")
				.content("id는 자동으로 생성되나요?")
				.createDate(LocalDateTime.now())
				.build();

		this.questionRepository.save(q2);
	}

	@Test
	@DisplayName("question findAll")
	void testJpa2() {
		List<Question> all = this.questionRepository.findAll();

		assertEquals(3, all.size());

		Question q = all.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());
	}

	@Test
	@DisplayName("question findById")
	void testJpa3() {
		Optional<Question> oq = this.questionRepository.findById(1);

		if(oq.isPresent()) {
			Question q = oq.get();

			assertEquals("sbb가 무엇인가요?", q.getSubject());
		}
	}

	@Test
	@DisplayName("question findBySubject")
	void testJpa4() {
		Question q = this.questionRepository.findBySubject("스프링부트 모델 질문입니다.");

		assertEquals(3, q.getId());
	}

	@Test
	@DisplayName("question findBySubjectAndContent")
	void testJpa5() {
		Question q = this.questionRepository.findBySubjectAndContent("스프링부트 모델 질문입니다.", "id는 자동으로 생성되나요?");

		assertEquals(3, q.getId());
	}

	@Test
	@DisplayName("question findBySubjectLike")
	void testJpa6() {
		List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");

		Question q = qList.get(0);

		assertEquals("sbb가 무엇인가요?", q.getSubject());
	}

	@Test
	@DisplayName("question modify")
	void testJpa7() {
		Optional<Question> oq = this.questionRepository.findById(1);

		assertTrue(oq.isPresent());

		Question q = oq.get();

		q.setSubject("수정된 제목");

		this.questionRepository.save(q);
	}

	@Test
	@DisplayName("question delete")
	void testJpa8() {
		assertEquals(3, this.questionRepository.count());

		Optional<Question> oq = this.questionRepository.findById(1);

		assertTrue(oq.isPresent());

		Question q = oq.get();

		this.questionRepository.delete(q);

		assertEquals(2, this.questionRepository.count());
	}

	@Test
	@DisplayName("answer create")
	void testJpa9() {
		Optional<Question> oq = this.questionRepository.findById(2);

		assertTrue(oq.isPresent());

		Question q = oq.get();

		Answer a = Answer
				.builder()
				.content("네")
				.createDate(LocalDateTime.now())
				.question(q)
				.build();

		this.answerRepository.save(a);
	}

	@Test
	@DisplayName("answer findById")
	void testJpa10() {
		Optional<Answer> oa = this.answerRepository.findById(1);

		assertTrue(oa.isPresent());

		Answer a = oa.get();

		assertEquals(2, a.getQuestion().getId());
	}

	@Test
	@DisplayName("find answer from question")
	@Transactional
	void testJpa11() {
		Optional<Question> oq = this.questionRepository.findById(2);

		assertTrue(oq.isPresent());

		Question q = oq.get();

		List<Answer> answerList = q.getAnswerList();

		assertEquals(1, answerList.size());
		assertEquals("네", answerList.get(0).getContent());
	}
}
