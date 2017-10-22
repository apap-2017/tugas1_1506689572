package com.example.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.model.KeluargaModel;
import com.example.model.PendudukModel;

public interface KeluargaService {
	KeluargaModel selectKeluarga (String nik);
	
	KeluargaModel selectKeluargaByNKK (String nkk);
	
	void tambahKeluarga(KeluargaModel keluarga);
	
	int hitungNKK(String NKK);
	
	String kodeKecamatan(String nama_kecamatan);
	
	String kodeKelurahan(String nama_kelurahan);
	
	String kodeKota(String nama_kota);
	
	List<PendudukModel> selectAnggotaKeluargaByIdKeluarga(int id_keluarga);
	
	void updateKeluarga (KeluargaModel keluarga);
	
	int statusKK(String id);
}
