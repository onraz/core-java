package core;


public interface TryWithResource {
	
	class MyResource implements AutoCloseable {
		String name;
		public MyResource(String name) { this.name = name; System.err.println("\nCreating.." + name);}
		public void doSomething() { System.err.println("Doing.." + name);}
		public void doSomethingError() throws Exception { doSomething(); throw new NullPointerException("Error.." + name); }
		@Override public void close() throws Exception { System.err.println("Closing.." + name); }
	}
	
	class MyFaultyResource extends MyResource {
		public MyFaultyResource(String name) { super(name); }
		@Override public void close() throws Exception { throw new IllegalArgumentException("Error Closing.." + name); }
	}

	static void main(String... args) throws Exception {
		
		try {
			/*
			 * Observe the behaviour without try-without-resource
			 * 	1. Resource need to be declared outside try block
			 * 	2. Resource needs to be closed in finally
			 * 	3. The exception thrown is the one in close() statement, not the actual error
			 */
			MyResource resource = null;
			try {
				resource = new MyFaultyResource("resource-0");
				resource.doSomething();
				resource.doSomethingError();
			} finally {
				System.err.println("Executing finally");
				if (resource != null) resource.close();
			}
			/* Notice how you can catch multiple exceptions using | operator */
		} catch(IllegalArgumentException | NullPointerException ex) {
			// the exception from resource.close()
			ex.printStackTrace();
		}
		
		try {
			/*
			 * try with resources closes resource before it handles
			 * exception in catch statement or finally statement
			 * 
			 * 	Note: Unlike ordinary try blocks, try-with-resource suppresses close() exceptions
			 */
			try(MyResource resource1 = new MyResource("resource-1")) {
				resource1.doSomething();
				resource1.doSomethingError();
			} finally {
				System.err.println("Executing finally");
			}
		} catch (Exception ex) {
			// the exception from resource1.doSomethingError()
			ex.printStackTrace();
		}
		
		/*
		 * try with resources closes in Reverse Order
		 */
		try(MyResource resource1 = new MyResource("resource-1");
			MyResource resource2 = new MyResource("resource-2")) {
			resource1.doSomething();
			resource2.doSomethingError();
		} catch (Exception e) {
			System.err.println("Executing Catch Statement for " + e);
		} finally {
			System.err.println("Executing finally");
		}
		
		/*
		 * try with resources suppresses close errors
		 * here resource2 had a close error which got suppressed
		 * by the error thrown by resource2.doSomethingError()
		 */
		try(MyResource resource1 = new MyResource("resource-1");
			MyResource resource2 = new MyFaultyResource("resource-2")) {
			resource1.doSomething();
			resource2.doSomethingError();
		} catch (Exception e) {
			/*
			 * Note that the close statement is executed before catch(), so 
			 * the suppressed exception is available here
			 */
			System.err.println("Executing Catch Statement for " + e);
			System.err.println("....Suppressed Exception is: " + e.getSuppressed()[0]);
		} finally {
			System.err.println("Executing finally");
		}
	}
}
