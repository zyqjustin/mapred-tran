package easy.mapreduce.tran;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class EmptyTranTest {

	@Test
	public void testEmptyTran() throws IOException {
		Tran<String, String> trueStrTran1 = EmptyTran.getInstance();
		Tran<Integer, Integer> trueIntTran2 = EmptyTran.getInstance();
		Tran<String, Integer> falseTran = EmptyTran.getInstance();
		
		String str1 = "abc";
		Integer int2 = new Integer(2);
		
		Assert.assertEquals("abc", trueStrTran1.from(str1));
		Assert.assertEquals("abc", trueStrTran1.to(str1));
		Assert.assertEquals(new Integer(2), trueIntTran2.from(int2));
		Assert.assertEquals(new Integer(2), trueIntTran2.to(int2));
		
		try {
			Integer falseInt = falseTran.from(str1);
		} catch (Exception e) {
			Assert.assertTrue("error tran [String => Integer] is ok.", true);
		}
		
		try {
			String falseStr = falseTran.to(int2);
		} catch (Exception e) {
			Assert.assertTrue("error tran [Integer => String] is ok", true);
		}
	}
}
