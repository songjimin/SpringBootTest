package com.song.sample;


import com.song.sample.model.Person;
import com.song.sample.service.SampleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UnitTest {

    @MockBean
    SampleService mockSampleService;

    @Test
    public void hello2() throws Exception {
        when(mockSampleService.getPerson()).thenReturn(new Person("JIMIN", 33));
    }

}