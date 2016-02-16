import com.jeannius.NumvericValidator.NumericValidator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



/**
 * Created by Jlarrieux on 1/13/2016.
 */
public class NumericValidatorTest {


    static NumericValidator NUM;
    static Field privateField, privateErrorString;



    @BeforeClass
    public static void superSetUp() throws NoSuchFieldException, IllegalAccessException {
        NUM = new NumericValidator(NumericValidator.TypeOfNumber.DOUBLE);

        privateField = NumericValidator.class.getDeclaredField("currentTextField");
        privateField.setAccessible(true);

    }



    @Test
    public void testLengthValidationOnEmpty() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        JTextField text = new JTextField();
        privateField.set(NUM, text);
        Method privateMethod = NumericValidator.class.getDeclaredMethod("lengthValidation", null);
        privateMethod.setAccessible(true);
        Assert.assertFalse((Boolean) privateMethod.invoke(NUM));
    }



    @Test
    public void testLengthValidationOnNotEmpty() throws Exception {
        JTextField text = new JTextField("a");
        privateField.set(NUM, text);
        Method privateMethod = NumericValidator.class.getDeclaredMethod("lengthValidation", null);
        privateMethod.setAccessible(true);
        Assert.assertTrue((Boolean) privateMethod.invoke(NUM));
    }



    @Test
    public void testIsNumericTypeIntegerFalse() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        JTextField text = new JTextField("a");
        privateField.set(NUM, text);
        Method privateMethod = NumericValidator.class.getDeclaredMethod("isNumericTypeInteger", null);
        privateMethod.setAccessible(true);
        Assert.assertFalse((Boolean) privateMethod.invoke(NUM));


        text.setText("9.5");
        privateField.set(NUM, text);
        Assert.assertFalse((Boolean) privateMethod.invoke(NUM));

        text.setText("9.5.");
        privateField.set(NUM, text);
        Assert.assertFalse((Boolean) privateMethod.invoke(NUM));

        text.setText("9.5.6");
        privateField.set(NUM, text);
        Assert.assertFalse((Boolean) privateMethod.invoke(NUM));
    }




    @Test
    public void testIsNumericTypeIntegerTrue() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        JTextField text = new JTextField("12345");
        privateField.set(NUM, text);
        Method privateMethod = NumericValidator.class.getDeclaredMethod("isNumericTypeInteger", null);
        privateMethod.setAccessible(true);
        Assert.assertTrue((Boolean) privateMethod.invoke(NUM));
    }


    @Test
    public void testIsNumericTypeDoubleFalse() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        JTextField text = new JTextField("a");
        privateField.set(NUM, text);
        Method privateMethod = NumericValidator.class.getDeclaredMethod("isNumericTypeDouble", null);
        privateMethod.setAccessible(true);
        Assert.assertFalse((Boolean) privateMethod.invoke(NUM));

        text.setText("9.5.");
        privateField.set(NUM, text);
        Assert.assertFalse((Boolean) privateMethod.invoke(NUM));

        text.setText("9.5.5");
        privateField.set(NUM, text);
        Assert.assertFalse((Boolean) privateMethod.invoke(NUM));
    }

    @Test
    public void testIsNumericTypeDoubleTrue() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        JTextField text = new JTextField("12345");
        privateField.set(NUM, text);
        Method privateMethod = NumericValidator.class.getDeclaredMethod("isNumericTypeDouble", null);
        privateMethod.setAccessible(true);
        Assert.assertTrue((Boolean) privateMethod.invoke(NUM));

        text.setText("9.55");
        privateField.set(NUM, text);
        Assert.assertTrue((Boolean) privateMethod.invoke(NUM));

    }






}