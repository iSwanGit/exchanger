package me.saru.exchanger.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.saru.exchanger.data.CalculateData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class InternalApiTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void 환율_계산_테스트() throws Exception {
        CalculateData.Req reqData = new CalculateData.Req("100", "0.1");

        mockMvc.perform(post("/api/calculate").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(reqData)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.result").value(10));
    }

    @Test
    void 환율_계산_밸리데이션_송금액을_입력하지_않음() throws Exception {
        CalculateData.Req reqData = new CalculateData.Req("", "100");

        mockMvc.perform(post("/api/calculate").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(reqData)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.result").isEmpty());
    }

    @Test
    void 환율_계산_밸리데이션_10000_USD보다_큰금액() throws Exception {
        CalculateData.Req reqData = new CalculateData.Req("12345", "100");

        mockMvc.perform(post("/api/calculate").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(reqData)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.result").isEmpty());
    }

    @Test
    void 환율_계산_밸리데이션_0보다_작거나_바른숫자가_아닌경우() throws Exception {
        CalculateData.Req reqData = new CalculateData.Req("123..", "100");

        mockMvc.perform(post("/api/calculate").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(reqData)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.result").isEmpty());

        reqData = new CalculateData.Req("100", "-123");

        mockMvc.perform(post("/api/calculate").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(reqData)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.result").isEmpty());
    }

}
