package com.yourname;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class ApiTest {
 @Test void addition(){assertEquals(4.0, Api.calculate("a=2&op=%2B&b=2"));}
 @Test void divisionZero(){assertThrows(IllegalArgumentException.class,()->Api.calculate("a=1&op=%2F&b=0"));}
 @Test void missingInput(){assertThrows(IllegalArgumentException.class,()->Api.calculate("a=1"));}
}
