package skeleton.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GUI extends JFrame {

    ClientMonitor c;
    JPanel buttonPanel, labelPanel, bottomPanel;
    JLabel sourceLabel, displayModeLabel;
    ImagePanel imagePanel1, imagePanel2;
    JButton syncB, connectB, movieB, idleB, autoB;

    public GUI(ClientMonitor c) {
        super();
        this.c = c;

        //set up layout
        this.getContentPane().setLayout(new BorderLayout());

        //imagePanels
        setUpImagePanels();

        //buttons and labels
        setUpBottomPanel();

        //button listeners
        linkButtonListeners();

        //last step
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    public ImagePanel getImagePanel(int panelNum) {
        switch (panelNum) {
            case 1: return imagePanel1;
            case 2: return imagePanel2;
            default: return null;
        }
    }

    public void setSourceLabel(int sourceNum) {
        sourceLabel.setText("Source: " + sourceNum);
    }

    public void setDisplayModeLabel(Constants.MODE mode) {
        String text = "Mode: ";
        switch(mode) {
            case AUTO:
                text += "AUTO";
                break;
            case IDLE:
                text += "IDLE";
                break;
            case MOVIE:
                text += "MOVIE";
                break;
            default:
                return;
        }
        displayModeLabel.setText(text);
    }

    public void setUpBottomPanel() {
        setUpLabelPanel();
        setUpButtonPanel();

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.add(labelPanel);
        bottomPanel.add(buttonPanel);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
    }

    public void setUpLabelPanel() {
        sourceLabel = new JLabel("Source: ");
        displayModeLabel = new JLabel("Mode: ");
        labelPanel = new JPanel();

        labelPanel.add(displayModeLabel);
        labelPanel.add(sourceLabel);
    }

    public void setUpImagePanels() {
        imagePanel1 = new ImagePanel();
        imagePanel2 = new ImagePanel();
        this.getContentPane().add(imagePanel1, BorderLayout.WEST);
        this.getContentPane().add(imagePanel2, BorderLayout.EAST);
    }

    public void setUpButtonPanel() {
        syncB = new JButton("Synchronous");
        connectB = new JButton("Connect");
        movieB = new JButton("Movie");
        idleB = new JButton("Idle");
        autoB = new JButton("Auto");

        buttonPanel = new JPanel();
        buttonPanel.add(syncB);
        buttonPanel.add(connectB);
        buttonPanel.add(movieB);
        buttonPanel.add(idleB);
        buttonPanel.add(autoB);
    }

    public void setSyncModeLabel(boolean sync) {
        if (sync) {
            syncB.setText("Unsynchronize");
        } else {
            syncB.setText("Synchronize");
        }
    }

    public void linkButtonListeners() {
        connectB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean success = c.connectButton();
                if (success) {
                    if (c.isConnected()) {
                        connectB.setText("Disconnect");
                    } else {
                        connectB.setText("Connect");
                    }
                }
            }
        });

        idleB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.idleButton();
            }
        });

        movieB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.movieButton();
            }
        });

        autoB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.autoButton();
            }
        });

        syncB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.syncButton();
            }
        });

    }
}

