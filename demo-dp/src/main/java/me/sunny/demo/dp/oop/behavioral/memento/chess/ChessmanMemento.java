package me.sunny.demo.dp.oop.behavioral.memento.chess;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.sunny.demo.dp.oop.behavioral.memento.role.Memento;

@Data
@AllArgsConstructor
public class ChessmanMemento implements Memento {
    private String label;
    private int x;
    private int y;

}
