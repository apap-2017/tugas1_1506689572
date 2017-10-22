package com.example.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;
import com.example.model.PendudukModel;
import com.example.service.KecamatanService;
import com.example.service.KeluargaService;
import com.example.service.KelurahanService;
import com.example.service.KotaService;
import com.example.service.PendudukService;

@Controller
public class KeluargaController {
	@Autowired
    PendudukService pendudukDAO;
	
	@Autowired
	KeluargaService keluargaDAO;
	
	@Autowired
	KotaService kotaDAO;
	
	@Autowired
	KelurahanService kelurahanDAO;
	
	@Autowired
	KecamatanService kecamatanDAO;
	
	 @RequestMapping("/keluarga")
	 public String view (Model model,
		 @RequestParam(value = "nkk", required = false) String nkk)
	 {
		 KeluargaModel keluarga = keluargaDAO.selectKeluargaByNKK(nkk);
		 if (keluarga != null) {
			 KelurahanModel kelurahan = kelurahanDAO.selectKelurahanByNKK(nkk);
			 KecamatanModel kecamatan = kecamatanDAO.selectKecamatanByNKK(nkk);
			 KotaModel kota = kotaDAO.selectKotaByNKK(nkk);
			 
			 model.addAttribute("keluarga", keluarga);
			 model.addAttribute("kelurahan", kelurahan);
			 model.addAttribute("kecamatan", kecamatan);
			 model.addAttribute("kota", kota);
			 
	         return "view-keluarga";
		 } else {
			 model.addAttribute ("nkk", nkk);
	         return "not-found";
	     }
	}
	 
	@RequestMapping("keluarga/tambah")
		public String tambahKeluarga() {
			return "form-tambah-keluarga";
		}
		 
		@RequestMapping("/keluarga/tambah/submit")
		public String tambahKeluarga (
		    @RequestParam(value = "alamat", required = false) String alamat,
			@RequestParam(value = "rt", required = false) String rt,
			@RequestParam(value = "rw", required = false) String rw,
			@RequestParam(value = "kelurahan", required = false) String kelurahan,
			@RequestParam(value = "kecamatan", required = false) String kecamatan,
			Model model)
		{
			String nkk = "";
			String kodeKelurahan = keluargaDAO.kodeKelurahan(kelurahan);
			String kodeKecamatan = keluargaDAO.kodeKecamatan(kecamatan);
			String nkkDepan = kodeKecamatan.substring(0,6);
			
			DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
			Date hariIni = new Date();
			String nkkSelanjutnya = dateFormat.format(hariIni);
			
			String[] splitHari = nkkSelanjutnya.split("-");
			String bulan = splitHari[1];
			String tanggal = splitHari[0];
			String tahun = splitHari[2].substring(2, 4);
			
			nkk = "" + nkkDepan + tanggal + bulan + tahun;			
			
			int hitungNKK = keluargaDAO.hitungNKK("%"+nkk+"%")+1;
			
			String urutanNKK = "" + hitungNKK;
			if(hitungNKK/10 == 0) {
				nkk += "000" + urutanNKK;
			} else if(hitungNKK/100 == 0) {
				nkk += "00" + urutanNKK;
			} else if (hitungNKK/1000 == 0) {
				nkk += "0" + urutanNKK;
			}
			
			KeluargaModel keluarga = new KeluargaModel (0, nkk, alamat, rt, rw, 
					kodeKelurahan, 0, null);
			keluargaDAO.tambahKeluarga (keluarga);
		    model.addAttribute("keluarga", keluarga);
		    return "success-tambah-keluarga";
		 }
		
		@RequestMapping("/keluarga/ubah/{nomor_kk}")
	    public String updateKeluarga (Model model, @PathVariable(value = "nomor_kk") String nomor_kk)
	    {

			KeluargaModel keluarga = keluargaDAO.selectKeluargaByNKK(nomor_kk);
			if (keluarga != null) {
				model.addAttribute("keluarga", keluarga);
				model.addAttribute("kelurahan", kelurahanDAO.selectNamaKelurahanById(keluarga.getId_kelurahan()));
				model.addAttribute("kecamatan", kecamatanDAO.selectNamaKecamatanByIdKelurahan(keluarga.getId_kelurahan()));
				model.addAttribute("kota", kotaDAO.selectNamaKotaByIdKelurahan(keluarga.getId_kelurahan()));
		    	return "form-update-keluarga";
			} else {
				return "not-found";
			}
	    }
		
		@RequestMapping(value="/keluarga/ubah/{nomor_kk}", method = RequestMethod.POST)
	    public String updateKeluarga (Model model, KeluargaModel keluarga,
	    		@RequestParam(value = "kelurahan", required = false) String kelurahan,
				@RequestParam(value = "kecamatan", required = false) String kecamatan,
				@RequestParam(value = "kota", required = false) String kota)
	    {	    	
	            boolean flagSukses = true;
	        	
				String kodeKelurahan = keluargaDAO.kodeKelurahan(kelurahan);
				String kodeKecamatan = keluargaDAO.kodeKecamatan(kecamatan);
				//String kodeKota = keluargaDAO.kodeKecamatan(kota);
				String nkkSementara = kodeKecamatan.substring(0,6);
				String nkk = nkkSementara;
				
				DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
				Date hariIni = new Date();
				String nkkSelanjutnya = dateFormat.format(hariIni);
				
				String[] splitHari = nkkSelanjutnya.split("-");
				String bulan = splitHari[1];
				String tanggal = splitHari[0];
				String tahun = splitHari[2].substring(2, 4);
				
				nkkSementara += "" + tanggal + bulan + tahun;			
				
				int hitungNKK = keluargaDAO.hitungNKK("%"+nkkSementara+"%")+1;
				
				String urutanNKK = "" + hitungNKK;
				if(hitungNKK/10 == 0) {
					nkkSementara += "000" + urutanNKK;
				} else if(hitungNKK/100 == 0) {
					nkkSementara += "00" + urutanNKK;
				} else if (hitungNKK/1000 == 0) {
					nkkSementara += "0" + urutanNKK;
				}
				
				keluarga.setNomor_kk(nkkSementara);
				keluarga.setId_kelurahan(kodeKelurahan);
				
				List<PendudukModel> anggotaKeluarga = keluargaDAO.selectAnggotaKeluargaByIdKeluarga(keluarga.getId());
				
				for(int i = 0; i < anggotaKeluarga.size(); i++) {
					PendudukModel tempPenduduk = anggotaKeluarga.get(i);
					String nikBaru = nkk + tempPenduduk.getNik().substring(6,12);
					int hitungNIK = pendudukDAO.hitungNIK(nikBaru) + 1;
					
					String urutanNIK = "" + hitungNIK;
					if(hitungNIK/10 == 0) {
						nkkSementara += "000" + urutanNIK;
					} else if(hitungNIK/100 == 0) {
						nkkSementara += "00" + urutanNIK;
					} else if (hitungNIK/1000 == 0) {
						nkkSementara += "0" + urutanNIK;
					}
					
					tempPenduduk.setNik(nikBaru);
					pendudukDAO.updatePenduduk(tempPenduduk);
				}
				
				KeluargaModel keluargaUpdate = new KeluargaModel (keluarga.getId(), keluarga.getNomor_kk(), keluarga.getAlamat(), keluarga.getRt(), keluarga.getRw(), 
						kodeKelurahan, 0, null);
	        	
			    model.addAttribute ("keluarga", keluargaUpdate);
	    	    model.addAttribute("flagSukses", flagSukses);
	    	    model.addAttribute("nomorKkLama", keluarga.getNomor_kk());
	    	    keluargaDAO.updateKeluarga(keluargaUpdate);
	    	    return "form-update-keluarga"; 
	    }
}
