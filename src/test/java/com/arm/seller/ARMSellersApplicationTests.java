package com.arm.seller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ARMSellersApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	Random random;

	@Test
	void contextLoads() {
		new ARMSellersApplication();
	}

	@Test
	void checkHomeAccessible() throws Exception{
		mockMvc.perform(get("/home?name=Anubhav"))
				.andExpect(status().isOk());
	}

	@Test
	void checkAddNumbersNotAccessible() throws Exception{
		mockMvc.perform(get("/addNumbers"))
				.andExpect(status().is4xxClientError());
	}

	@Test
	void checkRequestByPathVariable1() throws Exception{
		mockMvc.perform(get("/getFactorial/5"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.equalTo(120)));
	}

	@Test
	void checkRequestByPathVariable2() throws Exception{
		int num = random.nextInt(12);
		int fact = 1;
		for(int i=2;i<=num;i++) fact*=i;
		mockMvc.perform(get("/getFactorial/"+num))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.equalTo(fact)));
	}

	@Test
	void checkRequestByRequestBody1() throws Exception{
		mockMvc.perform(post("/getSquare")
						.contentType(MediaType.APPLICATION_JSON)
						.characterEncoding("utf-8")
						.content("5"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$",Matchers.equalTo(25)));
	}

	@Test
	void checkRequestByRequestBody2() throws Exception{
		int num = random.nextInt(1000);
		mockMvc.perform(post("/getSquare")
						.contentType(MediaType.APPLICATION_JSON)
						.characterEncoding("utf-8")
						.content(String.valueOf(num)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$",Matchers.equalTo(num*num)));
	}

	@Test
	void checkRequestByRequestParam1() throws  Exception{
		mockMvc.perform(get("/getSquareRoot?num=144"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$",Matchers.equalTo(12.0)));
	}

	@Test
	void checkRequestByRequestParam2() throws  Exception{
		int num = random.nextInt(1000000);
		mockMvc.perform(get("/getSquareRoot?num="+num))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$",Matchers.equalTo(Math.sqrt(num))));
	}
}
