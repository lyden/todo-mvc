package org.davidpadbury.modernweb;

import static org.junit.Assert.*;

import org.junit.Test;

public class HiControllerTest {

	@Test
	public void index_normallyCalled_returnsNonNull() {
		HiController controller = new HiController();
		
		String result = controller.index();
		
		assertNotNull(result);
	}

}
