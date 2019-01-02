package com.edu.restassure;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class JSONDemo {

	@Test
	public void DoubanDemo() {
		Response response = given().config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
				.params("q", "测试").get("https://api.douban.com/v2/book/search");
		response.print();
		// 获取Response 的所有 headers 并输出
		Headers headers = response.getHeaders();
		System.out.println(headers.toString());
		// 获取Response中header名为Content-Type的值
		String contentType = response.getHeader("Content-Type");
		System.out.println("contentType:" + contentType);
		// 校验某个Header 是否存在

		System.out.println(headers.hasHeaderWithName("Server"));
		// 如果Response 的headers不为空则返回true
		System.out.println(headers.exist());
		Map<String, String> cookiesMap = response.cookies();
		for (String key : cookiesMap.keySet()) {
			System.out.println(key + ":" + cookiesMap.get(key));
		}
		System.out.println(response.cookie("bid"));
		// 把Response 的body转成string类型
		System.out.println(response.getBody().asString());
		int count = response.jsonPath().getInt("count");
		System.out.println("count:" + count);
		// 获取所有的 subtitle
		ArrayList<String> subtitles = response.jsonPath().get("books.subtitle");
		for (int i = 0; i < subtitles.size(); i++) {
			System.out.println(subtitles.get(i));
		}
		// 获取特定某个的subtitle
		String subtitle = response.jsonPath().get("books.subtitle[0]");
		System.out.println(subtitle);
		// 获取倒数第二个的subtitle
		String subtitle1 = response.jsonPath().get("books.subtitle[-2]");
		System.out.println(subtitle1);
		// 获取特定tags底下的所有title
		ArrayList<String> tagTitle = response.jsonPath().get("books.tags[2].title");
		for (int i = 0; i < tagTitle.size(); i++) {
			System.out.println(tagTitle.get(i));
		}
		// 获取所有的 title
		ArrayList<ArrayList<String>> tagTitles = response.jsonPath().get("books.tags.title");
		for (int i = 0; i < tagTitles.size(); i++)

		{
			for (int j = 0; j < tagTitles.get(i).size(); j++) {
				System.out.println(tagTitles.get(i).get(j));
			}
			System.out.println("---------------------");
		}
		// 获取Response json里面所有title = "软件测试"的title
		String title = response.jsonPath().get("books.title.findAll{title ->title==\"软件测试\"}").toString();
		System.out.println(title);

//		 获取Response json中 1< numRaters <=20的所有 numRaters
		String numRaters = response.jsonPath()
				.get("books.rating.numRaters.findAll{numRaters -> numRaters>100 && numRaters<=400}").toString();
		System.out.println(numRaters);

//		 获取Response json种title = "像google一样进行软件测试"对应的 author
		String title2 = response.jsonPath().get("books.findAll{it.subtitle==\"像google一样进行软件测试\"}.author").toString();
		System.out.println(title2);


	}
}
