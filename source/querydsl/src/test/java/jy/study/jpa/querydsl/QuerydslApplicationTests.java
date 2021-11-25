package jy.study.jpa.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jy.study.jpa.querydsl.entity.Hello;
import jy.study.jpa.querydsl.entity.QHello;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class QuerydslApplicationTests {

	@Autowired
	EntityManager em;

	@Test
	void contextLoads() {
		Hello hello = new Hello();
		em.persist(hello);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		QHello qHello = new QHello("h");

		Hello result = queryFactory
				.selectFrom(qHello)
				.fetchOne();

		Assertions.assertThat(result).isEqualTo(hello);

	}

}
