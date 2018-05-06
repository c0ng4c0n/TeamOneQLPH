package sample;

import connectmySQL.DataAccess;

import java.sql.Connection;

public class testjdbc {

    public static void main(String[] args) {
        Connection conn = DataAccess.ketNoi();
        try {
            if (!conn.isClosed()) {
                System.out.println("Ket noi thanh cong");
            }
        } catch (Exception ex) {
            System.err.println("Co lo xay ra. Chi tiet: " + ex.getStackTrace());
        }

    }
}