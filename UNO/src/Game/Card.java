package Game;

public class Card {
    private Color color;
    private int value;

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

    public Card(Color color, int value) {
        this.color = color;
        this.value = value;
    }

    public Card(){

    }

    public boolean isSpecialActionCard(){
        return (value==10||value==11||value==12);
    }

    @Override
    public String toString() {
        return color +
                " " + value;
    }
}
