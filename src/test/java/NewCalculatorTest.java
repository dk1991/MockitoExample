import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.Iterator;

@RunWith(MockitoJUnitRunner.class)
public class NewCalculatorTest {
    @Mock
    ICalculator mockcalc;

    // используем аннотацию @InjectMocks для создания mock объекта
    @InjectMocks
    NewCalculator calc = new NewCalculator(mockcalc);

    @Test
    public void testCalcAdd() {
        // т.к. import static org.mockito.Mockito.*; можем сразу писать методы
        when(calc.add(10.0, 20.00)).thenReturn(30.0);

        // проверяем действие сложения
        assertEquals(calc.add(10, 20), 30.0, 0);
        // проверяем выполнение действия
        verify(mockcalc).add(10.0, 20.0);

        // определение поведения с использованием doReturn
        doReturn(15.0).when(mockcalc).add(10.0, 5.0);

        // проверяем действие сложения
        assertEquals(calc.add(10.0, 5.0), 15.0, 0);
        verify(mockcalc).add(10.0, 5.0);
    }

    @Test
    public void testCallMethod() {
        // определяем поведение (результаты)
        when(mockcalc.subtract(15.0, 25.0)).thenReturn(10.0);
        when(mockcalc.subtract(35.0, 25.0)).thenReturn(-10.0);

        // вызов метода subtract и проверка результата
        assertEquals(calc.subtract(15.0, 25.0), 10, 0);
        assertEquals(calc.subtract(15.0, 25.0), 10, 0);

        assertEquals(calc.subtract(35.0, 25.0), -10, 0);

        // проверка вызовов методов
        verify(mockcalc, atLeastOnce()).subtract(35.0, 25.0);
        verify(mockcalc, atLeast(2)).subtract(15.0, 25.0);

        // был ли метод вызван дважды?
        verify(mockcalc, times(2)).subtract(15.0, 25.0);

        // метод не был вызван ни разу
        verify(mockcalc, never()).divide(10.0, 20.0);

        /*
            Если снять коммент, будет исключительная ситуация
         */
        //verify(mockcalc, atLeast(2)).subtract(35.0, 25.0);

        /*
            Если снять коммент, будет исключительная ситуация
         */
        //verify(mockcalc, atMost(1)).subtract(15.0, 25.0);
    }

    @Test
    public void testDivide() {
        when(mockcalc.divide(15.0, 3)).thenReturn(5.0);

        assertEquals(calc.divide(15.0, 3), 5.0, 0);
        // проверка вызова метода
        verify(mockcalc).divide(15.0, 3);

        // создаем исключение
        RuntimeException exception = new RuntimeException("Division by zero");
        // определяем поведение
        doThrow(exception).when(mockcalc).divide(15.0, 0);

        assertEquals(calc.divide(15.0, 0), 0.0, 0);
        verify(mockcalc).divide(15.0, 0);
    }

    // метод обработки ответа
    private Answer<Double> answer = new Answer<Double>() {
        @Override
        public Double answer(InvocationOnMock invocationOnMock) throws Throwable {
            // получение mock объекта
            Object mock = invocationOnMock.getMock();
            System.out.println("mock object : " + mock.toString());

            // аргументы метода, переданные mock
            Object[] args = invocationOnMock.getArguments();
            double d1 = (double) args[0];
            double d2 = (double) args[1];
            double d3 = d1 + d2;
            System.out.println("" + d1 + " + " + d2);

            return d3;
        }
    };

    @Test
    public void testThenAnswer() {
        // определение поведения mock для метода с параметрами
        when(mockcalc.add(11.0, 12.0)).thenAnswer(answer);
        assertEquals(calc.add(11.0,12.0), 23.0,0);
    }

    @Test
    public void testSpy() {
        NewCalculator scalc = spy(new NewCalculator());
        when(scalc.returnDouble15()).thenReturn(23.0);

        // вызов реального метода реального класса
        scalc.returnDouble15();
        // проверка вызова метода
        verify(scalc).returnDouble15();

        // проверка возвращаемого значения
        assertEquals(23.0, scalc.returnDouble15(), 0);
        // проверка вызова метода не менее 2-х раз
        verify(scalc,atLeast(2)).returnDouble15();
    }

    @Test
    public void testTimeout() {
        // определение результирующего значения mock для метода
        when(mockcalc.add(11.0, 12.0)).thenReturn(23.0);

        // проверка значения
        assertEquals(calc.add(11.0, 12.0), 23.0, 0);

        // проверка вызова метода в течение 100 мс
        verify(mockcalc, timeout(100)).add(11.0, 12.0);
    }

    @Test
    public void testJavaClasses() {
        // создание объекта mock
        Iterator<String> mis = mock(Iterator.class);
        // формирование ответов
        when(mis.next()).thenReturn("Привет").thenReturn("Mockito");
        // формируем строку из ответов
        String result = mis.next() + ", " + mis.next();
        // check
        assertEquals("Привет, Mockito", result);
    }
}
