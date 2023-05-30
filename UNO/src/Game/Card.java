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
        return (value==10||value==11||value==12||color==Color.WILD||color==Color.WILD4);
    }

    @Override
    public String toString() {
        if(value==10)return color + " REVERSE";
        else if(value==11)return color + " SKIP";
        else if(value==12)return color + " +2";
        else if(color==Color.WILD && value==-1) return "WILD";
        else if(color==Color.WILD4 && value==-1) return "WILD +4";
        else return color + " " + value;
    }
}
