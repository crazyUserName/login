package cn.learncoding.controller;

import java.security.PrivateKey;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.learncoding.util.Base64Util;
import cn.learncoding.util.RSAUtil;
import cn.learncoding.util.SHAUtil;
import cn.learncoding.vo.DataVO;

/**   
 * @Description: TODO
 * @date:   2017年7月4日 下午6:24:42   
 *     
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/login")
	public String goLogin(ModelMap model, HttpSession session) {
		setPublicKey(session, model);
		return "login";
	}
	
	
	@PostMapping("/login")
	@ResponseBody
	public DataVO<Object> login(String userName, String password,
			boolean rememberMe, HttpSession session){
		password = dealPassword(userName, password, session, false);
		
		if (!userName.equals("admin") || !password.equals("123456")) {
			return DataVO.errorData("账号信息不匹配"); 
		}
		
		log.info("user {} login success", userName);
		return DataVO.successDefaultData();
	}
	

	
	private void setPublicKey(HttpSession session, ModelMap model){
		Object object = session.getAttribute(RSAUtil.PRIVATE_KEY);
		if (object != null) {
			byte[] bytes = (byte[]) session.getAttribute(RSAUtil.PUBLIC_KEY);
			model.addAttribute(RSAUtil.PUBLIC_KEY, Base64Util.encode(bytes));
		}else{
			Map<String, Object> map = RSAUtil.generateKeyBytes2();
			byte[] bytes = (byte[]) map.get(RSAUtil.PUBLIC_KEY);
			model.addAttribute(RSAUtil.PUBLIC_KEY, Base64Util.encode(bytes));
			session.setAttribute(RSAUtil.PRIVATE_KEY, map.get(RSAUtil.PRIVATE_KEY));
			session.setAttribute(RSAUtil.PUBLIC_KEY, bytes);
		}
	}
	
	private String dealPassword(String userName, String password, HttpSession session, boolean isNeedEncrypt) {
		PrivateKey privateKey = (PrivateKey) session.getAttribute(RSAUtil.PRIVATE_KEY);
		password = RSAUtil.decode(privateKey, Base64Util.decode(password));
		if (isNeedEncrypt) {
			password = SHAUtil.HMACSHA256(password, userName);
		}
		return password;
	}

}
