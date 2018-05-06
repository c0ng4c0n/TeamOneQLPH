package sample;

import connectmySQL.DataAccess;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main{

    public static String md5(String str){
        String result = "";
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(str.getBytes());
            BigInteger bigInteger = new BigInteger(1,digest.digest());
            result = bigInteger.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Object[]> laydsphong() {

        List<Object[]> istph = new ArrayList();

        try {
            Connection conn = DataAccess.ketNoi();

            Statement comm = conn.createStatement();

            String strsql = "SELECT * FROM phong_hoc";
            ResultSet rs = comm.executeQuery(strsql);

            Object[] phong = null;
            while (rs.next()) {
                phong = new Object[8];
                phong[0] = rs.getString("MaPhong");
                phong[1] = rs.getString("LoaiPhong");
                phong[2] = rs.getString("TinhTrang");
                phong[3] = rs.getString("SucChua");
                phong[4] = rs.getString("MayChieu");
                phong[5] = rs.getString("DieuHoa");
                phong[6] = rs.getString("Micro");
                phong[7] = rs.getString("Camera");
                istph.add(phong);
            }

            conn.close();

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        return istph;
    }

    public List<Object[]> laydsgv() {

        List<Object[]> istgv = new ArrayList();

        try {
            Connection conn = DataAccess.ketNoi();

            Statement comm = conn.createStatement();

            String strsql = "SELECT * FROM giao_vien";
            ResultSet rs = comm.executeQuery(strsql);

            Object[] gv = null;
            while (rs.next()) {
                gv = new Object[8];
                gv[0] = rs.getString("MaGV");
                gv[1] = rs.getString("HoTenGV");
                gv[2] = rs.getString("GioiTinh");
                gv[3] = rs.getString("NamSinh");
                String ns = gv[3].toString();
                ns = ns.substring(0,4);
                gv[3] = ns;
                gv[4] = rs.getString("Email");
                gv[5] = rs.getString("Khoa");
                istgv.add(gv);
            }

            conn.close();

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        return istgv;
    }

    public List<Object[]> laydslh() {

        List<Object[]> istlh = new ArrayList();

        try {
            Connection conn = DataAccess.ketNoi();

            Statement comm = conn.createStatement();

            String strsql = "SELECT * FROM lop_hoc";
            ResultSet rs = comm.executeQuery(strsql);

            Object[] lh = null;
            while (rs.next()) {
                lh = new Object[11];
                lh[0] = rs.getString("MaLop");
                lh[1] = rs.getString("TenLop");
                lh[2] = rs.getString("Email");
                lh[3] = rs.getString("SiSo");
                lh[4] = rs.getString("HoTenLT");
                lh[5] = rs.getString("GioiTinhLT");
                lh[6] = rs.getString("NamSinhLT");
                String s = lh[6].toString();
                lh[6] = s.substring(0,4);
                lh[7] = rs.getString("HoTenCN");
                lh[8] = rs.getString("GioiTinhCN");
                lh[9] = rs.getString("NamSinhCN");
                s = lh[9].toString();
                lh[9] = s.substring(0,4);
                lh[10] = rs.getString("ChucVuCN");
                istlh.add(lh);
            }

            conn.close();

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        return istlh;
    }

    public List<Object[]> laydstk() {

        List<Object[]> isttk = new ArrayList();

        try {
            Connection conn = DataAccess.ketNoi();

            Statement comm = conn.createStatement();

            String strsql = "SELECT * FROM account";
            ResultSet rs = comm.executeQuery(strsql);

            Object[] tk = null;
            while (rs.next()) {
                tk = new Object[3];
                tk[0] = rs.getString("username");
                tk[1] = rs.getString("password");
                tk[2] = rs.getString("type");
                isttk.add(tk);
            }

            conn.close();

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isttk;
    }

    public List<Object[]> laydsnv() {

        List<Object[]> istnv = new ArrayList();

        try {
            Connection conn = DataAccess.ketNoi();

            Statement comm = conn.createStatement();

            String strsql = "SELECT * FROM nhan_vien";
            ResultSet rs = comm.executeQuery(strsql);

            Object[] nv = null;
            while (rs.next()) {
                nv = new Object[6];
                nv[0] = rs.getString("MaNV");
                nv[1] = rs.getString("HoTenNV");
                nv[2] = rs.getString("GioiTinh");
                nv[3] = rs.getString("NamSinh");
                String ns = nv[3].toString();
                ns = ns.substring(0,4);
                nv[3] = ns;
                nv[4] = rs.getString("ChucVu");
                nv[5] = rs.getString("MaNVLanhDao");
                istnv.add(nv);
            }

            conn.close();

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        return istnv;
    }

    public List<Object[]> laydssdt() {

        List<Object[]> istsdt = new ArrayList();

        try {
            Connection conn = DataAccess.ketNoi();

            Statement comm = conn.createStatement();

            String strsql = "SELECT * FROM so_dien_thoai";
            ResultSet rs = comm.executeQuery(strsql);

            Object[] sdt = null;
            while (rs.next()) {
                sdt = new Object[3];
                sdt[0] = rs.getString("MaSo");
                sdt[1] = rs.getString("SoDienThoai");
                sdt[2] = rs.getString("PhanLoai");
                istsdt.add(sdt);
            }

            conn.close();

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        return istsdt;
    }

    public List<Object[]> laydsgd() {

        List<Object[]> istgd = new ArrayList();

        try {
            Connection conn = DataAccess.ketNoi();

            Statement comm = conn.createStatement();

            String strsql = "SELECT * FROM giang_day";
            ResultSet rs = comm.executeQuery(strsql);

            Object[] gd = null;
            while (rs.next()) {
                gd = new Object[3];
                gd[0] = rs.getString("MaGV");
                gd[1] = rs.getString("MaLop");
                gd[2] = rs.getString("MonHoc");
                istgd.add(gd);
            }

            conn.close();

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        return istgd;
    }

}
