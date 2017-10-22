package com.example.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KeluargaMapper;
import com.example.model.KeluargaModel;
import com.example.model.PendudukModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KeluargaServiceDatabase implements KeluargaService {
	@Autowired
    private KeluargaMapper keluargaMapper;

	@Override
	public KeluargaModel selectKeluarga(String nik) {
		log.info("cari keluarga penduduk dengan nik " + nik);
		return keluargaMapper.selectKeluarga(nik);
	}
	
	@Override
	public KeluargaModel selectKeluargaByNKK(String nkk) {
		log.info("cari keluarga penduduk dengan nkk " + nkk);
		return keluargaMapper.selectKeluargaByNKK(nkk);
	}
	
	@Override
	public void tambahKeluarga(KeluargaModel keluarga) {
		log.info("menambahkan keluarga");
		keluargaMapper.tambahKeluarga(keluarga);
	}
	
	@Override
	public String kodeKecamatan(String nama_kecamatan) {
		 log.info("menambahkan nkk dengan kode kecamatan");
		 return keluargaMapper.kodeKecamatan(nama_kecamatan);
	}
	
	@Override
	public int hitungNKK(String nkk) {
		 log.info("menghitung nkk keluarga" + nkk);
		 return keluargaMapper.hitungNKK(nkk);
	}
	 
	@Override
	public String kodeKelurahan(String nama_kelurahan) {
		log.info("mendapatkan id kelurahan");
		return keluargaMapper.kodeKelurahan(nama_kelurahan);
	}
	
	@Override
	public String kodeKota(String nama_kota) {
		log.info("mendapatkan id kota");
		return keluargaMapper.kodeKota(nama_kota);
	}
	
	@Override
	public List<PendudukModel> selectAnggotaKeluargaByIdKeluarga(int id_keluarga) {
		log.info("cari anggota keluarga berdasarkan id " + id_keluarga);
		return keluargaMapper.selectAnggotaKeluargaByIdKeluarga(id_keluarga);
	}
	
	@Override
	 public void updateKeluarga (KeluargaModel keluarga) {
	   	log.info("update keluarga dengan nkk " + keluarga.getNomor_kk());
	   	keluargaMapper.updateKeluarga(keluarga);
	 }
	 
	@Override	 
	public int statusKK(String id) {
		log.info("update status kk dengan id" + id);
		return keluargaMapper.statusKK(id);
	}
}
