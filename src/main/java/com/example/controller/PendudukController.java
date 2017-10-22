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
import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;
import com.example.model.PendudukModel;
import com.example.service.PendudukService;
import com.example.service.KeluargaService;
import com.example.service.KotaService;
import com.example.service.KecamatanService;
import com.example.service.KelurahanService;

@Controller
public class PendudukController {
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
	
	@RequestMapping("/")
    public String index ()
    {
        return "index";
    }

	 @RequestMapping("/penduduk")
	 public String view (Model model,
		@RequestParam(value = "nik", required = false) String nik,
	 	@RequestParam(value = "tanggal", required = false) String tanggal,
	 	@RequestParam(value = "bulan", required = false) String bulan,
	 	@RequestParam(value = "tahun", required = false) String tahun)
	 {
		 PendudukModel penduduk = pendudukDAO.selectPenduduk (nik);
		 if (penduduk != null) {
			 KeluargaModel keluarga = keluargaDAO.selectKeluarga(nik);
			 KelurahanModel kelurahan = kelurahanDAO.selectKelurahan(nik);
			 KecamatanModel kecamatan = kecamatanDAO.selectKecamatan(nik);
			 KotaModel kota = kotaDAO.selectKota(nik);
			 
			 String[] tglLahir = penduduk.getTanggal_lahir().split("-");
			 String tahun1 = tglLahir[0];
			 String bulan1 = tglLahir[1];
			 String tanggal1 = tglLahir[2];
			 int bulanFix = Integer.parseInt(bulan1);
			 
			 if (bulanFix == 1) {
				 bulan1 = "Januari";
			 } else  if (bulanFix == 2) {
				 bulan1 = "Februari";
			 } else  if (bulanFix == 3) {
				 bulan1 = "Maret";
			 } else  if (bulanFix == 4) {
				 bulan1 = "April";
			 } else if (bulanFix == 5) {
				 bulan1 = "Mei";
			 } else  if (bulanFix == 6) {
				 bulan1 = "Juni";
			 } else  if (bulanFix == 7) {
				 bulan1 = "Juli";
			 } else  if (bulanFix == 8) {
				 bulan1 = "Agustus";
			 } else  if (bulanFix == 9) {
				 bulan1 = "September";
			 } else  if (bulanFix == 10) {
				 bulan1 = "Oktober";
			 } else if (bulanFix == 11) {
				 bulan1 = "November";
			 } else {
				 bulan1 = "Desember";
			 }
			 
			 
			 model.addAttribute("penduduk", penduduk);
			 model.addAttribute("keluarga", keluarga);
			 model.addAttribute("kelurahan", kelurahan);
			 model.addAttribute("kecamatan", kecamatan);
			 model.addAttribute("kota", kota);
			 model.addAttribute("tanggal", tanggal1);
			 model.addAttribute("bulan", bulan1);
			 model.addAttribute("tahun", tahun1);
			 
	         return "view-penduduk";
		 } else {
			 model.addAttribute ("nik", nik);
	         return "not-found";
	     }
	}
	
	@RequestMapping("penduduk/tambah")
	public String tambahPenduduk() {
		return "form-tambah-penduduk";
	}
		 
	@RequestMapping("/penduduk/tambah/submit")
	public String tambahPendudukHasil (
		@RequestParam(value = "nama", required = false) String nama,
	    @RequestParam(value = "tempat_lahir", required = false) String tempat_lahir,
		@RequestParam(value = "tanggal_lahir", required = false) String tanggal_lahir,
		@RequestParam(value = "jenis_kelamin", required = false) int jenis_kelamin,
		@RequestParam(value = "is_wni", required = false) int is_wni,
		@RequestParam(value = "id_keluarga", required = false) int id_keluarga,
	    @RequestParam(value = "agama", required = false) String agama,
		@RequestParam(value = "pekerjaan", required = false) String pekerjaan,
		@RequestParam(value = "status_perkawinan", required = false) String status_perkawinan,
		@RequestParam(value = "status_dalam_keluarga", required = false) String status_dalam_keluarga,
		@RequestParam(value = "golongan_darah", required = false) String golongan_darah,
		Model model)
	{
		String kodeKecamatan = pendudukDAO.kodeKecamatan(id_keluarga);
		String nikDepan = kodeKecamatan.substring(0,6);
		
		String[] tanggalLahir = tanggal_lahir.split("-");
		String bulan = tanggalLahir[2];
		String tanggal = tanggalLahir[1];
		String tahun = tanggalLahir[0].substring(2, 4);
		

		if (jenis_kelamin == 1) {
			int tgl  = Integer.parseInt(tanggal);
			tgl += 40;
			tanggal = "" + tgl;
		}
		
		String tglFix = "" + tanggal + bulan + tahun;
		nikDepan += tglFix;
		
		int hitungNIK = pendudukDAO.hitungNIK("%"+nikDepan+"%")+1;
		
		String urutanNIK = "" + hitungNIK;
		
		if(hitungNIK/10 == 0) {
			nikDepan += "000" + urutanNIK;
		} else if(hitungNIK/100 == 0) {
			nikDepan += "00" + urutanNIK;
		} else if (hitungNIK/1000 == 0) {
			nikDepan += "0" + urutanNIK;
		}
		
		PendudukModel penduduk = new PendudukModel (0, nikDepan, nama, tempat_lahir, tanggal_lahir, 
				jenis_kelamin, is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, 
				golongan_darah, 0);
	    pendudukDAO.tambahPenduduk (penduduk);
	    model.addAttribute("penduduk", penduduk);
	    return "success-tambah-penduduk";
	 }
	
	@RequestMapping("/penduduk/ubah/{nik}")
    public String updatePendudukIndex (Model model, @PathVariable(value = "nik") String nik)
    {
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		if (penduduk != null) {
			model.addAttribute("penduduk", penduduk);
	    	return "form-update-penduduk";
		} else {
			return "not-found";
		}
    }
	
	@RequestMapping(value = "/penduduk/ubah/{nik}", method = RequestMethod.POST)
	public String updatePenduduk (Model model, PendudukModel penduduk)
	{
	    	boolean flagSukses = true;
	    	
	    	String kodeKecamatan = pendudukDAO.kodeKecamatan(penduduk.getId_keluarga());
	    	String nikDepan = kodeKecamatan.substring(0,6);
	    		
	    	String[] tanggalLahir = penduduk.getTanggal_lahir().split("-");
	    	String bulan = tanggalLahir[2];
	    	String tanggal = tanggalLahir[1];
	    	String tahun = tanggalLahir[0].substring(2, 4);
	    		

	    	if (penduduk.getJenis_kelamin() == 1) {
    			int tgl  = Integer.parseInt(tanggal);
    			tgl += 40;
    			tanggal = "" + tgl;
    		}
	    		
	    	String tglFix = "" + tanggal + bulan + tahun;
	    	nikDepan += tglFix;
	    		
	    	int hitungNIK = pendudukDAO.hitungNIK("%"+nikDepan+"%")+1;
	    		
	    	String urutanNIK = "" + hitungNIK;
	    		
	    	if(hitungNIK/10 == 0) {
	    		nikDepan += "000" + urutanNIK;
	    	} else if(hitungNIK/100 == 0) {
	    		nikDepan += "00" + urutanNIK;
	    	} else if (hitungNIK/1000 == 0) {
	    		nikDepan += "0" + urutanNIK;
	    	}
	    	
	    		
	    	PendudukModel pendudukUpdate = new PendudukModel (penduduk.getId(), nikDepan, penduduk.getNama(), penduduk.getTempat_lahir(), penduduk.getTanggal_lahir(), 
	    		penduduk.getJenis_kelamin(), penduduk.getIs_wni(), penduduk.getId_keluarga(), penduduk.getAgama(), penduduk.getPekerjaan(), 
	    		penduduk.getStatus_perkawinan(), penduduk.getStatus_dalam_keluarga(), penduduk.getGolongan_darah(), penduduk.getIs_wafat());
	    		
	    	    model.addAttribute ("penduduk", pendudukUpdate);
	    	    model.addAttribute("flagSukses", flagSukses);
	    	    model.addAttribute("nikLama", penduduk.getNik());
	    	    pendudukDAO.updatePenduduk(pendudukUpdate);
	    	    
	            return "form-update-penduduk"; 
	}
	
	@RequestMapping(value="/penduduk", method = RequestMethod.POST)
	public String statusKematianPenduduk(@RequestParam(value = "nik", required = false) String nik,
			@RequestParam(value = "id_keluarga", required = false) String id_keluarga,
			Model model) {
		boolean statusKematian = false;
		pendudukDAO.statusKematianPenduduk(nik);
		
		int statusAnggotaKeluarga = pendudukDAO.statusKeluarga(id_keluarga);
		if (statusAnggotaKeluarga < 1) {
			keluargaDAO.statusKK(id_keluarga);
		}
		
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		KeluargaModel keluarga = keluargaDAO.selectKeluarga(nik);
		KelurahanModel kelurahan = kelurahanDAO.selectKelurahan(nik);
		KecamatanModel kecamatan = kecamatanDAO.selectKecamatan(nik);
		KotaModel kota = kotaDAO.selectKota(nik);
		
		model.addAttribute("penduduk", penduduk);
		model.addAttribute("keluarga", keluarga);
		model.addAttribute("kelurahan", kelurahan);
		model.addAttribute("kecamatan", kecamatan);
		model.addAttribute("kota", kota);
		
		model.addAttribute("statusKematian", true);
		model.addAttribute("nik", nik);
		
		return "success-status-kematian";
	}
	
	@RequestMapping("/penduduk/cari-kelurahan")
	public String pendudukSatuKelurahan(@RequestParam(value = "kota", required = false) String kota,
			@RequestParam(value = "kec", required = false) String kec,
			@RequestParam(value = "kel", required = false) String kel,
			Model model) {
		
		boolean sudah = false;
		if (kel != null) {
			model.addAttribute("nama_kota"), kotaDAO.namaKotaById(Integer.parseInt(kota)));
			model.addAttribute("nama_kecamatan", kecamatanDAO.namaKecamatanById(Integer.parseInt(kec));
			model.addAttribute("nama_kelurahan", kelurahanDAO.namaKelurahanById(Integer.parseInt(kel));
			model.addAttribute("kl", kl);
			
			model.addAttribute("sudah", true);
			List<PendudukModel> daftarPenduduk = pendudukDAO.daftarPendudukSatuKelurahan(Integer.parseInt(kel));
			model.addAttribute("daftarPenduduk", daftarPenduduk);
			
		}
		
	}
}