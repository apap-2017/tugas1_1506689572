package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.KelurahanModel;
import com.example.service.KelurahanService;

@Controller
public class KelurahanController {
	@Autowired
    KelurahanService kelurahanDAO;
	
	/* @RequestMapping("/kelurahan/view")
	 public String view (Model model,
		 @RequestParam(value = "kode_kelurahan", required = false) String kode_kelurahann)
	 {
		 return "";
		 //KelurahanModel kelurahan = kelurahanDAO.selectKelurahan (kode_kelurahan);
		 if (kelurahan != null) {
			 model.addAttribute ("kelurahan", kelurahan);
	         return "view-kelurahan";
		 } else {
			 model.addAttribute ("kode_kelurahan", kode_kelurahan);
	         return "not-found";
	     }
	}*/
}
