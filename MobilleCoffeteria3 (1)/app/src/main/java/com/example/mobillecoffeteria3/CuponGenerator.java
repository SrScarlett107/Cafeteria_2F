package com.example.mobillecoffeteria3;
import java.util.Random;
public class CuponGenerator {
    public static String generateCoupon() {

        // Método para gerar um código de cupom aleatório
            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // caracteres permitidos no código do cupom
            StringBuilder coupon = new StringBuilder();
            Random rnd = new Random();

            // Gera um código de cupom com 8 caracteres
            for (int i = 0; i < 8; i++) {
                coupon.append(characters.charAt(rnd.nextInt(characters.length())));
            }

            return coupon.toString();
        }
    }
