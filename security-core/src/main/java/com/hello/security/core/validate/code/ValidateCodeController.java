package com.hello.security.core.validate.code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.hello.security.core.properties.SecurityProperties;

/**
 * @author Administrator
 * 获取验证码
 */
@RestController
public class ValidateCodeController {
	
	static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
	
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@GetMapping("/code/image")
	public void createCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
		ImageCode imageCode = createImageCode(new ServletWebRequest(request));//创建验证码
		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);//放入session
		ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());//写入到响应中
	}
	
	/**
	 * 创建验证码
	 * @param request
	 * @return
	 */
	private ImageCode createImageCode(ServletWebRequest request) {
		//宽和高需要从request来取，如果没有传递，再从配置的值来取
        //验证码宽和高
        int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width", securityProperties.getCode().getImage().getWidth());
        int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height", securityProperties.getCode().getImage().getHeight());
        //int width = 100;
        //int height = 30;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        Random random = new Random();

        graphics.setColor(getRandColor(200,250));
        graphics.fillRect(0, 0, width, height);
        graphics.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        graphics.setColor(getRandColor(160,200));
        for(int i=0;i<155;i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            graphics.drawLine(x, y, x+xl, y+yl);
        }
        String sRand = "";
        //验证码长度
        for (int i = 0; i < 155; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand +=rand;
            graphics.setColor(new Color(20, random.nextInt(110), 20+random.nextInt(110),20+random.nextInt(110)));
            graphics.drawString(rand, 13*i+6, 16);
        }
        graphics.dispose();
        //过期时间
        return new ImageCode(image, sRand, securityProperties.getCode().getImage().getExpireIn());
	}

	//随机生成背景条纹
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc>255) {
            fc = 255;
        }
        if (bc>255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc-fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
