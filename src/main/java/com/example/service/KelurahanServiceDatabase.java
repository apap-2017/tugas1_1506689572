package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KelurahanMapper;
import com.example.model.KelurahanModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KelurahanServiceDatabase implements KelurahanService {
	@Autowired
    private KelurahanMapper kelurahanMapper;

	@Override
	public KelurahanModel selectKelurahan(String nik) {
		log.info("cari kelurahan penduduk dengan nik " + nik);
		return kelurahanMapper.selectKelurahan(nik);
	}
	
	@Override
	public KelurahanModel selectKelurahanByNKK(String nkk) {
		log.info("cari kelurahan penduduk dengan nkk " + nkk);
		return kelurahanMapper.selectKelurahanByNKK(nkk);
	}
	
	@Override
	public String selectNamaKelurahanById(String id) {
		log.info("cari kelurahan penduduk dengan id " + id);
		return kelurahanMapper.selectNamaKelurahanById(id);
	}
}
