package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.KelurahanModel;

import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Many;

@Mapper
public interface KelurahanMapper {
	@Select("SELECT kelurahan.id, kode_kelurahan, id_kecamatan, nama_kelurahan, kode_pos FROM penduduk, keluarga, kelurahan WHERE penduduk.nik = #{nik} AND keluarga.id = penduduk.id_keluarga AND keluarga.id_kelurahan = kelurahan.id")
    @Results (value = {
    	@Result(property="id", column="id"),
    	@Result(property="kode_kelurahan", column="kode_kelurahan"),
    	@Result(property="id_kecamatan", column="id_kecamatan"),
    	@Result(property="nama_kelurahan", column="nama_kelurahan"),
    	@Result(property="kode_pos", column="kode_pos")
    })
	KelurahanModel selectKelurahan(@Param("nik") String nik);
	
	@Select("SELECT kl.id, kl.kode_kelurahan, kl.id_kecamatan, kl.nama_kelurahan, kl.kode_pos FROM keluarga kg, kelurahan kl WHERE kg.nomor_kk = #{nkk} AND kg.id_kelurahan = kl.id")
    @Results (value = {
    	@Result(property="id", column="id"),
    	@Result(property="kode_kelurahan", column="kode_kelurahan"),
    	@Result(property="id_kecamatan", column="id_kecamatan"),
    	@Result(property="nama_kelurahan", column="nama_kelurahan"),
    	@Result(property="kode_pos", column="kode_pos")
    })
	KelurahanModel selectKelurahanByNKK(@Param("nkk") String nkk);
	
	@Select("SELECT kl.nama_kelurahan FROM kelurahan kl WHERE kl.id = #{id}")
	String selectNamaKelurahanById(@Param("id") String id);	
}
