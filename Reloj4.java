package com.mycompany.reloj4;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

/**
 * @author luise
 */
public class Reloj4 extends JPanel {

    JPanel paneldibujo;

    public Reloj4() {
      
        setLayout(new FlowLayout());
        paneldibujo = new JPanel();
        paneldibujo.setPreferredSize(new Dimension(370, 610));
        paneldibujo.setBackground(Color.white);
        add(paneldibujo);
    }

    private static void clic() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\luise\\Documents\\NetBeansProjects\\Reloj4\\src\\main\\java\\com\\mycompany\\reloj4\\Clic.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error al reproducir sonido");

        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Reloj4 ventana = new Reloj4();
        frame.add(ventana);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public static void reproducir() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\luise\\Documents\\NetBeansProjects\\Reloj4\\src\\main\\java\\com\\mycompany\\reloj4\\Burro.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error al reproducir sonido");
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D lapiz2d;
        Font f = new Font("ROBOTO", Font.BOLD, 19);
        Graphics lapiz = g;
        lapiz2d = (Graphics2D) lapiz;
        lapiz2d.setFont(f);
        lapiz2d.drawString("Arial", 128, 205);

        GradientPaint degradado1 = new GradientPaint(0, 0, Color.DARK_GRAY, 0, 170, Color.decode("#e30b0b"), true);
        GradientPaint degradado3 = new GradientPaint(0, 0, Color.BLUE, 0, 170, Color.MAGENTA, true);
        // GradientPaint degradado4 = new GradientPaint(0, 0, Color.BLUE, 0, 170, Color.MAGENTA, true);

        lapiz2d.setPaint(degradado1);
        lapiz2d.setStroke(new BasicStroke(6));
        lapiz2d.drawOval(15, 60, 340, 340);
        lapiz2d.drawOval(16, 61, 340, 340);
        lapiz2d.drawOval(17, 62, 340, 340);

        //pendulo
        lapiz2d.fillRect(178, 360, 10, 60);
        lapiz2d.fillRect(178, 400, 10, 20);

        //Segundo circulo
        lapiz2d.fillOval(45, 88, 280, 280);
        //tercer circulo
        lapiz2d.setPaint(degradado3);
        lapiz2d.fillOval(54, 96, 263, 263);
        //cuarto circulo
        lapiz2d.setPaint(degradado1);
        lapiz2d.fillOval(58, 100, 255, 255);

        //Circulo1 pendulo
        lapiz2d.fillOval(58, 100, 255, 255);
        lapiz2d.setColor(Color.blue);
        lapiz2d.fillOval(150, 415, 65, 65);
        //circulo2pendulo
        lapiz2d.setPaint(degradado1);
        lapiz2d.fillOval(152, 410, 60, 60);
        lapiz2d.setColor(Color.red);
        lapiz2d.fillOval(157, 415, 50, 50);
        //circulo3pendulo
        lapiz2d.setPaint(degradado1);
        lapiz2d.fillOval(157, 410, 50, 50);

        //Con un ciclo for hacemos que se importen las librerias en calendario y get instance adquiere la hora exacta
        double grados, radianes, a = 0, o = 0, b = 0;
        Calendar calendario = Calendar.getInstance();
        int hora = calendario.get(Calendar.HOUR_OF_DAY);
        int minutos = calendario.get(Calendar.MINUTE);
        int segundos = calendario.get(Calendar.SECOND);

        lapiz2d.setColor(Color.cyan);
        Font f1 = new Font("ROBOTO", Font.BOLD, 30);
        lapiz2d.setFont(f1);

        //PenultimoCirculo
        lapiz2d.setPaint(degradado3);
        lapiz2d.fillOval(70, 111, 230, 230);
        //circulo en medio
        lapiz2d.setPaint(degradado1);
        lapiz2d.fillOval(125, 168, 120, 120);

        //Numeros reloj
        lapiz2d.setColor(Color.BLACK);
        lapiz2d.drawString("XII", 165, 150);
        lapiz2d.drawString("III", 259, 238);
        lapiz2d.drawString("VI", 168, 325);
        lapiz2d.drawString("IX", 85, 240);

        //puntos segundero
        for (int j = 1; j <= 360; j++) {
            grados = o;
            radianes = Math.toRadians(grados);
            a = Math.sin(radianes) * 108;
            b = Math.cos(radianes) * 108;
            lapiz2d.setColor(Color.BLACK);
            lapiz2d.drawString(".", 182 + (int) a, 228 - (int) b);
            Font f2 = new Font("ROBOTO", Font.BOLD, 15);
            lapiz2d.setFont(f2);

            //convierte los grados aradianes
            if (grados % 10 == 0) {
                double a1 = Math.sin(radianes) * 65;
                double b1 = Math.cos(radianes) * 65;
                lapiz2d.drawString(".", 182 + (int) a1, 228 - (int) b1);
            }
            o = o + 6;
        }

        //Manecillas Hora
        lapiz2d.setStroke(new BasicStroke(2));
        grados = hora * 30;
        radianes = Math.toRadians(grados);
        a = Math.sin(radianes) * 60;
        b = Math.cos(radianes) * 60;
        lapiz2d.setColor(Color.YELLOW);
        lapiz2d.drawLine(182, 228, 182 + (int) a, 228 - (int) b);

        //Manecillas minutos
        grados = minutos * 6;
        radianes = Math.toRadians(grados);
        a = Math.sin(radianes) * 85;
        b = Math.cos(radianes) * 85;
        lapiz2d.setColor(Color.BLACK);
        lapiz2d.drawLine(182, 228, 182 + (int) a, 228 - (int) b);

        //Manecillas segundos
        grados = segundos * 6;
        radianes = Math.toRadians(grados);
        a = Math.sin(radianes) * 100;
        b = Math.cos(radianes) * 100;
        lapiz2d.setColor(Color.BLACK);
        lapiz2d.drawLine(182, 228, 182 + (int) a, 228 - (int) b);
        clic();

        try {
            Thread.sleep(950);
        } catch (InterruptedException ignored) {
            /**/
        }

        //ALARMA
        if (hora == 22 && minutos == 20 && segundos == 0) {
            reproducir();
        } else if (hora == 6 && minutos == 0 && segundos == 0) {
            reproducir();
        }

        this.repaint();
    }
}
