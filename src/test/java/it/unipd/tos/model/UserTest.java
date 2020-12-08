////////////////////////////////////////////////////////////////////
// [SIMONE] [DE RENZIS] [1187510]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

import java.time.LocalDate;
import java.time.Period;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UserTest {

    private User user;

    @Before
    public void setup() {
        user = new User(43,"Pippo", "Pluto", LocalDate.of(1999, 1, 24));
    }
    
    @Test
    public void getIdTest() {
        assertEquals(43, user.getId());
    }

    @Test
    public void getNameTest() {
        assertEquals("Pippo", user.getName());
    }

    @Test
    public void getSurnameTest() {
        assertEquals("Pluto", user.getSurname());
    }

    @Test
    public void getAgeTest() {
        int compareAge = Period.between(LocalDate.of(1999, 1, 24), LocalDate.now()).getYears();
        assertEquals(compareAge, user.getAge());
    }

    @Test
    public void nullBirthDateTest() {
        User userTest = new User(43,"Pippo", "Pluto", null);
        assertEquals(0, userTest.getAge());
    }

}