package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.KeluargaModel;
import com.example.model.PendudukModel;

import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Many;

@Mapper
public interface KeluargaMapper {
	@Select("SELECT keluarga.id, nomor_kk, alamat, rt, rw, id_kelurahan, is_tidak_berlaku FROM penduduk, keluarga, kelurahan, kecamatan, kota"
			+ " WHERE penduduk.nik = #{nik} AND keluarga.id = penduduk.id_keluarga AND keluarga.id_kelurahan = kelurahan.id"
			+ " AND kelurahan.id_kecamatan = kecamatan.id AND kecamatan.id_kota = kota.id")
    @Results (value = {
    	@Result(property="id", column="id"),
    	@Result(property="nomor_kk", column="nomor_kk"),
    	@Result(property="alamat", column="alamat"),
    	@Result(property="rt", column="rt"),
    	@Result(property="rw", column="rw"),
    	@Result(property="id_kelurahan", column="id_kelurahan"),
    	@Result(property="is_tidak_berlaku", column="is_tidak_berlaku")
    })
	KeluargaModel selectKeluarga(@Param("nik") String nik);
	
	@Select("SELECT keluarga.id, nomor_kk, alamat, rt, rw, id_kelurahan, is_tidak_berlaku FROM keluarga "
			+ "WHERE nomor_kk = #{nkk}")
    @Results (value = {
    	@Result(property="id", column="id"),
    	@Result(property="nomor_kk", column="nomor_kk"),
    	@Result(property="alamat", column="alamat"),
    	@Result(property="rt", column="rt"),
    	@Result(property="rw", column="rw"),
    	@Result(property="id_kelurahan", column="id_kelurahan"),
    	@Result(property="is_tidak_berlaku", column="is_tidak_berlaku"),
    	@Result(property="anggotaKeluarga", column="id",
		javaType = List.class,
		many=@Many(select="selectAnggotaKeluarga"))
    })
	KeluargaModel selectKeluargaByNKK(@Param("nkk") String nkk);
	
	 @Select("SELECT * " +
	    		"FROM penduduk " +
	    		"WHERE penduduk.id_keluarga = #{id}")
	    		List<PendudukModel> selectAnggotaKeluarga(@Param("id") int id);
	 
	 @Insert("INSERT INTO keluarga (nomor_kk, alamat, rt, rw, "
		 	+ "id_kelurahan, is_tidak_berlaku) VALUES (#{nomor_kk}, #{alamat}, #{rt}, #{rw},"
		 	+ " #{id_kelurahan}, #{is_tidak_berlaku})")
	 	void tambahKeluarga (KeluargaModel keluarga);
		 
	 @Select("SELECT count(*) FROM keluarga WHERE keluarga.nomor_kk = #{nomor_kk}")
	 	int hitungNKK (String nkk);
		 
	 @Select("SELECT kec.kode_kecamatan FROM kecamatan kec WHERE #{nama_kecamatan} = kec.nama_kecamatan")
	 	String kodeKecamatan(String nama_kecamatan);
	 
	 @Select("SELECT kel.id FROM kelurahan kel WHERE #{nama_kelurahan} = kel.nama_kelurahan")
	 	String kodeKelurahan(String nama_kelurahan);
	 
	 @Select("SELECT kota.id FROM kota WHERE #{nama_kota} = kota.nama_kota")
	 	String kodeKota(String nama_kota);
	 
	 @Select("SELECT * " +
	    		"FROM penduduk " +
	    		"WHERE penduduk.id_keluarga = #{id_keluarga}")
	    		List<PendudukModel> selectAnggotaKeluargaByIdKeluarga(@Param("id_keluarga") int id_keluarga);
	 
	 @Update("UPDATE keluarga SET nomor_kk = #{nomor_kk}, alamat = #{alamat}, rt = #{rt}, rw = #{rw}, id_kelurahan = #{id_kelurahan}, "
		 		+ "is_tidak_berlaku = #{is_tidak_berlaku} WHERE id = #{id}")
		  void updateKeluarga(KeluargaModel keluarga);
	 
	 @Update("UPDATE keluarga SET is_tidak_berlaku = 1 WHERE id = #{id}")
	 	int statusKK(@Param("id") String id);
}
