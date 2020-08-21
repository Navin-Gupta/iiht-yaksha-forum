package com.iiht.forum.postmicro.functional;

import org.junit.Test;
import static com.iiht.forum.postmicro.testutils.TestUtils.*;

import java.io.IOException;

public class CommentControllerTest {

	@Test
	public void postCommentDummy() throws IOException {
		yakshaAssert(currentTest(), true, businessTestFile);
	}
}
