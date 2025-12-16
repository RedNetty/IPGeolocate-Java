package org.codered.frames;

import io.ipgeolocation.api.Geolocation;
import io.ipgeolocation.api.GeolocationParams;
import io.ipgeolocation.api.IPGeolocationAPI;
import org.codered.IPGeolocator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame {

    private final IPGeolocationAPI api;
    private final JTextField ipField;
    private final JTextArea displayArea;
    private final JProgressBar progressBar;

    public MainFrame() {
        setTitle("Geolocation App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        Font font = loadFont();
        BufferedImage img = loadImage();

        if (img != null) {
            setIconImage(img.getScaledInstance(64, 64, 32));
        }

        JPanel mainPanel = initializeMainPanel();
        add(mainPanel);

        ipField = initializeIPField(font);
        JButton fetchSelfButton = initializeButton("Fetch My Location", font);
        JButton fetchIpButton = initializeButton("Fetch IP Location", font);

        JPanel topPanel = initializeTopPanel(ipField, fetchSelfButton, fetchIpButton);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        displayArea = initializeTextArea(font);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        progressBar = initializeProgressBar();
        mainPanel.add(progressBar, BorderLayout.SOUTH);

        api = new IPGeolocationAPI(IPGeolocator.getGeoAPIKey());

        addMouseListenerToButtons(fetchSelfButton, fetchIpButton);
        addActionListenerToButtons(fetchSelfButton, fetchIpButton);

        pack();
        setLocationRelativeTo(null); // Center the frame
    }

    private Font loadFont() {
        Font font = new Font("Times New Roman", Font.PLAIN, 16);
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/null.otf"));
            font = font.deriveFont(Font.PLAIN, 10);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        return font;
    }

    private BufferedImage loadImage() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src/main/resources/icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    private JPanel initializeMainPanel() {
        JPanel mainPanel = new JPanel(new FlowLayout());
        mainPanel.setBackground(new Color(44, 47, 51));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        return mainPanel;
    }

    private JTextField initializeIPField(Font font) {
        JTextField ipField = new JTextField("Enter IPv4 Address");
        ipField.setPreferredSize(new Dimension(200, 30));
        ipField.setFont(font);
        ipField.setToolTipText("Enter IPv4 Address.");
        return ipField;
    }

    private JButton initializeButton(String text, Font font) {
        JButton button = new JButton(text);
        button.setBackground(new Color(64, 68, 75));
        button.setForeground(Color.WHITE);
        button.setFont(font);
        return button;
    }

    private JPanel initializeTopPanel(JTextField ipField, JButton fetchSelfButton, JButton fetchIpButton) {
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(44, 47, 51));
        topPanel.add(ipField);
        topPanel.add(fetchSelfButton);
        topPanel.add(fetchIpButton);
        return topPanel;
    }

    private JTextArea initializeTextArea(Font font) {
        JTextArea displayArea = new JTextArea();
        displayArea.setFont(font);
        displayArea.setPreferredSize(new Dimension(200, 200));
        displayArea.setBackground(new Color(44, 47, 51));
        displayArea.setForeground(Color.WHITE);
        displayArea.setEditable(false);
        return displayArea;
    }

    private JProgressBar initializeProgressBar() {
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(false);
        progressBar.setVisible(false);
        return progressBar;
    }


    private void addMouseListenerToButtons(JButton fetchSelfButton, JButton fetchIpButton) {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JButton source = (JButton) e.getSource();
                source.setBackground(Color.GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JButton source = (JButton) e.getSource();
                source.setBackground(new Color(64, 68, 75));
            }
        };

        fetchSelfButton.addMouseListener(mouseAdapter);
        fetchIpButton.addMouseListener(mouseAdapter);
    }

    private void addActionListenerToButtons(JButton fetchSelfButton, JButton fetchIpButton) {
        ActionListener fetchAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleFetchAction(e.getSource());
            }
        };

        fetchSelfButton.addActionListener(fetchAction);
        fetchIpButton.addActionListener(fetchAction);
    }

    private void handleFetchAction(Object source) {
        progressBar.setIndeterminate(true);
        progressBar.setVisible(true);
        displayArea.setText("Fetching data...");
        SwingUtilities.invokeLater(() -> {
            Geolocation geolocation = fetchData(source);
            updateDisplay(geolocation);
        });
    }

    private Geolocation fetchData(Object source) {
        String ipAddress = ipField.getText().equalsIgnoreCase("Enter IPv4 Address") ? "" : ipField.getText().trim();
        GeolocationParams geoParams = new GeolocationParams();
        JFrame.setDefaultLookAndFeelDecorated(true);
        if (!ipAddress.isEmpty()) {
            geoParams.setIPAddress(ipAddress);
        }
        geoParams.setFields("geo,time_zone,currency");
        return api.getGeolocation(geoParams);
    }

    private void updateDisplay(Geolocation geolocation) {
        displayArea.setText(""); // Clear text area
        displayArea.append("City: " + geolocation.getCity() + "\n\n");
        displayArea.append("State: " + geolocation.getStateProvince() + "\n\n");
        displayArea.append("Country: " + geolocation.getCountryName() + "\n\n");
        displayArea.append("Zip-Code: " + geolocation.getZipCode() + "\n\n\n");

        displayArea.append("Longitude: " + geolocation.getLongitude() + "\n");
        displayArea.append("Latitude: " + geolocation.getLatitude() + "\n");
        progressBar.setIndeterminate(false);
        progressBar.setVisible(false);
    }
}
