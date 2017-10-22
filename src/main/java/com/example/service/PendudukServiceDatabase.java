package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PendudukMapper;
import com.example.model.PendudukModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PendudukServiceDatabase implements PendudukService {
	@Autowired
    private PendudukMapper pendudukMapper;

	@Override
	public PendudukModel selectPenduduk(String nik) {
		log.info("cari penduduk dengan" + nik);
		return pendudukMapper.selectPenduduk(nik);	
	}
	
	 @Override
	 public void tambahPenduduk (PendudukModel penduduk)
	 {
		 log.info("menambahkan penduduk");
		 pendudukMapper.tambahPenduduk(penduduk);
	 }
	 
	 public String kodeKecamatan(int id_keluarga) {
		 log.info("menambahkan nik dengan kode kecamatan" + id_keluarga);
		 return pendudukMapper.kodeKecamatan(id_keluarga);
	 }
	 
	 public int hitungNIK(String nik) {
		 log.info("menghitung nik penduduk" + nik);
		 return pendudukMapper.hitungNIK(nik);
	 }
	 
	 @Override
	 public void updatePenduduk (PendudukModel penduduk) {
	   	log.info("update penduduk dengan nik " + penduduk.getNik());
	   	pendudukMapper.updatePenduduk(penduduk);
	 }
	 
	 @Override
	 public int statusKematianPenduduk(String nik) {
	   	log.info("update status kematian penduduk dengan nik " + nik);
	   	return pendudukMapper.statusKematianPenduduk(nik);
	 }
	 
	 @Override
	 public int statusKeluarga(String id_keluarga) {
	   	log.info("cek status kk dengan id " + id_keluarga);
	   	return pendudukMapper.statusKeluarga(id_keluarga);
	 }
	 
	 @Override
	public List<PendudukModel> daftarPendudukSatuKelurahan(int id) {
		 log.info("pilih penduduk dengan id kelurahan " + id);
		 return pendudukMapper.daftarPendudukSatuKelurahan(id);
	 }
}
