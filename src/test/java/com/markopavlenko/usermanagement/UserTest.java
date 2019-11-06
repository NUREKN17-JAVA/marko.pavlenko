package com.markopavlenko.usermanagement;

import junit.framework.TestCase;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class UserTest extends TestCase {
    /** Date of birth*/
    public static final int YEAR_OF_BIRTH = 2000;
    public static final int CURRENT_YEAR = 2019;

    /** Test case #1 День рождения в следующем месяце года*/
    public static final int MONTH_OF_BIRTH1 = Calendar.FEBRUARY;
    public static final int DATE_OF_BIRTH1 = 17;
    public static final int ETALON_AGE1 = CURRENT_YEAR - YEAR_OF_BIRTH - 1;

    /** Test case #2 День рождения сегодня*/ // Change data
    public static final int MONTH_OF_BIRTH2 = Calendar.OCTOBER;
    public static final int DATE_OF_BIRTH2 = 17;
    public static final int ETALON_AGE2 = CURRENT_YEAR - YEAR_OF_BIRTH ;

    /** Test case #3 День рождение в этом месяце (на несколько дней позже)*/
    public static final int MONTH_OF_BIRTH3 = Calendar.OCTOBER;
    public static final int DATE_OF_BIRTH3 = 27;
    public static final int ETALON_AGE3 = CURRENT_YEAR - YEAR_OF_BIRTH -1 ;

    /** Test case #4 День рождения в этом месяце (на несколько дней раньше)*/
    public static final int MONTH_OF_BIRTH4 = Calendar.OCTOBER;
    public static final int DATE_OF_BIRTH4 = 7;
    public static final int ETALON_AGE4 = CURRENT_YEAR - YEAR_OF_BIRTH;

    /** Test case #5 День рождения на месяц раньше */
    public static final int MONTH_OF_BIRTH5 = Calendar.SEPTEMBER;
    public static final int DATE_OF_BIRTH5 = 17;
    public static final int ETALON_AGE5 = CURRENT_YEAR - YEAR_OF_BIRTH;

    private User user;
    private Date dateOfBirth;

    public void testGetFullName() {
        user.setFirstName("Marko");
        user.setLastName("Pavlenko");
        assertEquals("Marko Pavlenko", user.getFullName());
    }
    @Test
    /** Get Age for Test case #1*/
     public void testGetAge1() { //день рождения прошёл но месяц идет
        Calendar calendar = Calendar.getInstance(); 
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH1, DATE_OF_BIRTH1);
        dateOfBirth = calendar.getTime();
        user.setDateOfBirth(dateOfBirth);
        assertEquals(ETALON_AGE1, user.getAge());
    }
     @Test
    /**Get Age for Test case #2*/
    public void testGetAge2() { //день рождения в тот же день
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH2, DATE_OF_BIRTH2);
        dateOfBirth = calendar.getTime();
        user.setDateOfBirth(dateOfBirth);
        assertEquals(ETALON_AGE2, user.getAge());
    }
@Test
    /**Get Age for Test case #3*/
    public void testGetAge3() { //день рождения в тот же день
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH3, DATE_OF_BIRTH3);
        dateOfBirth = calendar.getTime();
        user.setDateOfBirth(dateOfBirth);
        assertEquals(ETALON_AGE3, user.getAge());
    }
@Test
    /**Get Age for Test case #4*/
    public void testGetAge4() { //день рождения в тот же день
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH4, DATE_OF_BIRTH4);
        dateOfBirth = calendar.getTime();
        user.setDateOfBirth(dateOfBirth);
        assertEquals(ETALON_AGE4, user.getAge());
    }
@Test
    /**Get Age for Test case #5*/
    public void testGetAge5() { //день рождения в тот же день
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH5, DATE_OF_BIRTH5);
        dateOfBirth = calendar.getTime();
        user.setDateOfBirth(dateOfBirth);
        assertEquals(ETALON_AGE5, user.getAge());
    }
@Before
    protected void setUp() throws Exception {
        super.setUp();
        user = new User();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        user = new User();
    }

}
