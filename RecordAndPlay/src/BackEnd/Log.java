package BackEnd;

public class Log {
    /**
     * Variable Declaration
     */
    private int id, type, x, y;
    private int keyCode,buttonMask;

    /**
     * - Constructors
     * This is the main constructor which initializes all the variables.
     * @param id - the id of the log,to uniquely identify it.
     * @param type - the type of log .i.e whether it is a mouse event or key event or etc...
     * @param x - the x-co-ordinate of the mouse event.
     * @param y - the y-co-ordinate of the mouse event.
     * @param buttonMask - the mask of the button that is pressed.
     * @param keyCode - the virtual key code of the key that is pressed/released/typed.
     */
    public Log(final int id,
               final int type,
               final int x,
               final int y,
               final int buttonMask,
               final int keyCode) {
        this.id = id;
        this.type = type;
        this.x = x;
        this.y = y;
        this.keyCode = keyCode;
        this.buttonMask = buttonMask;
    }

    /**
     * Just another constructor for providing more flexibility of the parameters
     * @param id
     * @param type
     * @param x
     * @param y
     * @param buttonMask
     */
    public Log(final int id,
               final int type,
               final int x,
               final int y,
               final int buttonMask) {
        this(id, type, x, y,buttonMask, 0);
    }

    /**
     * Just another constructor for providing more flexibility of the parameters
     * @param id
     * @param type
     * @param keyCode
     */
    public Log(final int id,
               final int type,
               final int keyCode) {
        this(id,type,0,0,0,keyCode);
    }

    /**
     * Just another constructor for providing more flexibility of the parameters
     * @param id
     * @param type
     */
    public Log(final int id,
               final int type) {
        this(id,type,0,0,0,0);
    }

    /**
     *  SETTERS
     */
    public void setId(int id) {
        this.id = id;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    public void setButtonPressed(int buttonMask) {
        this.buttonMask = buttonMask;
    }

    /**
     * GETTERS
     */
    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public int getButtonMask() {
        return buttonMask;
    }

    @Override
    public String toString() {
        return String.format("Log : \n\tid - %d \n\ttype - %d\n\tx - %d \n\ty = %d \n\tkeyCode - %d",id,type,x,y,keyCode);
    }
}
