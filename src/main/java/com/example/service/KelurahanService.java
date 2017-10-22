package com.example.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.model.KelurahanModel;

public interface KelurahanService {
	KelurahanModel selectKelurahan (String nik);
	
	KelurahanModel selectKelurahanByNKK (String nkk);
	
	String selectNamaKelurahanById(String id);
}
