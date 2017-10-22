package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.KecamatanModel;

import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Many;

@Mapper
public interface KecamatanMapper {
	@Select("SELECT kecamatan.id, kode_kecamatan, id_kota, nama_kecamatan FROM penduduk, keluarga, kelurahan, kecamatan"
			+ " WHERE penduduk.nik = #{nik} AND penduduk.id_keluarga = keluarga.id AND keluarga.id_kelurahan = kelurahan.id"
			+ " AND kelurahan.id_kecamatan = kecamatan.id")
    @Results (value = {
    	@Result(property="id", column="id"),
    	@Result(property="kode_kecamatan", column="kode_kecamatan"),
    	@Result(property="id_kota", column="id_kota"),
    	@Result(property="nama_kecamatan", column="nama_kecamatan")
    })
	KecamatanModel selectKecamatan(@Param("nik") String nik);
	
	@Select("SELECT kecamatan.id, kode_kecamatan, id_kota, nama_kecamatan FROM keluarga, kelurahan, kecamatan WHERE keluarga.nomor_kk = #{nkk} AND keluarga.id_kelurahan = kelurahan.id AND kelurahan.id_kecamatan = kecamatan.id")
    @Results (value = {
    	@Result(property="id", column="id"),
    	@Result(property="kode_kecamatan", column="kode_kecamatan"),
    	@Result(property="id_kota", column="id_kota"),
    	@Result(property="nama_kecamatan", column="nama_kecamatan")
    })
	KecamatanModel selectKecamatanByNKK(@Param("nkk") String nkk);
	
	@Select("SELECT kec.nama_kecamatan FROM kecamatan kec, kelurahan kl WHERE kl.id = #{id} and kl.id_kecamatan = kec.id")
	String selectNamaKecamatanByIdKelurahan(@Param("id") String id);
	
	@Select("SELECT nama_kecamatan FROM kecamatan WHERE id=#{id}")
	String namaKecamatanById(@Param("id") int id);
}