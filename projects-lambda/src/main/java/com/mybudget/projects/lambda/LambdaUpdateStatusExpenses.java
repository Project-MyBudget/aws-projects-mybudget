package com.mybudget.projects.lambda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Guilherme Monteiro
 */
public class LambdaUpdateStatusExpenses {

    public void executeUpdate() {
        try {
            
            String url = "jdbc:mysql://mybdb.cddkgvwhc4hh.us-east-1.rds.amazonaws.com:3306/mybdb";
            String username = "admin";
            String password = "F1Avin_duPn3u";

            Connection conn = DriverManager.getConnection(url, username, password);
            LocalDate hoje = LocalDate.now();
            LocalDate mesAnterior = hoje.minusMonths(1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dataMesAnterior = mesAnterior.format(formatter);

            String sqlUpdate = "UPDATE TB_EXPENSES SET STATUS = ? WHERE MONTH(DATE_REFERENCE) = ? AND YEAR(DATE_REFERENCE) = ?";

            PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);

            pstmt.setString(1, "I");
            pstmt.setInt(2, mesAnterior.getMonthValue());
            pstmt.setInt(3, mesAnterior.getYear());
            
            int rowsUpdated = pstmt.executeUpdate();
            System.out.println("Registros atualizados: " + rowsUpdated);
            System.out.println("Mês que foi atualizado: " + dataMesAnterior);

        } catch (SQLException e) {
            System.out.println("Erro ao executar o update: " + e.getMessage());
        }
    }
}
