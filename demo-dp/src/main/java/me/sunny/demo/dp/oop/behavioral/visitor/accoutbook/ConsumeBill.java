package me.sunny.demo.dp.oop.behavioral.visitor.accoutbook;

import lombok.Getter;

public class ConsumeBill implements Bill {
    @Getter
    private double amount;
    @Getter
    private String item;

    public ConsumeBill(double amount, String item) {
        this.amount = amount;
        this.item = item;
    }

    public void accept(Viewer viewer) {
        viewer.view(this);
    }
}
