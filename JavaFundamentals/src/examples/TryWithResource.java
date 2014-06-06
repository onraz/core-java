package examples;


public interface TryWithResource {
	
	class MyResource implements AutoCloseable {
		String name;
		public MyResource(String name) { this.name = name; System.err.println("Creating.." + name);}
		public void doSomething() { System.err.println("Doing.." + name);}
		public void doSomethingError() throws Exception { doSomething(); throw new Exception("Error.." + name); }
		@Override public void close() throws Exception { System.err.println("Closing.." + name); }
	}
	
	class MyFaultyResource extends MyResource {
		public MyFaultyResource(String name) { super(name); }
		@Override public void close() throws Exception { throw new Exception("Error Closing.." + name); }
	}

	static void main(String... args) throws Exception {
		/*
		 * try with resources closes resource before it handles
		 * exception in catch statement or finally statement
		 */
		try(MyResource resource1 = new MyResource("resource-1")) {
			resource1.doSomething();
			resource1.doSomethingError();
		} catch (Exception e) {
			System.err.println("Executing Catch Statement for " + e);
		} finally {
			System.err.println("Executing finally\n");
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
			System.err.println("Executing finally\n");
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
			System.err.println("Executing Catch Statement for " + e);
			System.err.println("....Suppressed Exception is: " + e.getSuppressed()[0]);
		} finally {
			System.err.println("Executing finally");
		}
	}
}
