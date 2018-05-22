package BackEnd;

import UserInterface.MainFrame;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.LogManager;

public class EventHandler implements MouseListener,NativeMouseInputListener,NativeKeyListener{
    private MainFrame ref;
    private boolean isRecord;
    private boolean isPlay;
    private XmlHandler xmlHandler;
    private int count = 0;
    public EventHandler(final MainFrame ref) {
        this.ref = ref;
        this.isRecord = false;
        this.isPlay = false;
        this.xmlHandler = new XmlHandler("/Users/gauravpunjabi/Desktop/Test.xml");
        GlobalScreen.addNativeMouseListener(this);
        GlobalScreen.addNativeKeyListener(this);
        try {
            GlobalScreen.registerNativeHook();
        } catch(NativeHookException nhe) {
            System.out.println("nhe.getMessage() = " + nhe.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
        }
        LogManager.getLogManager().reset();
    }
    private void startPlaying() {
        try {
            System.out.println("Starting PLay.");
            Robot robot = new Robot();
            System.out.println("xmlHandler.getLogs().size() = " + xmlHandler.getLogs().size());
            for(Log log : xmlHandler.getLogs()) {
                if(!isPlay)
                    break;
                System.out.println("Processing the event.");
                switch (log.getType()) {
                    case LogConstants.MOUSE_CLICKED :
                        System.out.println("Mouse Pressed");
                        robot.mouseMove(log.getX(),log.getY());
                        robot.mousePress(MouseEvent.BUTTON1_MASK);
                        robot.mouseRelease(MouseEvent.BUTTON1_MASK);
                    break;

                    case LogConstants.KEY_PRESSED:
                        robot.keyPress(log.getKeyCode());
                    break;

                    case LogConstants.KEY_RELEASED:
                        robot.keyRelease(log.getKeyCode());
                        break;
                }
                try {
                    Thread.sleep(400);
                } catch(Exception e) {
                }
            }
        } catch (AWTException awte) {
            System.out.println(awte.getMessage());
        }
    }

    private void recordAction() {
        this.isRecord = !isRecord;
        System.out.println("The value of isRecord is : " + isRecord);
    }
    private void playAction() {
        this.isPlay = !isPlay;
        startPlaying();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == ref.getRecordPanel()) {
            recordAction();
        } else if(e.getSource() == ref.getPlayPanel()) {
            playAction();
        }
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {}

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        if(!isRecord) {
            System.out.println("Suspending the event.");
            return;
        }
        if(isPlay) {
            return;
        }
        Log log = new Log(count++,LogConstants.KEY_PRESSED,nativeKeyEvent.getKeyCode());
        xmlHandler.addLog(log);
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        if(!isRecord) {
            System.out.println("Suspending the event.");
            return;
        }
        if(isPlay) {
            return;
        }
        Log log = new Log(count++,LogConstants.KEY_RELEASED,nativeKeyEvent.getKeyCode());
        xmlHandler.addLog(log);
    }

    @Override
    public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {
        if(!isRecord) {
            System.out.println("Suspending the event.");
            return;
        }
        if(isPlay) {
            return;
        }
        Log log = new Log(count++,LogConstants.MOUSE_CLICKED,nativeMouseEvent.getX(),nativeMouseEvent.getY(),MouseEvent.BUTTON1_DOWN_MASK);
        xmlHandler.addLog(log);
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {}

    @Override
    public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {}

    @Override
    public void nativeMouseMoved(NativeMouseEvent nativeMouseEvent) {}

    @Override
    public void nativeMouseDragged(NativeMouseEvent nativeMouseEvent) {}
}
