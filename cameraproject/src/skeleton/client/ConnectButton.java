package skeleton.client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by michael on 03/12/15.
 */
public class ConnectButton extends JButton implements ActionListener {
    ClientManager c;

    public ConnectButton(ClientManager c) {
        super("Connect!");
        this.c = c;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent evt) {
        c.connectButton();
    }
}