package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.PendudukModel;

import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Many;

@Mapper
public interface PendudukMapper {
	@Select("SELECT id, nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, is_wni, id_keluarga, agama, pekerjaan, "
			+ "status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat FROM penduduk WHERE nik = #{nik}")
    @Results (value = {
    	@Result(property="id", column="id"),
    	@Result(property="nik", column="nik"),
    	@Result(property="nama", column="nama"),
    	@Result(property="tempat_lahir", column="tempat_lahir"),
    	@Result(property="tanggal_lahir", column="tanggal_lahir"),
    	@Result(property="jenis_kelamin", column="jenis_kelamin"),
    	@Result(property="is_wni", column="is_wni"),
    	@Result(property="id_keluarga", column="id_keluarga"),
    	@Result(property="agama", column="agama"),
    	@Result(property="pekerjaan", column="pekerjaan"),
    	@Result(property="status_perkawinan", column="status_perkawinan"),
    	@Result(property="status_dalam_keluarga", column="status_dalam_keluarga"),
    	@Result(property="golongan_darah", column="golongan_darah"),
    	@Result(property="is_wafat", column="is_wafat")
    })
	PendudukModel selectPenduduk(@Param("nik") String nik);
	
	 @Insert("INSERT INTO penduduk (nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, "
	 		+ "is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, "
	 		+ "is_wafat) VALUES (#{nik}, #{nama}, #{tempat_lahir}, #{tanggal_lahir}, #{jenis_kelamin},"
	 		+ " #{is_wni}, #{id_keluarga}, #{agama}, #{pekerjaan}, #{status_perkawinan}, #{status_dalam_keluarga}, #{golongan_darah}, #{is_wafat})")
	    void tambahPenduduk (PendudukModel penduduk);
	 
	 @Select("SELECT count(*) FROM penduduk WHERE penduduk.nik = #{nik}")
	 	int hitungNIK (String nik);
	 
	 @Select("SELECT kec.kode_kecamatan FROM keluarga kg, kelurahan kel, kecamatan kec "
				+ " WHERE #{id_keluarga} = kg.id AND kg.id_kelurahan = kel.id AND kel.id_kecamatan = kec.id")
		 String kodeKecamatan(@Param("id_keluarga") int id_keluarga);
	 
	 @Update("UPDATE penduduk SET nik = #{nik}, nama = #{nama}, tempat_lahir = #{tempat_lahir}, tanggal_lahir = #{tanggal_lahir}, "
	 		+ "jenis_kelamin = #{jenis_kelamin}, is_wni = #{is_wni}, id_keluarga = #{id_keluarga}, agama = #{agama},"
	 		+ "pekerjaan = #{pekerjaan}, status_perkawinan = #{status_perkawinan}, status_dalam_keluarga = #{status_dalam_keluarga},"
	 		+ "golongan_darah = #{golongan_darah}, is_wafat = #{is_wafat} WHERE id = #{id}")
	    void updatePenduduk(PendudukModel penduduk);
	 
	 @Update("UPDATE penduduk SET is_wafat = 1 WHERE nik = #{nik}")
	 	int statusKematianPenduduk(@Param("nik") String nik);
	 
	 @Update("SELECT count(*) FROM penduduk WHERE id_keluarga = #{id_keluarga} AND is_wafat = 0")
	 	int statusKeluarga(@Param("id_keluarga") String id_keluarga);
	 @Select("SELECT penduduk.nik, penduduk.nama, penduduk.jenis_kelamin FROM penduduk, keluarga "
	 		+ "WHERE #{id} = keluarga.id_kelurahan AND penduduk.id_keluarga = keluarga.id")
	 @Results(value = {
			 @Result(property = "nik", column = "nik"),
			 @Result(property = "nama", column = "nama"),
			 @Result(property = "jenis_kelamin", column = "jenis_kelamin")
	 })
	 List<PendudukModel> daftarPendudukSatuKelurahan(@Param("id") int id);
}
