package UserInterface;

import BackEnd.EventHandler;
import BackEnd.UXHandler;
import jiconfont.icons.FontAwesome;
import jiconfont.icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements Constants {
    private JPanel jpRecord, jpPlay;
    private JLabel jlRecordIcon, jlPlayIcon, jlRecord, jlPlay;
    private Icon icnRecord, icnPlay, icnStop, icnPause;
    private UXHandler uxHandler;
    private EventHandler eventHandler;

    public MainFrame() {
        super("Record And Play.");
        initialization();
    }

    /**
     * For Unit Testing Purpose only....
     */
    private static void unitTesting() {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        unitTesting();
    }

    private void initialization() {
        //Setting up the frame
        this.setSize(260, 50);
        this.getContentPane().setBackground(Color.WHITE);

        //Setting up Icon Font For Swing ....
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        IconFontSwing.register(FontAwesome.getIconFont());

        //Initializing the variables...
        eventHandler = new EventHandler(this);
        uxHandler = new UXHandler(this);
        jpRecord = new JPanel();
        jpPlay = new JPanel();
        jlRecordIcon = new JLabel();
        jlPlayIcon = new JLabel();
        jlRecord = new JLabel("Record");
        jlPlay = new JLabel("Play");
        icnPause = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PAUSE, 32, PAUSE_ICON_COLOR);
        icnPlay = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PLAY_ARROW, 32, PLAY_ICON_COLOR);
        icnStop = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.STOP, 32, STOP_ICON_COLOR);
        icnRecord = IconFontSwing.buildIcon(FontAwesome.CIRCLE, 32, RECORD_ICON_COLOR);

        jpRecord.setBackground(Color.WHITE);
        jpPlay.setBackground(Color.WHITE);

        //Setting the location and size of the components...
        jpRecord.setBounds(5, 0, 130, 32);
        jpPlay.setBounds(130, 0, 130, 32);
        jlRecordIcon.setBounds(0, 0, 32, 32);
        jlRecord.setBounds(32, 0, 200, 32);
        jlPlayIcon.setBounds(0, 0, 32, 32);
        jlPlay.setBounds(32, 0, 160, 32);

        //Setting the Layout of the containers...
        this.setLayout(null);
        jpRecord.setLayout(null);
        jpPlay.setLayout(null);

        //Setting the icons...
        jlRecordIcon.setIcon(icnRecord);
        jlPlayIcon.setIcon(icnPlay);

        //Setting the font of labels....
        this.jlRecord.setFont(DEFAULT_FONT);
        this.jlPlay.setFont(DEFAULT_FONT);

        //Adding the respective Listeners.
        jpRecord.addMouseListener(uxHandler);
        jpPlay.addMouseListener(uxHandler);
        jpRecord.addMouseListener(eventHandler);
        jpPlay.addMouseListener(eventHandler);

        //Adding the components...
        this.add(jpRecord);
        this.add(jpPlay);

        jpRecord.add(jlRecordIcon);
        jpRecord.add(jlRecord);

        jpPlay.add(jlPlay);
        jpPlay.add(jlPlayIcon);
    }

    public JPanel getRecordPanel() {
        return this.jpRecord;
    }

    public JPanel getPlayPanel() {
        return this.jpPlay;
    }

    public JLabel getRecordIconLabel() {
        return this.jlRecordIcon;
    }

    public JLabel getPlayIconLabel() {
        return this.jlPlayIcon;
    }

    public JLabel getRecordLabel() {
        return this.jlRecord;
    }

    public JLabel getPlayLabel() {
        return this.jlPlay;
    }

    public Icon getPlayIcon() {
        return this.icnPlay;
    }

    public Icon getRecordIcon() {
        return this.icnRecord;
    }

    public Icon getPauseIcon() {
        return this.icnPause;
    }

    public Icon getStopIcon() {
        return this.icnStop;
    }
}
