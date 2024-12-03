
package dao;

import connection.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.LokasiModel;


public class LokasiDao {
    private final Connection conDB;
    private PreparedStatement ps;
    private ResultSet rs;
    
  
    
    public LokasiDao() {
       conDB = Koneksi.getKoneksi();
    }
   public void simpanData(LokasiModel sis, String page) {
        String sqlSimpan = null;
        if (page.equals("tambah")) {
            sqlSimpan = "INSERT INTO lokasi (namaLokasi, kodeLokasi) VALUES (?, ?)";        
            try { 
                ps = conDB.prepareStatement(sqlSimpan);
                ps.setString(1, sis.getNamaLokasi());
                ps.setString(2, sis.getKodeLokasi());
    //            ps.setString(7, sis.getId());
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Simpan data error: " + e.getMessage());
            }
        }
    }
   
   public void updateData(LokasiModel sis, String page) {
        String sqlSimpan = null;
        if (page.equals("edit")) {
            sqlSimpan = "UPDATE lokasi SET namaLokasi=?, kodeLokasi=? WHERE id=?";      
            try { 
                ps = conDB.prepareStatement(sqlSimpan);
                ps.setString(1, sis.getNamaLokasi());
                ps.setString(2, sis.getKodeLokasi());
                ps.setInt(3, sis.getId());
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Simpan data error: " + e.getMessage());
            }
        
        }
    }


    public void hapusData(String id) {
        String sqlHapus = "DELETE FROM lokasi WHERE id=?";
        try {
            ps = conDB.prepareStatement(sqlHapus);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("method hapus data error: " + e.getMessage());
        }
    }

    public LokasiModel getRecordById(String id) {
        LokasiModel sis = new LokasiModel();
        String sqlSeacrh = "SELECT * FROM lokasi WHERE id=?";
        try {
            ps = conDB.prepareStatement(sqlSeacrh);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                sis.setId(rs.getInt("id"));
                sis.setNamaLokasi(rs.getString("namaLokasi"));
                sis.setKodeLokasi(rs.getString("kodeLokasi"));
            }
        } catch (SQLException e) {
            System.out.println("getRecord by id ada kesalahan: " + e.getMessage());
        }
        return sis;
    }

    public ArrayList<LokasiModel> getAllSiswa() {
        ArrayList<LokasiModel> listSiswa = new ArrayList<>();
        try {
            String sqlAllSiswa = "SELECT * FROM lokasi ORDER BY kodeLokasi";
            ps = conDB.prepareStatement(sqlAllSiswa);
            rs = ps.executeQuery();
            while (rs.next()) {
                LokasiModel sis = new LokasiModel();
                sis.setId(rs.getInt("id"));
                sis.setNamaLokasi(rs.getString("namaLokasi"));
                sis.setKodeLokasi(rs.getString("kodeLokasi"));
                listSiswa.add(sis);
            }
        } catch (SQLException e) {
            System.out.println("method arrayList error: " + e.getMessage());
        }
        return listSiswa;
    }
    
     public static void main(String[] args) {
        // TODO code application logic here
        LokasiDao sisdao = new LokasiDao();
        System.out.println(sisdao.getAllSiswa());
        //sisdao.hapusData("S121");
    
    }
}
