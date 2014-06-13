package org.student.test;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.university.RegistrationService;
import org.university.Student;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class RegistrationSteps {
	
	RegistrationService service = new RegistrationService();
	Student student;
	
	@Given("An Undergrad Student with name $studentName")
	public void anUndergradStudent(String studentName) {
		student = new Student();
		student.setName(studentName);
		student.setUndergrad(true);
		
//		Webdriver driver = new WebDriver();
//		driver.go("");
//		driver.click
		
	}
	
	@Given("A Postgrad Student")
	public void aPostgradStudent() {
		student = new Student();
		student.setUndergrad(false);
	}
	
	@When("Student registers for course")
	public void whenRegister() {
		service.register(student, "science");
	}
	

	@Then("Student should be enrolled")
	public void thenEnrolled() {
		assertTrue(student.isEnrolled());
	}

	@Then("Student should not be enrolled")
	public void thenNotEnrolled() {
		assertFalse(student.isEnrolled());
	}
}
