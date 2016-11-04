package com.backcube;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners
public class SpringBootVaadinApplicationTests {

	@Test
	public void contextLoads() {
	}


	@Test
	public void testAppendSimple() {
		StringBuilder sb = new StringBuilder("Hello");
		assertEquals("string test", "Hello w", sb.toString());

	}

	@Test
	public void testSqrtGood() {
		assertEquals("test sqrt",1.414213562D, Math.sqrt(2), 0.00000001D);
	}
}
