package com.sadhin.sadhin.dice.roller
import junit.framework.TestCase.*
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 3)
    }
    @Test
    fun generates_number() {
        val dice = Dice(6)
        val rollResult = dice.roll()
        assertTrue("The value of rollResult was not between 1 and 6", rollResult in 1..3)
    }
    @Test
    fun oddEven_nuhmber(){
        val dice = Dice(6)
        assertTrue(dice.oddEven())
    }
    @Test
    fun equalRoll(){
        val dice = Dice(6)
        assertFalse("not equal roll",dice.twoRollEqual())
    }
    @Test
    fun zeroNumber(){
        val dice=Dice(0)
        assertNotNull("no dice like this exist",dice.rollValidation())
    }
}