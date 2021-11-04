package tests;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import lab.*;

class CombinationLock_Tests {

	private static final PrintStream systemOut = System.out;
	private static final InputStream systemIn = System.in;
	private ByteArrayInputStream testIn;
	private String outputStream;
	
	@Test
	@DisplayName("Test #1: Constructors")
	void test1() {
		
		CombinationLock c1 = new CombinationLock();
		CombinationLock c2 = new CombinationLock("Ruby");
		
		int testNum = 1;
		
		
		try {
			overridePrintStatements();
			Assertions.assertTrue(c1.isLocked());
			
			testNum++;
			Assertions.assertTrue(c2.isLocked());
			
			resetPrintStream();
			System.out.println("Test #1 Constructors: Passed\n"
					+ "   Note: I can't tell if the combination is properly stored.\n"
					+ "         That's on you.\n\n\n\n");
			
		}
		catch (Error e) {
			resetPrintStream();
			if (testNum == 1) {
				System.out.println("Test #1: \n"
						 + "   Didn't properly construct the Default Contructor"
				         + "\n\n\n\n");
			}
			else if (testNum == 2) {
				System.out.println("Test #1: \n"
						 + "   Didn't properly construct the Parameterized Contructor"
				         + "\n\n\n\n");
			}
			Assertions.fail();
		}
		
	}
	
	
	@Test
	@DisplayName("Test #2: open")
	void test2() {
		
		CombinationLock c1 = new CombinationLock("hello");
		
		int testNum = 1;
		
		try {
			overridePrintStatements();
			provideInput("hello\n");
			c1.open();
			Assertions.assertFalse(c1.isLocked());
			
			testNum++;
			c1.close();
			provideInput("goodbye");
			c1.open();
			Assertions.assertTrue(c1.isLocked());
			
			
			resetPrintStream();
			System.out.println("Test #2 open(): Passed\n\n\n\n");
			
		}
		catch (Error e) {
			resetPrintStream();
			
			if (testNum == 1) {
				System.out.println("Test #2: \n"
						 + "   open() did not unlock when correct password was given.\n"
				         + "   \n\n\n\n");
			}
			else if (testNum == 2) {
				System.out.println("Test #2: \n"
						 + "   open() unlocked when incorrect password was given.\n"
				         + "   \n\n\n\n");
				
			}
			
			
			Assertions.fail();
		}
		
	}
	
	
	
	
	
	
	@Test
	@DisplayName("Test #3: setCombination")
	void test3() {
		
		outputStream = "";
		
		CombinationLock c1 = new CombinationLock("hello");
		
		int testNum = 1;
		
		try {
			overridePrintStatements();
			c1.close();
			c1.getCombination();
			Assertions.assertTrue(outputStream.contains("Unlock First"));
			
			resetPrintStream();
			overridePrintStatements();
			
			testNum++;
			provideInput("hello\n");
			c1.open();
			provideInput("goodbye\n");
			c1.setCombination();
			c1.close();
			provideInput("goodbye\n");
			c1.open();
			Assertions.assertFalse(c1.isLocked());
			
			
			resetPrintStream();
			System.out.println("Test #3 setCombination(): Passed\n\n\n\n");
			
			
		}
		catch (Error e) {
			resetPrintStream();
			
			if (testNum == 1) {
				System.out.println("Test #3: \n"
						 + "   getCombination() did not tell the user to Unlock First\n"
				         + "   \n\n\n\n");
			}
			else if (testNum == 2) {
				System.out.println("Test #3: \n"
						 + "   getCombination() password was not changed correctly\n"
				         + "   \n\n\n\n");
				
			}
			
			
			Assertions.fail();
		}
		
	}
	
	
	
	
	@Test
	@DisplayName("Test #4: getCombination")
	void test4() {
		
		CombinationLock c1 = new CombinationLock("hello");
		
		int testNum = 1;
		
		try {
			overridePrintStatements();
			outputStream = "";
			c1.close();
			c1.getCombination();
			Assertions.assertTrue(outputStream.contains("Unlock First"));
			
			resetPrintStream();
			overridePrintStatements();
			outputStream = "";
			
			testNum++;
			provideInput("hello\n");
			c1.open();
			Assertions.assertTrue(c1.getCombination().contains("hello"));
			
			
			resetPrintStream();
			System.out.println("Test #4 getCombination(): Passed\n\n\n\n");
			
		}
		catch (Error e) {
			resetPrintStream();
			
			if (testNum == 1) {
				System.out.println("Test #4: \n"
						 + "   getCombination() did not tell the user to unlock first\n"
				         + "   \n\n\n\n");
			}
			else if (testNum == 2) {
				System.out.println("Test #4: \n"
						 + "   getCombination() did not display the password\n"
				         + "   \n\n\n\n");
				System.out.println(outputStream);
				
			}
			
			
			Assertions.fail();
		}
		
	}
	
	
	

	
	private void overridePrintStatements() {
		
		System.setOut(new PrintStream(System.out) {
			
			//override all println()s
			public void println() {
				printCalled("\n");
			}
			public void println(boolean b) {
				printCalled(String.valueOf(b) + "\n");
			}
			public void println(char c) {
				printCalled(String.valueOf(c) + "\n");
			}
			public void println(char[] s) {
				printCalled(String.valueOf(s) + "\n");
			}
			public void println(double d) {
				printCalled(String.valueOf(d) + "\n");
			}
			public void println(float f) {
				printCalled(String.valueOf(f) + "\n");
			}
			public void println(int i) {
				printCalled(String.valueOf(i) + "\n");
			}
			public void println(long l) {
				printCalled(String.valueOf(l) + "\n");
			}
			public void println(Object obj) {
				printCalled(String.valueOf(obj) + "\n");
			}
			public void println(String str) {
				printCalled(str + "\n");
			}
			
			
			//override printf()
			public void printf(String str) {
				printCalled(str);
			}
			
			
			//override all print()s
			public void print(boolean b) {
				printCalled(String.valueOf(b));
			}
			public void print(char c) {
				printCalled(String.valueOf(c));
			}
			public void print(char[] s) {
				printCalled(String.valueOf(s));
			}
			public void print(double d) {
				printCalled(String.valueOf(d));
			}
			public void print(float f) {
				printCalled(String.valueOf(f));
			}
			public void print(int i) {
				printCalled(String.valueOf(i));
			}
			public void print(long l) {
				printCalled(String.valueOf(l));
			}
			public void print(Object obj) {
				printCalled(String.valueOf(obj));
			}
			public void print(String str) {
				printCalled(str);
			}
			
			
		});

	}
	
	
	private void printCalled(String str) {
		outputStream += str; 
	}
	
	
	private void provideInput(String data) {
		testIn = new ByteArrayInputStream(data.getBytes());
		System.setIn(testIn);
	}
	
	private void resetPrintStream() {
		System.setOut(systemOut);
		System.setIn(systemIn); 
	}
	
	@AfterAll
	static void afterAll() {
		System.setOut(systemOut);
		System.setIn(systemIn);
	}
	
	
}
