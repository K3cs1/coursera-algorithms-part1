package org.coursera.module12.interview_questions.hashing_with_wrong_hashcode_or_equals;

import java.util.Objects;

// The right implementation
public class OlympicAthlete {
    private final String name;
    private final String country;
    private final int medals;

    public OlympicAthlete(String name, String country, int medals) {
        this.name = name;
        this.country = country;
        this.medals = medals;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OlympicAthlete that = (OlympicAthlete) obj;
        return medals == that.medals &&
                Objects.equals(name, that.name) &&
                Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, medals);
    }

}
