package com.controller;

import com.model.Employee;
import com.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integrations tests of {@link EmployeeRestController}.
 *
 * @author Sergey Ignatyuk
 * @version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {EmployeeRestControllerTest.EmployeeControllerTestConfiguration.class})
@WebAppConfiguration
public class EmployeeRestControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private EmployeeService employeeService;

    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Configuration
    public static class EmployeeControllerTestConfiguration {

        @Bean
        public EmployeeService employeeService() {
            return mock(EmployeeService.class);
        }

        @Bean
        public EmployeeRestController employeeRestController() {
            return new EmployeeRestController(employeeService());
        }
    }

    @Test
    public void findAllEmployeesTest() throws Exception {
        List<Employee> employeeList = Stream.of(Employee.builder()
                .id(1L)
                .fullName("Nikolai Nikolaev")
                .dateOfBirth(new Date())
                .phoneNumber("111-111-111")
                .emailAddress("nikolai@gmail.com")
                .position("PM")
                .dateOfEmployment(new Date())
                .departmentId(2L)
                .build(), Employee.builder()
                .id(2L)
                .fullName("Sergey Sergeev")
                .dateOfBirth(new Date())
                .phoneNumber("222-222-222")
                .emailAddress("signatuk89@gmail.com")
                .position("Java Developer")
                .dateOfEmployment(new Date())
                .departmentId(1L)
                .build()).collect(Collectors.toList());

        when(employeeService.findAllEmployees()).thenReturn(employeeList);

        mockMvc.perform(MockMvcRequestBuilders.get("/employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$[0].fullName", ))
//                .andExpect(jsonPath("$[0].dateOfBirth", ))
//                .andExpect(jsonPath("$[0].phoneNumber", ))
//                .andExpect(jsonPath("$[0].emailAddress", ))
//                .andExpect(jsonPath("$[0].position", ))
//                .andExpect(jsonPath("$[0].dateOfEmployment", ));
    }
}