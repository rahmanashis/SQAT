package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ScholarshipEligibilitySolutionTest {

    private final ScholarshipEligibilitySolution system =
            new ScholarshipEligibilitySolution();

    @Test
    void acceptsMinimumAndMaximumValidMarks() {
        assertAll(
                () -> assertEquals("Not Eligible", check(0, 75, false, 0, false)),
                () -> assertEquals("Not Eligible", check(79, 75, false, 0, false)),
                () -> assertEquals("Eligible", check(80, 75, false, 0, false)),
                () -> assertEquals("Eligible", check(100, 75, false, 0, false)));
    }

    @Test
    void rejectsMarksOutsideInclusiveRangeAsInvalid() {
        assertAll(
                () -> assertEquals("Invalid", check(-1, 75, false, 0, false)),
                () -> assertEquals("Invalid", check(101, 75, false, 0, false)));
    }

    @Test
    void validatesAttendanceRangeAndMinimum() {
        assertAll(
                () -> assertEquals("Not Eligible", check(80, 0, false, 0, false)),
                () -> assertEquals("Not Eligible", check(80, 74, false, 0, false)),
                () -> assertEquals("Eligible", check(80, 75, false, 0, false)),
                () -> assertEquals("Eligible", check(80, 100, false, 0, false)),
                () -> assertEquals("Invalid", check(80, -1, false, 0, false)),
                () -> assertEquals("Invalid", check(80, 101, false, 0, false)));
    }

    @Test
    void validatesFamilyIncomeAndIncludesTheMaximumLimit() {
        assertAll(
                () -> assertEquals("Invalid", check(80, 75, false, -1, false)),
                () -> assertEquals("Eligible", check(80, 75, false, 0, false)),
                () -> assertEquals("Eligible", check(80, 75, false, 500_000, false)),
                () -> assertEquals("Not Eligible", check(80, 75, false, 500_001, false)));
    }

    @Test
    void disciplinaryRecordAlwaysMakesStudentNotEligible() {
        assertAll(
                () -> assertEquals("Not Eligible", check(80, 75, true, 0, false)),
                () -> assertEquals("Not Eligible", check(100, 100, true, 0, false)));
    }

    @Test
    void winnerWaivesAttendanceButMustMeetAllOtherRules() {
        assertAll(
                () -> assertEquals("Eligible", check(80, 0, false, 500_000, true)),
                () -> assertEquals("Invalid", check(80, -1, false, 0, true)),
                () -> assertEquals("Invalid", check(80, 101, false, 0, true)),
                () -> assertEquals("Not Eligible", check(79, 0, false, 0, true)),
                () -> assertEquals("Not Eligible", check(80, 0, true, 0, true)),
                () -> assertEquals("Not Eligible", check(80, 0, false, 500_001, true)));
    }

    @Test
    void perfectScoreWaivesAttendanceAndIncomeButNotDiscipline() {
        assertAll(
                () -> assertEquals("Eligible", check(100, 0, false, 600_000, false)),
                () -> assertEquals("Eligible", check(100, 100, false, 500_000, false)),
                () -> assertEquals("Not Eligible", check(100, 75, true, 0, false)));
    }

    @Test
    void invalidInputsAreStillRejectedForPerfectScores() {
        assertAll(
                () -> assertEquals("Invalid", check(100, -1, false, 0, false)),
                () -> assertEquals("Invalid", check(100, 101, false, 0, false)),
                () -> assertEquals("Invalid", check(100, 75, false, -1, false)));
    }

    @Test
    void multipleInvalidInputsReturnInvalid() {
        assertEquals("Invalid", check(-1, 101, false, -1, false));
    }

    private String check(int marks,
                         int attendance,
                         boolean disciplinaryRecord,
                         int familyIncome,
                         boolean competitionWinner) {
        return system.checkEligibility(
                marks, attendance, disciplinaryRecord, familyIncome, competitionWinner);
    }
}
