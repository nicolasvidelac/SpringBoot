package com.example.JPAexample;



import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class JpAexampleApplicationTests {

	Calculator _test = new Calculator();
	@Test
	void itShouldAddNumber() {
		assertThat(_test.add(20,30)).isEqualTo(50);
	}

	class Calculator{
		int add(int a, int b){
			return a + b;
		}
	}

}
