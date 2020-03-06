package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest( classes = DemoApplication.class )
class DemoApplicationTests
{
	@Autowired
	private WebApplicationContext webAppContext;

	private MockMvc mockMvc;

	@BeforeEach
	public void setUp()
	{
		mockMvc = MockMvcBuilders.webAppContextSetup( webAppContext ).build();
	}

	@Test
	void testIndex() throws Exception
	{
		String actualResult = mockMvc.perform( get( "/" ) ).andReturn().getResponse().getContentAsString();
		String expected = "Welcome to the demo application";
		assertEquals( expected, actualResult );
	}

	@Test
	public void testHello() throws Exception
	{
		mockMvc.perform( get( "/hello" ) ).andExpect( status().isOk() ).andExpect( content().string( "Hello World!" ) );
	}

	@Test
	public void testAbout() throws Exception
	{
		mockMvc.perform( get( "/about" ) ).andExpect( status().isOk() ).andExpect( content().string( "I am a hero here" ) );
	}

}
