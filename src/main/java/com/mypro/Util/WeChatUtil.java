package com.mypro.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.mypro.entity.AccessToken;
import com.mypro.entity.po.Button;
import com.mypro.entity.po.ClickButton;
import com.mypro.entity.po.Menu;
import com.mypro.entity.po.ViewButton;
import com.mypro.repository.AccessTokenRepository;

/**
 * @author chen
 *
 */
public class WeChatUtil {

	private static final String APPID = "wx51d131093063b255";

	private static final String APPSECRET = "53afc15ecc91e23958882124661148f5";

	private static final String getWeChatAccessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	private static final String initMenuURL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	@Autowired
	private AccessTokenRepository accessTokenRepository;

	/**
	 * 通过微信接口申请获得AccessToken
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static AccessToken getWeChatAccessTokenFromWeChatInf()
			throws ParseException, IOException {
		AccessToken accessToken = new AccessToken();
		String url = getWeChatAccessTokenUrl.replace("APPID", APPID).replace(
				"APPSECRET", APPSECRET);
		JSONObject json = doGet(url);
		if (json.get("errcode") != null) {

		} else {
			accessToken.setAccessToken(json.getString("access_token"));
			accessToken.setExpireTime(json.getInt("expires_in"));
			accessToken.setSaveTime(new Date());
		}
		return accessToken;
	}

	/**
	 * get请求,对微信接口获取到的结果进行json封装
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static JSONObject doGet(String url) {
		JSONObject jsonObject = null;
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		try {
			HttpResponse response = httpclient.execute(get);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String result = EntityUtils.toString(entity, "UTF-8");
				jsonObject = JSONObject.fromObject(result);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 * post请求,对微信接口获取到的结果进行json封装
	 * 
	 * @param url
	 * @param param
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static JSONObject doPostStr(String url, String param)
			throws ClientProtocolException, IOException {
		JSONObject jsonObject = null;
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		post.setEntity(new StringEntity(param, "UTF-8"));
		HttpResponse req = httpclient.execute(post);
		HttpEntity entity = req.getEntity();
		if (entity != null) {
			String result = EntityUtils.toString(entity, "UTF-8");
			jsonObject = JSONObject.fromObject(result);
		}
		return jsonObject;
	}

	/**
	 * 从本地数据库中获取AccessToken
	 * 
	 * @param id
	 * @return
	 */
	public AccessToken getAccessTokenFromLocalDB() {
		AccessToken accessToken = null;
		List<AccessToken> list = accessTokenRepository.findAll();
		accessToken = list.get(0);
		return accessToken;
	}

	/**
	 * 比较当前时间和上一次accestoken保存的时间,大于3600秒返回true,否则返回false
	 * 
	 * @param currentTime
	 * @param saveTime
	 * @return
	 */
	public boolean equalsCurrentTimeWithSaveTime(Date currentTime, Date saveTime) {
		if (currentTime == null || saveTime == null) {
			throw new NullPointerException(
					"currentTime or saveTime is null,please check your param");
		} else {
			Long differ = currentTime.getTime() - saveTime.getTime();
			return differ > 3600 ? true : false;
		}
	}

	/**
	 * 获取token
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static String getAccessToken() throws ParseException, IOException {
		// AccessToken accessToken = null;
		// accessToken = this.getAccessTokenFromLocalDB();
		// boolean flag = this.equalsCurrentTimeWithSaveTime(new Date(),
		// accessToken.getSaveTime());
		String Access_Token = null;
		// if (flag) {
		Access_Token = getWeChatAccessTokenFromWeChatInf().getAccessToken();
		// } else {
		// Access_Token = this.getAccessTokenFromLocalDB().getAccessToken();
		// }
		return Access_Token;
	}

	/**
	 * 初始化自定义菜单
	 * 
	 * @return
	 */
	public static Menu initMenu() {
		Menu menu = new Menu();
		ClickButton button11 = new ClickButton();
		button11.setName("click菜单");
		button11.setType("click");
		button11.setKey("11");

		ViewButton button21 = new ViewButton();
		button21.setName("view菜单");
		button21.setType("view");
		button21.setUrl("http://www.imooc.com");

		ClickButton button31 = new ClickButton();
		button31.setName("扫码事件");
		button31.setType("scancode_push");
		button31.setKey("31");

		ClickButton button32 = new ClickButton();
		button32.setName("地理位置");
		button32.setType("location_select");
		button32.setKey("32");

		Button button = new Button();
		button.setName("菜单");
		button.setSub_button(new Button[] { button31, button32 });

		menu.setButton(new Button[] { button11, button21, button });
		return menu;
	}

	public static int createMenu() throws ParseException, IOException {
		JSONObject jsonObject = null;
		int result = 0;
		String token = getAccessToken();
		String url = initMenuURL.replace("ACCESS_TOKEN", token);
		String menu = JSONObject.fromObject(initMenu()).toString();
		jsonObject = doPostStr(url, menu);
		if (jsonObject != null) {
			result = jsonObject.getInt("errcode");
		}
		return result;
	}
}
