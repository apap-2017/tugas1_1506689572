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

import com.example.model.KecamatanModel;
import com.example.service.KecamatanService;

@Controller
public class KecamatanController {
	@Autowired
    KecamatanService kecamatanDAO;
	
	/* @RequestMapping("/kecamatan/view")
	 public String view (Model model,
		 @RequestParam(value = "kode_kecamatan", required = false) String kode_kecamatan)
	 {
		 KecamatanModel kecamatan = kecamatanDAO.selectKecamatan (kode_kecamatan);
		 if (kecamatan != null) {
			 model.addAttribute ("kecamatan", kecamatan);
	         return "view-kecamatan";
		 } else {
			 model.addAttribute ("kode_kecamatan", kode_kecamatan);
	         return "not-found";
	     }
	}*/
}
