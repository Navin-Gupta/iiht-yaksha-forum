package com.iiht.forum.postmicro.boundary;

import static com.iiht.forum.postmicro.testutils.TestUtils.*;

import java.io.IOException;

import org.junit.Test;

public class PostBoundaryTest {
	@Test
	public void postBoundaryDummy() throws IOException {
		yakshaAssert(currentTest(), true, boundaryTestFile);
	}

}
