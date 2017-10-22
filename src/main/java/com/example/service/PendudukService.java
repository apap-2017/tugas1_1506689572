package com.example.service;

import java.util.List;

import com.example.model.PendudukModel;

public interface PendudukService {
	PendudukModel selectPenduduk(String nik);
	
	void tambahPenduduk(PendudukModel penduduk);
	
	String kodeKecamatan(int id_keluarga);
	
	int hitungNIK(String nik);
	
	void updatePenduduk (PendudukModel penduduk);
	
	int statusKematianPenduduk(String nik);
	
	int statusKeluarga(String id_keluarga);
	
	List<PendudukModel> daftarPendudukSatuKelurahan(int id);
}
