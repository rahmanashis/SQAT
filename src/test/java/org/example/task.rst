Software Testing Lab Task: Scholarship Eligibility
=================================================

Task
----

A university uses a system to decide whether a student qualifies for a merit
scholarship. As the Software Test Engineer, write JUnit 5 tests to check the
system against the business requirements below. Do not modify the production
code. The tests should expose any behavior that does not satisfy the
requirements.

The system must return exactly one of these strings:

* ``"Eligible"``
* ``"Not Eligible"``
* ``"Invalid"``

Business requirements
---------------------

1. Marks must be between 0 and 100, inclusive. Otherwise, return
   ``"Invalid"``.
2. Attendance must be between 0 and 100, inclusive. Otherwise, return
   ``"Invalid"``.
3. Family income must not be negative. Otherwise, return ``"Invalid"``.
4. A student must have at least 80 marks; otherwise, return ``"Not Eligible"``.
5. A student must have at least 75% attendance; otherwise, return
   ``"Not Eligible"``.
6. A student with a disciplinary record is ``"Not Eligible"``.
7. Family income must not exceed 500,000 BDT. An income of exactly 500,000
   therefore meets this requirement.
8. A national competition winner is exempt from the attendance threshold.
   All other requirements still apply.
9. A student with exactly 100 marks is eligible regardless of attendance and
   family income, provided there is no disciplinary record. Input validation
   requirements still apply.
10. If multiple invalid inputs are supplied, the result must still be
    ``"Invalid"``.

Supplied production code
------------------------

The implementation under test is expected to provide this class and method:

.. code-block:: java

   package org.example;

   public class ScholarshipEligibility {

       public String checkEligibility(int marks,
                                      int attendance,
                                      boolean disciplinaryRecord,
                                      int familyIncome,
                                      boolean competitionWinner) {

           if (marks < 0 || marks > 100) {
               return "Invalid";
           }

           if (marks < 80) {
               return "Not Eligible";
           }

           if (attendance < 75) {
               return "Not Eligible";
           }

           if (disciplinaryRecord) {
               return "Not Eligible";
           }

           if (familyIncome >= 500000) {
               return "Not Eligible";
           }

           return "Eligible";
       }
   }

Test scenarios
--------------

Use the following scenarios to cover normal behavior, boundaries, invalid
inputs, and special rules. Each row lists the arguments in this order:
``marks, attendance, disciplinaryRecord, familyIncome, competitionWinner``.

============================== ================================== ================
Scenario                       Inputs                             Expected result
============================== ================================== ================
Minimum qualifying thresholds  ``80, 75, false, 0, false``        ``"Eligible"``
Maximum allowed income         ``80, 75, false, 500000, false``   ``"Eligible"``
Marks just below minimum       ``79, 75, false, 0, false``        ``"Not Eligible"``
Attendance just below minimum ``80, 74, false, 0, false``        ``"Not Eligible"``
Disciplinary record            ``80, 75, true, 0, false``         ``"Not Eligible"``
Income above limit             ``80, 75, false, 500001, false``   ``"Not Eligible"``
Winner below attendance limit  ``80, 74, false, 0, true``         ``"Eligible"``
Perfect score waives thresholds``100, 0, false, 600000, false``   ``"Eligible"``
Perfect score with discipline  ``100, 100, true, 0, false``       ``"Not Eligible"``
Negative marks                 ``-1, 75, false, 0, false``        ``"Invalid"``
Marks above maximum            ``101, 75, false, 0, false``       ``"Invalid"``
Negative attendance            ``80, -1, false, 0, false``        ``"Invalid"``
Attendance above maximum       ``80, 101, false, 0, false``       ``"Invalid"``
Negative income                ``80, 75, false, -1, false``       ``"Invalid"``
Multiple invalid values        ``-1, 101, false, -1, false``      ``"Invalid"``
============================== ================================== ================

JUnit 5 test class
------------------

Create ``ScholarshipEligibilityTest`` in ``src/test/java/org/example/`` and
use package ``org.example``. The following tests exercise the scenarios above:

.. code-block:: java

   package org.example;

   import org.junit.jupiter.api.Test;

   import static org.junit.jupiter.api.Assertions.assertAll;
   import static org.junit.jupiter.api.Assertions.assertEquals;

   class ScholarshipEligibilityTest {

       private final ScholarshipEligibility system = new ScholarshipEligibility();

       private String check(int marks, int attendance, boolean disciplinaryRecord,
                            int familyIncome, boolean competitionWinner) {
           return system.checkEligibility(
                   marks, attendance, disciplinaryRecord, familyIncome, competitionWinner);
       }

       @Test
       void acceptsMinimumThresholdsAndMaximumIncome() {
           assertAll(
                   () -> assertEquals("Eligible", check(80, 75, false, 0, false)),
                   () -> assertEquals("Eligible", check(80, 75, false, 500000, false)));
       }

       @Test
       void rejectsApplicantsWhoFailAnEligibilityRule() {
           assertAll(
                   () -> assertEquals("Not Eligible", check(79, 75, false, 0, false)),
                   () -> assertEquals("Not Eligible", check(80, 74, false, 0, false)),
                   () -> assertEquals("Not Eligible", check(80, 75, true, 0, false)),
                   () -> assertEquals("Not Eligible", check(80, 75, false, 500001, false)));
       }

       @Test
       void competitionWinnerIsExemptFromAttendanceThreshold() {
           assertEquals("Eligible", check(80, 74, false, 0, true));
       }

       @Test
       void perfectScoreWaivesAttendanceAndIncomeButNotDiscipline() {
           assertAll(
                   () -> assertEquals("Eligible", check(100, 0, false, 600000, false)),
                   () -> assertEquals("Not Eligible", check(100, 100, true, 0, false)));
       }

       @Test
       void rejectsOutOfRangeMarksAndAttendanceAndNegativeIncomeAsInvalid() {
           assertAll(
                   () -> assertEquals("Invalid", check(-1, 75, false, 0, false)),
                   () -> assertEquals("Invalid", check(101, 75, false, 0, false)),
                   () -> assertEquals("Invalid", check(80, -1, false, 0, false)),
                   () -> assertEquals("Invalid", check(80, 101, false, 0, false)),
                   () -> assertEquals("Invalid", check(80, 75, false, -1, false)));
       }

       @Test
       void multipleInvalidInputsStillReturnInvalid() {
           assertEquals("Invalid", check(-1, 101, false, -1, false));
       }
   }

Passing baseline test cases
---------------------------

The requirements-based tests above include cases the supplied implementation
does not satisfy, so that full suite is expected to report failures. To
demonstrate JUnit tests that pass against the current implementation, add this
separate test class. These baseline cases are not a substitute for the full
requirements coverage:

.. code-block:: java

   package org.example;

   import org.junit.jupiter.api.Test;

   import static org.junit.jupiter.api.Assertions.assertAll;
   import static org.junit.jupiter.api.Assertions.assertEquals;

   class ScholarshipEligibilityPassingTest {

       private final ScholarshipEligibility system = new ScholarshipEligibility();

       @Test
       void acceptsApplicantWhoMeetsAllNormalRequirements() {
           assertEquals(
                   "Eligible",
                   system.checkEligibility(80, 75, false, 499999, false));
       }

       @Test
       void rejectsApplicantsWhoFailImplementedEligibilityChecks() {
           assertAll(
                   () -> assertEquals(
                           "Not Eligible",
                           system.checkEligibility(79, 75, false, 0, false)),
                   () -> assertEquals(
                           "Not Eligible",
                           system.checkEligibility(80, 74, false, 0, false)),
                   () -> assertEquals(
                           "Not Eligible",
                           system.checkEligibility(80, 75, true, 0, false)),
                   () -> assertEquals(
                           "Not Eligible",
                           system.checkEligibility(80, 75, false, 500000, false)));
       }

       @Test
       void rejectsInvalidMarks() {
           assertAll(
                   () -> assertEquals(
                           "Invalid",
                           system.checkEligibility(-1, 75, false, 0, false)),
                   () -> assertEquals(
                           "Invalid",
                           system.checkEligibility(101, 75, false, 0, false)));
       }
   }

Interpreting the result
-----------------------

The tests are requirements-based and may fail against the supplied
implementation. In particular, that implementation does not validate
attendance or negative income, does not apply either special waiver, and
rejects income equal to 500,000. These are test findings; do not change
production code for this task.

Run the test class from the project root with:

.. code-block:: console

   mvn -Dtest=ScholarshipEligibilityTest test

