//package com.jayjav.coronavirustracker.services;
//
//import com.jayjav.coronavirustracker.models.LocationStats;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.Mockito.doReturn;
//
//@SpringBootTest
//public class CoronaVirusDataServiceTests {
//
//    @Autowired
//    private CoronaVirusDataService coronaVirusDataService;
//
//    @Test
//    @DisplayName("Test TotalReportedCases Found")
//    void getTotalReportedCasesFound(){
//         int totalReportedCases = coronaVirusDataService.getTotalReportedCases();
//         System.out.println(">>>>>>>>>>>>>>>" + totalReportedCases);
//        Assertions.assertTrue(totalReportedCases > 0);
//    }
//}
