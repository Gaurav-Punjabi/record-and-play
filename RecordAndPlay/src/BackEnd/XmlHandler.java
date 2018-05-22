package BackEnd;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlHandler implements LogConstants,XmlConstants {
    private File path;
    private int count;
    public XmlHandler(String path) {
        this.path = new File(path);
        if(!this.path.exists()) {
            try {
                Files.createFile(this.path.toPath(), null);
            } catch (IOException ioe) {
                System.out.println("IOException caught : " + ioe.getMessage());
            } catch (Exception e) {
                System.out.println("Some other exception while creating the file : " + e.getMessage());
            }
        } else {
            this.count = this.getLogs().size();
        }
        this.removeAllLogs();
    }
    public void addLog(Log log) {
        int logType = log.getType();
        int x,y,keyCode,buttonMask;
        x = log.getX();
        y = log.getY();
        keyCode = log.getKeyCode();
        buttonMask = log.getButtonMask();
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(this.path, true);
        } catch(IOException ioe) {
            JOptionPane.showMessageDialog(null,"Some IO Exception : " + ioe.getMessage());
            return;
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Some exception while initializing FOS : " + e.getMessage());
            return;
        }
        switch (logType) {
            case MOUSE_CLICKED :
                String mouseClickedTag = "";
                try {
                    fileOutputStream.write(("<" + MOUSE_CLICKED_TAG + " " +
                            X_TAG + "=" + x + " " + Y_TAG + "=" + y +
                            ">" + buttonMask +
                            "</" + MOUSE_CLICKED_TAG + ">").getBytes());
                } catch(IOException ioe) {
                    JOptionPane.showMessageDialog(null,"IOException " + ioe.getMessage());
                } catch(Exception e) {
                    JOptionPane.showMessageDialog(null, "EXCPETION : " + e.getMessage());
                }
                break;

            case KEY_PRESSED :
                String keyPressedTag = "";
                try {
                    fileOutputStream.write(("<" + KEY_PRESSED_TAG + ">" +
                                            keyCode +
                                            "</" + KEY_PRESSED_TAG + ">").getBytes());
                } catch(IOException ioe) {
                    ioe.printStackTrace();
                }
                break;

            case KEY_RELEASED :
                String keyReleasedTag = "";
                try {
                    fileOutputStream.write(("<" + KEY_RELEASED_TAG + ">" +
                            keyCode +
                            "</" + KEY_RELEASED_TAG + ">").getBytes());
                } catch(IOException ioe) {
                    ioe.printStackTrace();
                }
                break;
        }
    }
    public void removeAllLogs() {
        try {
            FileOutputStream fos = new FileOutputStream(this.path);
            fos.write(" ".getBytes());
        } catch(IOException ioe) {
            System.out.println("Remove All Logs : " + ioe.getMessage());
        }
    }
    public List<Log> getLogs() {
        ArrayList<Log> logs = new ArrayList<>();
        Matcher matcher = null;
        String pattern = "<([\\w\\-]*)([ =\\w\\d]*)>([\\w\\d]*)</\\1>";
        try {
            matcher = Pattern.compile(pattern).matcher(new String(Files.readAllBytes(this.path.toPath())));
        } catch(IOException ioe) {
            System.out.println("Inside GetLogs");
            System.out.println("ioe : " + ioe.getMessage());
        }
        while(matcher.find()) {
            Log log;
            switch(matcher.group(1)) {
                case MOUSE_CLICKED_TAG :
                    Matcher mouseMatcher = Pattern.compile("<" + MOUSE_CLICKED_TAG + " " +
                                                            X_TAG + "=" + "(\\d+) " +
                                                            Y_TAG + "=(\\d+)" + ">(\\d*)" +
                                                            "</" + MOUSE_CLICKED_TAG + ">").matcher(matcher.group(0));
                    
                    if(mouseMatcher.find()) {
                        log = new Log(count++,
                                LogConstants.MOUSE_CLICKED,
                                Integer.parseInt(mouseMatcher.group(1)),
                                Integer.parseInt(mouseMatcher.group(2)),
                                Integer.parseInt(mouseMatcher.group(3)));
                        logs.add(log);
                    }
                    break;

                case KEY_PRESSED_TAG :
                    Matcher keyPressedMatcher = Pattern.compile("<" + KEY_PRESSED_TAG + ">" +
                                                                "(\\d+)" +
                                                                "</" + KEY_PRESSED_TAG + ">").matcher(matcher.group(0));
                    if(keyPressedMatcher.find()) {
                        log = new Log(count++,
                                LogConstants.KEY_PRESSED,
                                Integer.parseInt(keyPressedMatcher.group(1)));
                        logs.add(log);
                    }
                    break;

                case KEY_RELEASED_TAG :
                    Matcher keyReleasedMatcher = Pattern.compile("<" + KEY_RELEASED_TAG + ">" +
                                                                 "(\\d+)" +
                                                                 "</" + KEY_RELEASED_TAG + ">").matcher(matcher.group(0));
                    if(keyReleasedMatcher.find()) {
                        log = new Log(count++,
                                LogConstants.KEY_RELEASED,
                                Integer.parseInt(keyReleasedMatcher.group(1)));
                        logs.add(log);
                    }
                    break;

            }
        }
        return logs;
    }
    private static void UnitTesting() {
        XmlHandler xmlHandler = new XmlHandler("/Users/gauravpunjabi/Desktop/Test.xml");
        xmlHandler.addLog(new Log(1,LogConstants.MOUSE_CLICKED,100,100, MouseEvent.BUTTON1_DOWN_MASK));
        System.out.println("xmlHandler.getLogs().size() = " + xmlHandler.getLogs().size());
    }

    public static void main(String[] args) {
        UnitTesting();
    }
}
