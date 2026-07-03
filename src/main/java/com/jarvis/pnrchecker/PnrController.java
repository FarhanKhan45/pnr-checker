package com.jarvis.pnrchecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jarvis.pnrchecker.model.PnrResponse;

@Controller
public class PnrController {

    @Autowired
    private PnrService pnrService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/check-pnr")
    public String checkPnr(@RequestParam String pnr, Model model) {

        try {
            PnrResponse response = pnrService.checkPnr(pnr);
            model.addAttribute("response", response);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
        }

        return "result";
    }
}