/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package responsi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author Orenji
 */
public class MovieController {
    MovieModel movieModel;
    MovieView movieView;

    public MovieController(MovieModel movieModel, MovieView movieView) {
        this.movieModel = movieModel;
        this.movieView = movieView;
        
        if (movieModel.getBanyakData()!=0) {
            String dataMovie[][] = movieModel.readMovie();
            movieView.tabel.setModel((new JTable(dataMovie, movieView.namaKolom)).getModel());
        }else {
            JOptionPane.showMessageDialog(null, "Data Tidak Ada");
        }
        
        movieView.btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String judul = movieView.getJudul();
                String alur = movieView.getAlur();
                String penokohan = movieView.getPenokohan();
                String akting = movieView.getAkting();
                double a = Double.parseDouble(alur);
                double p = Double.parseDouble(penokohan);
                double ak = Double.parseDouble(akting);
                double n = (a+p+ak)/3;
                String nilai = Double.toString(n);
                movieModel.insertMovie(judul, alur, penokohan, akting, nilai);
         
                String dataMovie[][] = movieModel.readMovie();
                movieView.tabel.setModel((new JTable(dataMovie, movieView.namaKolom)).getModel());
            }
        });
        
        movieView.btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String judul = movieView.getJudul();
                String alur = movieView.getAlur();
                String penokohan = movieView.getPenokohan();
                String akting = movieView.getAkting();
                double a = Double.parseDouble(alur);
                double p = Double.parseDouble(penokohan);
                double ak = Double.parseDouble(akting);
                double n = (a+p+ak)/3;
                String nilai = Double.toString(n);
                movieModel.updateMovie(judul, alur, penokohan, akting, nilai);
                
                String dataMovie[][] = movieModel.readMovie();
                movieView.tabel.setModel((new JTable(dataMovie, movieView.namaKolom)).getModel());
            }
        });
        /*
        movieView.btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String dataMovie[][] = movieModel.readMovie();
                movieView.tabel.setModel((new JTable(dataMovie, movieView.namaKolom)).getModel());
            }
        });
        */
        movieView.tabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mousePressed(e);
                int baris = movieView.tabel.getSelectedRow();
                int kolom = movieView.tabel.getSelectedColumn(); // ga kepake sekarang

                String dataterpilih = movieView.tabel.getValueAt(baris, 1).toString();

                System.out.println(dataterpilih);

                int input = JOptionPane.showConfirmDialog(null,"Apa anda ingin menghapus judul " + dataterpilih + "?", "Pilih Opsi...", JOptionPane.YES_NO_OPTION);

                if (input == 0) {
                    movieModel.deleteMovie(dataterpilih);
                    String dataMovie[][] = movieModel.readMovie();
                    movieView.tabel.setModel(new JTable(dataMovie, movieView.namaKolom).getModel());
                } else {
                    JOptionPane.showMessageDialog(null, "Tidak Jadi Dihapus");
                }
            }
        });
    }
}
