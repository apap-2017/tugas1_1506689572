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

import com.example.model.KotaModel;
import com.example.service.KotaService;

@Controller
public class KotaController {
	@Autowired
    KotaService kotaDAO;
	
	 /*@RequestMapping("/kota/view")
	 public String view (Model model,
		 @RequestParam(value = "kode_kota", required = false) String kode_kota)
	 {
		 KotaModel kota = kotaDAO.selectKota (kode_kota);
		 if (kota != null) {
			 model.addAttribute ("kota", kota);
	         return "view-kota";
		 } else {
			 model.addAttribute ("kode_kota", kode_kota);
	         return "not-found";
	     }
	}*/
}
