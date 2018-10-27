import java.util.ArrayList;

public class ColourPalette {

    private int numberOfColours;
    private ArrayList<Colour> palette = new ArrayList<>();

    public ColourPalette(int maxNumberOfColours){
        numberOfColours = maxNumberOfColours;
    }

    public ColourPalette(){

    }

    public void add(Colour colour){
        if(palette.size() < numberOfColours){
            palette.add(colour);
        } else {
            System.out.println("Too many colours!");
        }
    }

    public Colour get(int index){
        return palette.get(index);
    }

    public int getNumberOfColours() {
        return numberOfColours;
    }

    public ArrayList<Colour> getPalette() {
        return palette;
    }

    public void setNumberOfColours(int colours){
        numberOfColours =colours;
    }

    public int getSizeOfPalette(){
        return palette.size();
    }

    public int getNumberOfGrayColours(){
        int grays = 0;
        for (Colour colour : palette) {
            if (colour.isGrey()){
                grays++;
            }
        }

        return grays;
    }

    public void mixColour(int index, Colour colour2){
        Colour colour1 = palette.get(index);
        colour1.mixWith(colour2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ColourPalette that = (ColourPalette) o;

        if (numberOfColours != that.numberOfColours) return false;
        return palette.equals(that.palette);
    }

    @Override
    public String toString() {
        return "ColourPalette{" +
                "numberOfColours=" + numberOfColours +
                ", palette=" + palette +
                '}';
    }
}
