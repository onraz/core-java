package org.university;

public class RegistrationService {

	public void register(Student student, String course) {
		if (student.isUndergrad()) {
			student.setEnrolled(true);
			student.setCourse(course);
		}
	}
}
