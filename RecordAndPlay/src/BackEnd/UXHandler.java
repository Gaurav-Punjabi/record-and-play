package BackEnd;

import UserInterface.Constants;
import UserInterface.MainFrame;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UXHandler implements MouseListener, Constants {

    private MainFrame ref;
    private boolean isRecord, isPlay;

    public UXHandler(MainFrame ref) {
        this.ref = ref;
        this.isPlay = true;
        this.isRecord = true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == ref.getRecordPanel()) {
            if(isRecord) {
                ref.getRecordLabel().setText("Stop");
                ref.getRecordIconLabel().setIcon(ref.getStopIcon());
                this.isRecord = false;
                return;
            }
            ref.getRecordLabel().setText("Record");
            ref.getRecordIconLabel().setIcon(ref.getRecordIcon());
            this.isRecord = true;
        } else if(e.getSource() == ref.getPlayPanel()) {
            if(isPlay) {
                ref.getPlayLabel().setText("Pause");
                ref.getPlayIconLabel().setIcon(ref.getPauseIcon());
                this.isPlay = false;
                return;
            }
            ref.getPlayLabel().setText("Play");
            ref.getPlayIconLabel().setIcon(ref.getPlayIcon());
            this.isPlay = true;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        ((JPanel)e.getSource()).setBackground(PANEL_CLICKED_COLOR);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ((JPanel)e.getSource()).setBackground(PANEL_HOVER_COLOR);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        ((JPanel)e.getSource()).setBackground(PANEL_HOVER_COLOR);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        ((JPanel)e.getSource()).setBackground(PANEL_COLOR);
    }
}
