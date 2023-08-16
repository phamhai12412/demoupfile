package bt.uploadfile.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("")
@PropertySource("classpath:upfile.properties")
public class HomeController {
    @Value("${upload-path}")

  private String upload;
    @GetMapping("")
    public String form(){
        return "form";
    }
    @PostMapping("/upload")
    public ModelAndView home(@RequestParam("image")MultipartFile image) throws IOException {

        File file=new File(upload);
        if(!file.exists()){
            file.mkdirs();
        }
        String filename=image.getOriginalFilename();
        FileCopyUtils.copy(image.getBytes(),new File(upload+filename));
        ModelAndView modelAndView=new ModelAndView("/home","image",filename);
        return modelAndView;
    }
}
