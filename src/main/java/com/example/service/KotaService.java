package com.example.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.model.KotaModel;

public interface KotaService {
	KotaModel selectKota (String nik);
	
	KotaModel selectKotaByNKK (String nkk);
	
	String selectNamaKotaByIdKelurahan(String id);
	
	String namaKotaById(int id);
}
