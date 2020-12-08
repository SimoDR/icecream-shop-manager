////////////////////////////////////////////////////////////////////
// [SIMONE] [DE RENZIS] [1187510]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

import java.time.LocalDate;
import java.time.Period;

public class User {
    private String name;
    private String surname;
    private LocalDate birthday;

    public User(String name, String surname, LocalDate birthday) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        if (birthday == null) {
            return 0;
        }
        else {
            return Period.between(birthday, LocalDate.now()).getYears();
        }
    }

}