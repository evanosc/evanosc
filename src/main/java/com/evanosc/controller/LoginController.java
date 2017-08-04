package com.evanosc.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.evanosc.model.SystemUser;
import com.evanosc.service.ISystemUserService;
import com.evanosc.utils.AjaxResult;
import com.evanosc.utils.ServletUtils;
import com.evanosc.utils.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 登录相关
 * Created by evang on 2017/3/2.
 */
@Controller
public class LoginController extends BaseController{

    @Autowired
    private Producer captchaProducer;

    @Autowired
    private ISystemUserService systemUserService;

    @RequestMapping("captcha.jpg")
    public ModelAndView captcha(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        // 禁止服务器端缓存
        response.setDateHeader("Expires", 0);

        // 设置标准的 HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

        // 设置IE扩展 HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");

        // 设置标准 HTTP/1.0 不缓存图片
        response.setHeader("Pragma", "no-cache");

        // 返回一个 jpeg 图片，默认是text/html(输出文档的MIMI类型)
        response.setContentType("image/jpeg");

        // 为图片创建文本
        String capText = captchaProducer.createText();

        // 将文本保存在session中，这里就使用包中的静态变量吧
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

        // 创建带有文本的图片
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();

        // 图片数据输出
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        return null;
    }

    /**
     * GET 登录
     * @param model
     * @return
     */
    @RequestMapping(value = "/system", method = RequestMethod.GET)
    public ModelAndView login(Model model) {
        return new ModelAndView("system/login/login.jsp");
    }

    /**
     * 登录
     */
    @ResponseBody
    @RequestMapping(value = "/system/login", method = RequestMethod.POST)
    public AjaxResult login(String username, String password, String captcha)throws IOException {
        String kaptcha = ShiroUtils.getKaptcha();
        if(!captcha.equalsIgnoreCase(kaptcha)){
            return fail(false, "验证码错误");
        }

        Subject currentUser = SecurityUtils.getSubject();
        //sha256加密
        password = new Sha256Hash(password).toHex();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try{
            currentUser.login(token);
            SystemUser systemUser = systemUserService.queryByUserName(username);
            systemUserService.updateLogByUserName(systemUser.getUserId(), ServletUtils.getIpAddr(), ServletUtils.getUserBrowser(), ServletUtils.getUserOperatingSystem());
        }catch (UnknownAccountException e) {
            LOGGER.error("该账号不存在!", e);
            return fail(false, "该账号不存在!");
        } catch (DisabledAccountException e) {
            LOGGER.error("该账号已被冻结!", e);
            return fail(false, "该账号已被冻结!");
        } catch (IncorrectCredentialsException e) {
            LOGGER.error("密码错误", e);
            return fail(false, "密码错误");
        } catch (RuntimeException e) {
            LOGGER.error("未知错误,请联系管理员!", e);
            return fail(false, "未知错误,请联系管理员!");
        }
        return success(true);
    }

    /**
     * 退出
     */
    @RequestMapping(value = "/system/logout", method = RequestMethod.GET)
    public ModelAndView logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ModelAndView("system/login/login.jsp");
    }
}
