package org.codered;


import org.codered.frames.MainFrame;

import javax.swing.*;
import java.util.logging.Logger;

public class IPGeolocator {
    private static final Logger LOGGER = Logger.getLogger(IPGeolocator.class.getName());

    // Set the IPGEO_API_KEY environment variable, or replace this with your key for local development.
    // Get a free key at: https://ipgeolocation.io/
    private static final String IPGEO_API_KEY = System.getenv("IPGEO_API_KEY") != null
            ? System.getenv("IPGEO_API_KEY")
            : "";

    public static void main(String[] args) {
        if (IPGEO_API_KEY.isEmpty()) {
            LOGGER.warning("IPGEO_API_KEY environment variable is not set. Geolocation requests will fail. " +
                    "Get a free key at: https://ipgeolocation.io/");
        }
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }

    public static String getGeoAPIKey() {
        return IPGEO_API_KEY;
    }
}
