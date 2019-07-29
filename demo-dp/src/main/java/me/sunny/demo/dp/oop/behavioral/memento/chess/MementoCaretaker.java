package me.sunny.demo.dp.oop.behavioral.memento.chess;

import java.util.ArrayList;

import me.sunny.demo.dp.oop.behavioral.memento.role.Caretaker;

public class MementoCaretaker implements Caretaker {
    //定义一个集合来存储备忘录
    private ArrayList<ChessmanMemento> mementoList = new ArrayList<>();

    public ChessmanMemento getMemento(int i) {
        return (ChessmanMemento) mementoList.get(i);
    }

    public void addMemento(ChessmanMemento memento) {
        mementoList.add(memento);
    }
}
