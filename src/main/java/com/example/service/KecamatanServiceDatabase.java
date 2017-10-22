package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KecamatanMapper;
import com.example.model.KecamatanModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KecamatanServiceDatabase implements KecamatanService {
	@Autowired
    private KecamatanMapper kecamatanMapper;

	@Override
	public KecamatanModel selectKecamatan(String nik) {
		log.info("cari kecamatan penduduk dengan nik " + nik);
		return kecamatanMapper.selectKecamatan(nik);
	}
	
	@Override
	public KecamatanModel selectKecamatanByNKK(String nkk) {
		log.info("cari kecamatan penduduk dengan nkk " + nkk);
		return kecamatanMapper.selectKecamatanByNKK(nkk);
	}
	
	@Override
	public String selectNamaKecamatanByIdKelurahan(String id) {
		log.info("cari kecamatan penduduk dengan id kelurahan " + id);
		return kecamatanMapper.selectNamaKecamatanByIdKelurahan(id);
	}
	
	@Override
	public String namaKecamatanById (int id) {
		log.info("nama kecamatan dari id", id);
		return kecamatanMapper.namaKecamatanById(id);
	}
}
