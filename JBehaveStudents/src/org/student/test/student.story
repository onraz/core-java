Narrative:
This user story describes how a student enrols to a course.


Scenario: An undergrad student enrollment

Given An Undergrad Student with name Raz
When Student registers for course
Then Student should be enrolled

Scenario: A Postgrad student enrollment

Given A Postgrad Student
When Student registers for course
Then Student should be enrolled


Scenario: Undergrad course not available

Given An Undergrad Student with name Dina
When Student registers for course
Then Student should not be enrolled