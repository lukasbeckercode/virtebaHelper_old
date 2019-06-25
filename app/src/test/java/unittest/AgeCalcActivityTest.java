package unittest;

import com.example.virtebahelper.AgeCalcActivity;

import org.junit.Test;

import static org.junit.Assert.*;

public class AgeCalcActivityTest {

    @Test
    public void calcAgeTest() {
        int inputDay = 26;
        int inputMonth = 6;
        int inputYear = 1998;
        int expected = 20;
        int  output;


        AgeCalcActivity ageCalcActivity = new AgeCalcActivity();
        output = ageCalcActivity.calcAge(inputDay,inputMonth,inputYear);

        assertEquals(expected,output);


    }

    @Test
    public void subtractionTest(){
        int inputDay = 18;
        int inputMonth = 7;
        int inputYear = 1998;
        boolean isSubtractedTest;
        AgeCalcActivity ageCalcActivity = new AgeCalcActivity();
        ageCalcActivity.calcAge(inputDay,inputMonth,inputYear);
        isSubtractedTest = AgeCalcActivity.isSubstracted;
        assertTrue(isSubtractedTest);
    }
}