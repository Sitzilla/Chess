package com.evansitzes.chessgame;

import javax.swing.*;
import java.awt.*;

public class StartClass {

public static void main(final String[] args) {

    // Necessary so UI works cross-platform
    try {
        UIManager.setLookAndFeel(
                UIManager.getCrossPlatformLookAndFeelClassName());
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (InstantiationException e) {
        e.printStackTrace();
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    } catch (UnsupportedLookAndFeelException e) {
        e.printStackTrace();
    }

    final JFrame window = new JFrame("Chess");
        final ChessGame game = new ChessGame();
        window.setContentPane(game);
        final Dimension d = new Dimension(600,600);
        window.setPreferredSize(d);
        window.setResizable(false);
        window.pack();
        window.setLocation(100,100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

}
