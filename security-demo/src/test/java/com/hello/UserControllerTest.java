/**
 * 
 */
package com.hello;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.apache.catalina.startup.Tomcat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Administrator
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	//测试之前执行
	@Before
	public void setup() {
		mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	//下载
	@Test
	public void whenUploadSuccess() throws Exception {
			String result = mockMvc.perform(fileUpload("/file")
					.file(new MockMultipartFile("file", "test.txt", "multipart/form-data", 
						"hello upload".getBytes("UTF-8"))))
				.andExpect(status().isOk())//希望成功返回
				.andReturn().getResponse().getContentAsString();
			System.out.println(result);
	}
	//单个测试,只选择一个运行即可
	//多查
	@Test
	public void whenQuerySuccess() throws Exception {
		String result = mockMvc.perform(get("/user")//get方式请求
				//.param("name", "zhangsan")//传入参数,不传有默认
				.param("name", "zhangsan")//传入参数
				.param("age", "18")
				.param("ageTo", "30")
				.param("xxx", "yyy")
				//.param("size", "15")
				//.param("page", "3")//3条
				//.param("sort", "age,desc")//按年龄降序排序
				.contentType(MediaType.APPLICATION_JSON_UTF8))//以json格式返回
				.andExpect(status().isOk())//希望成功返回
				.andExpect(jsonPath("$.length()").value(3))//希望返回3条数据
				.andReturn().getResponse().getContentAsString();//返回结果
		
		System.out.println(result);
	}
	//单查详情
	@Test
	public void whenGetInfoSuccess() throws Exception {
		String result = mockMvc.perform(get("/user/1")//get方式请求
				.contentType(MediaType.APPLICATION_JSON_UTF8))//以json格式返回
				.andExpect(status().isOk())//希望成功返回
				.andExpect(jsonPath("$.name").value("tom"))//希望返回3条数据
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);//这里详情中,age是看不到的
	}
	
	//单查失败
	@Test
	public void whenGetInfoFail() throws Exception {
		String result = mockMvc.perform(get("/user/a")//get方式请求
				.contentType(MediaType.APPLICATION_JSON_UTF8))//以json格式返回
				.andExpect(status().is4xxClientError())//希望成功返回
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);//结果是空
	}
	
	//存
	@Test
	public void whenCreateSuccess() throws Exception {
		//使用时间戳处理
		Date date = new Date();
		System.out.println(date.getTime());
			String content = "{\"name\":null,\"age\":0,\"birthday\":"+date.getTime()+"}";//json串
			String result = mockMvc.perform(post("/user")//post方式请求
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(content))//以json格式返回
				.andExpect(status().isOk())//希望成功返回
				.andExpect(jsonPath("$.id").value(1))//返回1
				
				.andReturn().getResponse().getContentAsString();
			
			System.out.println(result);//与事例不一致
	}
	
	//修
	@Test
	public void whenUpdateSuccess() throws Exception {
		//使用时间戳处理
		//Date date = new Date();//当前时间
		Date date = new Date(LocalDateTime.now().plusYears(1)
				.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
		System.out.println(date.getTime());
		//传入的修改实体
			String content = "{\"id\":\"1\",\"name\":null,\"age\":0,\"birthday\":"+date.getTime()+"}";//json串
			String result = mockMvc.perform(put("/user/1")//get方式请求
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(content))//以json格式返回
					
				//----以下是希望
				.andExpect(status().isOk())//希望成功返回
				.andExpect(jsonPath("$.id").value(1))//返回1
				
				//----返回的数据
				.andReturn().getResponse().getContentAsString();
			
			System.out.println(result);//与事例不一致
	}
	
	//删
	@Test
	public void whenDeleteSuccess() throws Exception {
			mockMvc.perform(delete("/user/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8))//以json格式返回
				.andExpect(status().isOk());//希望成功返回
	}
	

}
