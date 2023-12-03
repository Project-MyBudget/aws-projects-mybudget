package com.mybudget.projects.lambda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Guilherme Monteiro
 */
public class LambdaUpdateStatusExpenses {

    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://mybdb.cddkgvwhc4hh.us-east-1.rds.amazonaws.com/mybdb";
    private final String username = "admin";
    private final String password = "F1Avin_duPn3u";
    
    public void executeUpdate() {
        Connection conn = null;
        
        try {
            Class.forName(driver);

            conn = DriverManager.getConnection(url, username, password);
            
            String sqlUpdate = "UPDATE TB_EXPENSES SET STATUS = ? WHERE DATE_REFERENCE >= DATE_SUB(NOW(), INTERVAL 1 MONTH AND STATUS <> 'I');";

            PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);

            pstmt.setString(1, "I");

            int rowsUpdated = pstmt.executeUpdate();
            System.out.println("Registros atualizados: " + rowsUpdated);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao executar o update: " + e.getMessage());
            
        } finally {
            try {
                if (conn != null) {
                    conn.endRequest();
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar a conexão: " + ex.getMessage());
            }
        }
    }
}
