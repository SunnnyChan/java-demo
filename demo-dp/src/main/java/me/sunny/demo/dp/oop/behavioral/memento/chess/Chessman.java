package me.sunny.demo.dp.oop.behavioral.memento.chess;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.sunny.demo.dp.oop.behavioral.memento.role.Originator;

@Data
@AllArgsConstructor
public class Chessman implements Originator {
    private String label;
    private int x;
    private int y;

    //保存状态
    public ChessmanMemento save() {
        return new ChessmanMemento(this.label, this.x, this.y);
    }

    //恢复状态
    public void restore(ChessmanMemento memento) {
        this.label = memento.getLabel();
        this.x = memento.getX();
        this.y = memento.getY();
    }


    public void show() {
        System.out.println(String.format("棋子<%s>：当前位置为：<%d, %d>", this.getLabel(), this.getX(), this.getY()));
    }
}
