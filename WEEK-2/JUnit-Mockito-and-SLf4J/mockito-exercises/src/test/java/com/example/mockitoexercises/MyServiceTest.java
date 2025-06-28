package com.example.mockitoexercises;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

public class MyServiceTest {
    @Test
    public void testExternalApi() {
        ExternalApi mockApi = mock(ExternalApi.class);
        when(mockApi.fetchData()).thenReturn("Mock Data");
        MyService service = new MyService(mockApi);
        String result = service.fetchData();
        assertEquals("Mock Data", result);
    }

    @Test
    public void testVerifyPostDataInteraction() {
        ExternalApi mockApi = mock(ExternalApi.class);
        MyService service = new MyService(mockApi);
        service.sendData("Hello");
        verify(mockApi).postData("Hello"); 
    }

    @Test
    public void testArgumentMatcher() {
        ExternalApi mockApi = mock(ExternalApi.class);
        MyService service = new MyService(mockApi);
        service.sendData("AnyValue");
        verify(mockApi).postData(anyString());
        verify(mockApi).postData(eq("AnyValue"));
    }

    @Test
    public void testVoidMethodInteraction() {
        ExternalApi mockApi = mock(ExternalApi.class);
        doNothing().when(mockApi).logAction(anyString()); 
        MyService service = new MyService(mockApi);
        service.performAction("TEST_ACTION");
        verify(mockApi).logAction("TEST_ACTION");
    }

    @Test
    public void testFetchDataMultipleReturns() {
        ExternalApi mockApi = mock(ExternalApi.class);
        when(mockApi.fetchData())
            .thenReturn("First Call")
            .thenReturn("Second Call")
            .thenReturn("Third Call");
        MyService service = new MyService(mockApi);

        assertEquals("First Call", service.fetchData());
        assertEquals("Second Call", service.fetchData());
        assertEquals("Third Call", service.fetchData());
    }

    @Test
    public void testInteractionOrder() {
        ExternalApi mockApi = mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        service.fetchData();
        service.sendData("data");
        service.performAction("action");

        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).fetchData();
        inOrder.verify(mockApi).postData("data");
        inOrder.verify(mockApi).logAction("action");
    }

    @Test
    public void testVoidMethodThrowsException() {
        ExternalApi mockApi = mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        doThrow(new RuntimeException("Logging failed"))
            .when(mockApi).logAction("FAIL_ACTION");

        assertThrows(RuntimeException.class, () -> {
            service.performAction("FAIL_ACTION");
        });

        verify(mockApi).logAction("FAIL_ACTION");
    }
}