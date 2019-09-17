package ch0911;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.crypto.dsig.keyinfo.KeyInfo;

import org.apache.http.client.CookieStore;
import org.testng.annotations.Test;

import com.edu.qingguo.HttpDriver;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ChangJingTest {
	String address_list_url = "/fgadmin/address/list";
	String fee_url = "/common/getTransportFee";
	String submit_url="";

	@Test
	public void testChangjing1() throws Exception {
//		1、登录（可省略）
		CookieStore cookie = Common.getCookie("20000000005", "netease123");

//		2、获取地址/fgadmin/address/list
		String address_result = HttpDriver.doGet(address_list_url, cookie);
		System.out.println(address_result);
		JSONObject json_address = JSONObject.fromObject(address_result);
		JSONObject address1 = json_address.getJSONObject("result").getJSONArray("list").getJSONObject(0);

		String province = address1.getString("province");
		String city = address1.getString("city");
		String area = address1.getString("area");
		String receiverName = address1.getString("receiverName");
		String cellPhone = address1.getString("cellPhone");
		String addressDetail = address1.getString("addressDetail");

		// 3、获取运费?id=1&=浙江省_杭州市_滨江区
		Map<String, Object> fee_para=new HashMap<String, Object>();
		fee_para.put("id", "1");
		fee_para.put("addressDetail",province+"_"+city+"_"+area);
		String fee_result=HttpDriver.doGet(fee_url, fee_para,cookie);
		System.out.println(fee_result);
//		4、Submit
	}

}
