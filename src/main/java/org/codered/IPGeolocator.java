package org.codered;


import org.codered.frames.MainFrame;

import javax.swing.*;

public class IPGeolocator {
    private static final String IPGEO_API_KEY = "";
    public static void main(String[] args)  {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));


    }

    public static String getGeoAPIKey() {
        return IPGEO_API_KEY;
    }
}