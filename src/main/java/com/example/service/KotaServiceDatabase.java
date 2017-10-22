package com.example.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KotaMapper;
import com.example.model.KotaModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KotaServiceDatabase implements KotaService {
	@Autowired
    private KotaMapper kotaMapper;

	@Override
	public KotaModel selectKota(String nik) {
		log.info("cari kota penduduk dengan nik " + nik);
		return kotaMapper.selectKota(nik);
	}
	
	@Override
	public KotaModel selectKotaByNKK(String nkk) {
		log.info("cari kota penduduk dengan nkk " + nkk);
		return kotaMapper.selectKotaByNKK(nkk);
	}
	
	@Override
	public String selectNamaKotaByIdKelurahan(String id) {
		log.info("cari kota penduduk dengan id " + id);
		return kotaMapper.selectNamaKotaByIdKelurahan(id);
	}
	
	@Override
	public String namaKotaById(int id) {
		log.info("nama kota dengan id ", id);
		return kotaMapper.namaKotaById(id);
	}

}
