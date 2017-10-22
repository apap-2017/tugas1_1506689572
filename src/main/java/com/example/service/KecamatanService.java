package com.example.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.model.KecamatanModel;


public interface KecamatanService {
	KecamatanModel selectKecamatan (String nik);
	
	KecamatanModel selectKecamatanByNKK (String nkk);
	
	String selectNamaKecamatanByIdKelurahan(String id);
	
	String namaKecamatanById (int id);
}
