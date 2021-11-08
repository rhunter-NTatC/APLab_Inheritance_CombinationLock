package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import lab.Lock;

class Lock_Tests {

	private static final PrintStream systemOut = System.out;
	private static final InputStream systemIn = System.in;
	private ByteArrayInputStream testIn;
	private String outputStream;
	
	@Test
	@DisplayName("Test #1: Constructor")
	void test1() {
		
		Lock l1 = new Lock();
		
		try {
			overridePrintStatements();
			Assertions.assertTrue(l1.isLocked());
			resetPrintStream();
			System.out.println("Test #1: Passed\n\n\n\n");
			
		}
		catch (Error e) {
			resetPrintStream();
			System.out.println("Test #1: \n"
					 + "   didn't properly construct the Lock object.\n"
			         + "   Or you haven't done the isLocked() method yet.\n"
			         + "   Lock should initially be closed.\n\n\n\n");
			Assertions.fail();
		}
		
	}
	
	
	@Test
	@DisplayName("Test #2: Setter Methods")
	void test2() {
		
		Lock l1 = new Lock();
		
		try {
			overridePrintStatements();
			l1.open();
			Assertions.assertFalse(l1.isLocked());
			l1.close();
			Assertions.assertTrue(l1.isLocked());
			resetPrintStream();
			System.out.println("Test #2: Passed\n\n\n\n");
			
		}
		catch (Error e) {
			resetPrintStream();
			System.out.println("Test #2: \n"
					 + "   Setter methods didn't lock or unlock.\n"
			         + "\n\n\n\n");
			Assertions.fail();
		}
		
		
		
	}
	
	
	@Test
	@DisplayName("Test #3: toString")
	void test3() {
		
		Lock l1 = new Lock();
		
		try {
			overridePrintStatements();
			Assertions.assertTrue(l1.toString().contains("closed"));
			l1.open();
			Assertions.assertTrue(l1.toString().contains("open"));
			resetPrintStream();
			System.out.println("Test #3: Passed\n\n\n\n");
			
		}
		catch (Error e) {
			resetPrintStream();
			System.out.println("Test #3: \n"
					 + "   toString() method didn't indicate if the loack was open or closed.\n"
			         + "\n\n\n\n");
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