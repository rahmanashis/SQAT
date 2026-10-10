package org.example;

public class ScholarshipEligibilitySolution {

    public String checkEligibility(int marks,
                                   int attendance,
                                   boolean disciplinaryRecord,
                                   int familyIncome,
                                   boolean competitionWinner) {
        if (marks < 0 || marks > 100
                || attendance < 0 || attendance > 100
                || familyIncome < 0) {
            return "Invalid";
        }

        if (disciplinaryRecord) {
            return "Not Eligible";
        }

        if (marks == 100) {
            return "Eligible";
        }

        if (marks < 80
                || (!competitionWinner && attendance < 75)
                || familyIncome > 500_000) {
            return "Not Eligible";
        }

        return "Eligible";
    }
}
