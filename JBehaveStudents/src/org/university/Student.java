package org.university;

public class Student {
	
	private String name;
	private boolean enrolled = false;
	private boolean undergrad = false;
	private String course; 

	public String getCourse() {
		return course;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isUndergrad() {
		return undergrad;
	}

	public void setUndergrad(boolean undergrad) {
		this.undergrad = undergrad;
	}

	public boolean isEnrolled() {
		return enrolled;
	}

	public void setEnrolled(boolean enrolled) {
		this.enrolled = enrolled;
	}

	public void setCourse(String course) {
		this.course = course;
	}
	
}
