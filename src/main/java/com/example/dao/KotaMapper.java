package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.KotaModel;

import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Many;

@Mapper
public interface KotaMapper {
	@Select("SELECT kota.id, kode_kota, nama_kota FROM penduduk, keluarga, kelurahan, kecamatan, kota WHERE penduduk.nik = #{nik} AND keluarga.id = penduduk.id_keluarga AND keluarga.id_kelurahan = kelurahan.id AND kelurahan.id_kecamatan = kecamatan.id AND kota.id = kecamatan.id_kota")
    @Results (value = {
    	@Result(property="id", column="id"),
    	@Result(property="kode_kota", column="kode_kota"),
    	@Result(property="nama_kota", column="nama_kota")
    })
	KotaModel selectKota(@Param("nik") String nik);
	
	@Select("SELECT kota.id, kode_kota, nama_kota FROM keluarga, kelurahan, kecamatan, kota WHERE keluarga.nomor_kk = #{nkk} AND keluarga.id_kelurahan = kelurahan.id AND kelurahan.id_kecamatan = kecamatan.id AND kota.id = kecamatan.id_kota")
    @Results (value = {
    	@Result(property="id", column="id"),
    	@Result(property="kode_kota", column="kode_kota"),
    	@Result(property="nama_kota", column="nama_kota")
    })
	KotaModel selectKotaByNKK(@Param("nkk") String nkk);
	
	@Select("SELECT kota.nama_kota FROM kota, kelurahan kel, kecamatan kec WHERE kel.id = #{id} AND kel.id_kecamatan = kec.id AND "
			+ "kec.id_kota = kota.id")
	String selectNamaKotaByIdKelurahan(@Param("id") String id);

	@Select("SELECT nama_kota FROM kota WHERE id=#{id}")
	String namaKotaById(@Param("id") int id);
}
