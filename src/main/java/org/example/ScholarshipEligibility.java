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
