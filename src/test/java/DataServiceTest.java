import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DataServiceTest {

    @Mock
    private DataService dataServiceMock = Mockito.mock(DataService.class);

    //private DataServiceUser dataServiceUser = new DataServiceUser();

    //DataService dataServiceSpy = Mockito.spy(DataService.class);
    @Test
    public void testData() {
        List<String> data = new ArrayList<>();
        data.add("dataItem");
        Mockito.when(dataServiceMock.getData()).thenReturn(data);
        // или
        //Mockito.doReturn(data).when(dataServiceMock).getData();
    }

    @Test
    public void testGetDataItemById() {
        String s = "idValue";
        Mockito.when(dataServiceMock.getDataById(Mockito.eq(s))).
                thenReturn("dataItem");
        // или
        //Mockito.when(dataServiceMock.getDataById("idValue")).thenReturn("dataItem");

        Mockito.when(dataServiceMock.getDataById(Mockito.argThat(
                arg -> arg == null || arg.length() > 5))).thenReturn("dataItem");




    }
}
