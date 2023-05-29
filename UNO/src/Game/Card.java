package Game;

public class Card {
    private Color color;
    private int value;
    private boolean checkWild;
    private boolean wildAction;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isCheckWild() {
        return checkWild;
    }

    public void setCheckWild(boolean checkWild) {
        this.checkWild = checkWild;
    }

    public boolean isWildAction() {
        return wildAction;
    }

    public void setWildAction(boolean wildAction) {
        this.wildAction = wildAction;
    }

    public Card(Color color, int value) {
        this.color = color;
        this.value = value;
        this.checkWild = false;
        this.wildAction = false;
    }

    public Card(){

    }

    public boolean isSpecialActionCard(){
        return !checkWild && (value==10||value==11||value==12);
    }

    public void actionPerformed(){

    }
}
