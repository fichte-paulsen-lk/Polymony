package polymony.Gamelogic.Fields;

public abstract class Field {
  int index;
  String name;

public String getName() {
        return name;
    }

 public void setName(String name) {
       this.name = name;
  }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
 
}