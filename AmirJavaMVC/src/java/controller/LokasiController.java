package controller;

import com.google.gson.Gson;
import dao.LokasiDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.LokasiModel;

@WebServlet(name = "LokasiController", urlPatterns = {"/LokasiController"})
public class LokasiController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        String page = request.getParameter("page");

        PrintWriter out = response.getWriter();

        LokasiDao lokasiDao = new LokasiDao();
        Gson gson = new Gson();

        if (page == null) {
            List<LokasiModel> listLokasi = lokasiDao.getAllSiswa();
            String jsonLokasi = gson.toJson(listLokasi);
            out.println(jsonLokasi);
        } else if (page.equals("tambah")) {
            LokasiModel lokasi = new LokasiModel();
            lokasi.setId(Integer.parseInt(request.getParameter("id"))); // Assuming id is an integer
            lokasi.setNamaLokasi(request.getParameter("namaLokasi"));
            lokasi.setKodeLokasi(request.getParameter("kodeLokasi"));
            lokasiDao.simpanData(lokasi, page);
            response.setContentType("text/html;charset=UTF-8");
            out.println("Data berhasil disimpan, OK");
        } else if (page.equals("tampil")) {
            String jsonLokasi = gson.toJson(lokasiDao.getRecordById(request.getParameter("id")));
            response.setContentType("application/json");
            out.println(jsonLokasi);
        } else if (page.equals("edit")) {
            LokasiModel lokasi = new LokasiModel();
            lokasi.setId(Integer.parseInt(request.getParameter("id"))); // Assuming id is an integer
            lokasi.setNamaLokasi(request.getParameter("namaLokasi"));
            lokasi.setKodeLokasi(request.getParameter("kodeLokasi"));
            lokasiDao.updateData(lokasi, page);
            response.setContentType("text/html;charset=UTF-8");
            out.println("Data berhasil diedit, OK");
        } else if (page.equals("hapus")) {
            lokasiDao.hapusData(request.getParameter("id"));
            response.setContentType("text/html;charset=UTF-8");
            out.println("Data berhasil dihapus, OK");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
