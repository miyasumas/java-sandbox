package com.github.miyasum.sandbox.args4j;

import static org.junit.Assert.*;
import org.junit.Test;
import org.kohsuke.args4j.CmdLineException;

/**
 * {@link com.github.miyasum.sandbox.args4j.SubCommandSample} のテスト
 */
public class SubCommandSampleTest {

	@Test
	public void main() throws CmdLineException {
		SubCommandSample.main(new String[] { "hello" }); // Hello
		SubCommandSample.main(new String[] { "goodbye" }); // Goodbye
		SubCommandSample.main(new String[] { "aaa" }); // エラー
	}
}
