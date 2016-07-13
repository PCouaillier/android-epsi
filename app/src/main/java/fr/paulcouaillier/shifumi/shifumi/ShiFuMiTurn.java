package fr.paulcouaillier.shifumi.shifumi;

import java.io.Serializable;

/**
 * Created by paulcouaillier on 10/06/16.
 */
public class ShiFuMiTurn  implements Serializable{

    public Integer id;
    public int userInput;
    public int computerInput;
    public int choice;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return Integer.valueOf(this.id);
    }

    public int getUserInput() {
        return this.userInput;
    }

    public int getComputerInput() {
        return this.computerInput;
    }

    public int getChoice() {
        return this.choice;
    }

    public ShiFuMiTurn() {

    }

    public ShiFuMiTurn(int id, int userInput, int computerInput, int choice) {
        this(userInput, computerInput, choice);
        this.id = id;
    }

    public ShiFuMiTurn(int userInput, int computerInput, int choice) {
        this.userInput = userInput;
        this.computerInput = computerInput;
        this.choice = choice;
    }

    public void setUserInput(int userInput) {
        this.userInput = userInput;
    }

    public void setComputerInput(int computerInput) {
        this.computerInput = computerInput;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }
}
