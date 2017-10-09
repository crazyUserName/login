package cn.learncoding.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**   
 * @Description: TODO
 * @date:   2017年7月4日 下午6:46:22   
 *     
 */
@Controller
public class CommonController {

	
	@GetMapping({"/", "/index", "/index.html"})
	public String goLogin(ModelMap model, HttpSession session) {
		return "redirect:/user/login";
	}
	
	@GetMapping("/goIndex")
	public String goIndex(){
		return "index";
	}
}
