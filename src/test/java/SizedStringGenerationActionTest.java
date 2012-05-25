import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;

import org.junit.Test;

import com.nacnez.util.modelgen.impl.generator.action.SizedStringGenerationAction;


public class SizedStringGenerationActionTest {

	SizedStringGenerationAction ssgaCut; 
	
	
	@Test
	public void testRandomGeneration() {
		int STRING_LENGTH_FOR_TEST = 55;
		ssgaCut= new SizedStringGenerationAction(STRING_LENGTH_FOR_TEST);
		String str = (String) ssgaCut.generate();
		assertNotNull(str);
		assertEquals(STRING_LENGTH_FOR_TEST,str.length());

		String str2 = (String) ssgaCut.generate();
		assertNotNull(str2);
		assertEquals(STRING_LENGTH_FOR_TEST,str.length());
		assertTrue(!str.equals(str2));

		String str3 = (String) ssgaCut.generate();
		assertNotNull(str3);
		assertEquals(STRING_LENGTH_FOR_TEST,str.length());
		assertTrue(!str.equals(str3));
		assertTrue(!str2.equals(str3));
	}
	
	@Test
	public void testForMutlipleLengths() {
		int FIRST_STRING_LENGTH_FOR_TEST = 100;
		ssgaCut= new SizedStringGenerationAction(FIRST_STRING_LENGTH_FOR_TEST);
		String str = (String) ssgaCut.generate();
		assertNotNull(str);
		assertEquals(FIRST_STRING_LENGTH_FOR_TEST,str.length());
		

		int SECOND_STRING_LENGTH_FOR_TEST = 100;
		ssgaCut= new SizedStringGenerationAction(SECOND_STRING_LENGTH_FOR_TEST);
		String str2 = (String) ssgaCut.generate();
		assertNotNull(str2);
		assertEquals(SECOND_STRING_LENGTH_FOR_TEST,str2.length());
	
	}

}
