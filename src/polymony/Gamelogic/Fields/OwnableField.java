package polymony.Gamelogic.Fields;

import polymony.Gamelogic.Player.Player;

public abstract class OwnableField extends Field{
  int price;
  Player owner;
  String name;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
