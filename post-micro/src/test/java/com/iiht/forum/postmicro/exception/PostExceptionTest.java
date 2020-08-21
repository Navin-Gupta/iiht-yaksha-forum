package com.iiht.forum.postmicro.exception;

import static com.iiht.forum.postmicro.testutils.TestUtils.*;

import java.io.IOException;

import org.junit.Test;

public class PostExceptionTest {
	
		@Test
		public void postExceptionDummy() throws IOException {
			yakshaAssert(currentTest(), false, exceptionTestFile);
		}

	
}
