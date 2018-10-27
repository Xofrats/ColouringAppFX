public class Colour {

    private int red;
    private int green;
    private int blue;

    public Colour(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public void setAll(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public boolean isGrey(){
        if(red == green && red == blue){
            return true;
        } else {
            return false;
        }
    }

    public void mixWith(Colour colour2){
        red = ((red + colour2.getRed()) + ((red + colour2.getRed())%2)) / 2;
        green = ((green + colour2.getGreen() + ((green + colour2.getGreen())%2)) / 2);
        blue = ((blue + colour2.getBlue() +((blue + colour2.getBlue())%2)) / 2);
    }

    public Colour copy(){
        Colour copy = new Colour(this.red, this.green, this.blue);
        return copy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Colour colour = (Colour) o;

        if (red != colour.red) return false;
        if (green != colour.green) return false;
        return blue == colour.blue;
    }

    @Override
    public String toString() {
        return "Colour{" +
                "red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                '}';
    }
}
