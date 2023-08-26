package com.vzcloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vzcloud.util.staticvar.StaticKeys;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @version v2.3
 * @ClassName:CodeController.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: CodeController.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Controller
@RequestMapping(value = "/code")
public class CodeController {

    private static final Logger logger = LoggerFactory.getLogger(CodeController.class);
    private int codeCount = 4;//Define the number of verification codes on the picture  
    char[] codeSequence = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    /**
     * get verification code
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "get")
    public void hostInfoList(Model model, HttpServletRequest req, HttpServletResponse resp) {
        int xx = 13;
        int codeY = 22;
        int width = 70;//Define the width of the picture
        int height = 30;//Define the picture of the picture height
        int fontHeight = 20;
        // Define the image buffer  
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//      Graphics2D gd = buffImg.createGraphics();  
        //Graphics2D gd = (Graphics2D) buffImg.getGraphics();  
        Graphics gd = buffImg.getGraphics();
        // Create a random number generator class  
        Random random = new Random();
        // Fill the image into white  
        gd.setColor(Color.getColor("#ef3f22"));
        gd.fillRect(0, 0, width, height);

        // Create fonts, the size of the font should be determined according to the height of the picture.  
        Font font = new Font("ITALIC", 0, fontHeight);
        // Set font.  
        gd.setFont(font);

        // Fringe.  
        gd.setColor(Color.BLACK);
        gd.drawRect(0, 0, width - 1, height - 1);


        // Randomcode is used to save randomly generated verification codes so that users can verify after logging in.  
        StringBuilder randomCode = new StringBuilder();
        int red = 0, green = 0, blue = 0;
        Font font2 = new Font("Fixedsys", Font.BOLD, fontHeight);
        // Set font.  
        gd.setFont(font2);
        // The verification code of the CodeCount number randomly generates.  
        for (int i = 0; i < codeCount; i++) {
            // Get randomly generated verification code numbers.  
            String code = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
            // The random color component is generated to construct the color value, so that the color value of each digital output will be different.  
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);

            // Draw the verification code into the image with the randomly generated color.  
            //gd.setColor(new Color(red, green, blue)); 
            gd.drawString(code, (i + 1) * xx, codeY);
            // Comb with the four random numbers generated.  
            randomCode.append(code);
        }
        // Save the verification code of the four -digit number to the session.  
        HttpSession session = req.getSession();
        session.setAttribute(StaticKeys.SESSION_CODE, randomCode.toString());

        // Forbidden image cache.  
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);

        resp.setContentType("image/jpeg");

        // Output the image into the Servlet output stream.  
        ServletOutputStream sos;
        try {
            sos = resp.getOutputStream();
            ImageIO.write(buffImg, "jpeg", sos);
            sos.close();
        } catch (IOException e) {
            logger.error("Generate verification code abnormal:", e);
        }

    }


}