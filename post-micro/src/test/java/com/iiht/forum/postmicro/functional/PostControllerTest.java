package com.iiht.forum.postmicro.functional;

import static com.iiht.forum.postmicro.testutils.TestUtils.*;

import java.io.IOException;

import org.junit.Test;

public class PostControllerTest {
	@Test
	public void postPostDummy() throws IOException {
		yakshaAssert(currentTest(), true, businessTestFile);
	}
}
