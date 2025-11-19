package MotorEntix.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String adminPwd = "Admin123!";
        String trabajadorPwd = "Trabajador123!";
        String clientePwd = "Cliente123!";

        System.out.println("Admin123!:      " + encoder.encode(adminPwd));
        System.out.println("Trabajador123!: " + encoder.encode(trabajadorPwd));
        System.out.println("Cliente123!:    " + encoder.encode(clientePwd));
    }
}
